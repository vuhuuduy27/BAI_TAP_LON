package vn.edu.uet.model;

import vn.edu.uet.exception.AuctionClosedException;
import vn.edu.uet.exception.InvalidBidException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import vn.edu.uet.status.AuctionStatus;

public class Auction implements Subject, Serializable {
    private String id;
    private Item item;
    private Seller seller;
    private double currentHighestBid;
    private Bidder winningBidder;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    // Sử dụng volatile để truyền trạng thái tức thì giữa các luồng
    private volatile AuctionStatus status;

    // Cấu hình Anti-sniping
    private int antiSnipingThresholdSeconds = 30; // 30 giây cuối
    private int extendDurationSeconds = 60;        // Gia hạn thêm 60 giây

    // Khóa ReadWriteLock đa luồng
    private transient ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    // CopyOnWriteArrayList chống xung đột luồng khi gửi Notify Realtime
    private transient List<Observer> observers = new CopyOnWriteArrayList<>();
    
    // Lịch sử đặt giá phục vụ vẽ biểu đồ LineChart
    private List<BidTransaction> bidHistory = new ArrayList<>();

    public Auction(Item item, Seller seller, LocalDateTime startTime, LocalDateTime endTime) {
        this.item = item;
        this.seller = seller;
        this.startTime = startTime;
        this.endTime = endTime;
        this.currentHighestBid = item.getStartingPrice();
        this.status = AuctionStatus.OPEN;
    }

    // Đặt giá an toàn đa luồng bằng Write Lock
    public void placeBid(Bidder bidder, double amount) throws InvalidBidException, AuctionClosedException {
        rwLock.writeLock().lock();
        try {
            // 1. Kiểm tra trạng thái phiên
            if (this.status != AuctionStatus.RUNNING) {
                throw new AuctionClosedException("Phiên đấu giá hiện không ở trạng thái hoạt động!");
            }

            // 2. Kiểm tra giá đặt
            if (amount <= this.currentHighestBid) {
                throw new InvalidBidException("Mức giá đặt (" + amount + ") phải lớn hơn giá hiện tại (" + currentHighestBid + ")");
            }

            // 3. Cập nhật thông tin giá cao nhất
            this.currentHighestBid = amount;
            this.winningBidder = bidder;

            // 4. Tạo bản ghi giao dịch
            BidTransaction transaction = new BidTransaction(bidder, amount, LocalDateTime.now());
            this.bidHistory.add(transaction);

            // 5. Kiểm tra Anti-sniping
            checkAndExtendEndTime();

            // 6. Phát thông báo Realtime cho các Client
            notifyObservers("Giá mới: " + amount + " VNĐ bởi " + bidder.getUsername());

        } finally {
            rwLock.writeLock().unlock();
        }
    }

    // Tự động gia hạn khi bị anti-sniping
    public void checkAndExtendEndTime() {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(endTime) && now.plusSeconds(antiSnipingThresholdSeconds).isAfter(endTime)) {
            this.endTime = this.endTime.plusSeconds(extendDurationSeconds);
            notifyObservers("Phiên đấu giá đã được tự động gia hạn thêm " + extendDurationSeconds + " giây!");
        }
    }

    // Đóng phiên đấu giá
    public void closeAuction() {
        rwLock.writeLock().lock();
        try {
            this.status = AuctionStatus.FINISHED;
            if (winningBidder != null) {
                notifyObservers("Phiên đấu giá kết thúc! Người chiến thắng: " + winningBidder.getUsername() + " với giá " + currentHighestBid);
            } else {
                notifyObservers("Phiên đấu giá kết thúc mà không có lượt đặt giá nào!");
            }
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    // Đọc thông tin an toàn bằng Read Lock
    public double getCurrentHighestBid() {
        rwLock.readLock().lock();
        try {
            return currentHighestBid;
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public List<BidTransaction> getBidHistory() {
        return Collections.unmodifiableList(bidHistory);
    }

    @Override
    public void addObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    // Getters & Setters
    public AuctionStatus getStatus() { return status; }
    public void setStatus(AuctionStatus status) { this.status = status; }
    public Item getItem() { return item; }
    public Seller getSeller() { return seller; }
    public Bidder getWinningBidder() { return winningBidder; }
    public LocalDateTime getEndTime() { return endTime; }
}