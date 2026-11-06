package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Bidder extends User {
    private long balance;
    private Map<String, Long> reservedByAuction;
    private Set<String> joinedAuctionIds;

    //Constructor
    public Bidder(String username, String rawPassword, String email, String fullName){
        super(username, rawPassword, email, fullName);
        this.balance = 0;
        this.reservedByAuction = new HashMap<>();
        this.joinedAuctionIds = new HashSet<>();
    }

    //Getter
    public Map<String, Long> getReservedByAuction(){
        return Map.copyOf(reservedByAuction);
    }

    public Set<String> getJoinedAuctionIds(){
        return Set.copyOf(joinedAuctionIds);
    }


//QUẢN LÝ SỐ DƯ TÀI KHOẢN
    //Tổng tiền
    public long getBalance(){
        return balance;
    }

    //Tiền đang bị giữ
    public long getFrozenAmount(){
        long total = 0;
        for(long amount : reservedByAuction.values()){
            total += amount;
        }
        return total;
    }

    //Tiền còn dùng được
    public long getAvailableBalance(){
        return balance - getFrozenAmount();
    }

    //Còn đủ tiền để đặt giá không?
    public boolean canAfford(long amount){
        return amount > 0 && amount <= getAvailableBalance();
    }
    // Nạp tiền
    public void deposit(long amount){
        if(amount <= 0){
            throw new IllegalArgumentException("Số tiền phải là số dương");
        }
        balance += amount;
    }
    //Rút tiền
    public void withdraw(long amount){
        if (amount <= 0) {
            throw new IllegalArgumentException("Số tiền phải là số dương");
        }
        if (amount > getAvailableBalance()) {
            throw new IllegalStateException("Số dư khả dụng không đủ");
        }
        balance -= amount;
    }


//QUẢN LÝ PHIÊN ĐẤU GIÁ

    //Kiểm tra xem tham gia phiên đấu giá chưa
    public boolean hasJoinAuction(String auctionId){
        return joinedAuctionIds.contains(auctionId);
    }

    //Tham gia phiên đấu giá
    public boolean joinAuction(String auctionId){
        if (auctionId == null || auctionId.isBlank()) {
            return false;
        }
        return joinedAuctionIds.add(auctionId);
    }

    //Rời phiên đấu giá
    public boolean leaveAuction(String auctionId){
        if(auctionId == null || auctionId.isBlank()){
            return false;
        }
        if(reservedByAuction.containsKey(auctionId)){
            return false;
        }
        return joinedAuctionIds.remove(auctionId);
    }

// QUẢN LÝ GIỮ TIỀN
    // giữ tiền cho 1 phiên
    public boolean reserveFunds(String auctionId, long amount){
        if (auctionId == null || auctionId.isBlank()) {
            return false;
        }
        if (amount <= 0 || !canAfford(amount)) {
            return false;
        }
        if (reservedByAuction.containsKey(auctionId)) {
            return false;
        }
        reservedByAuction.put(auctionId, amount);
        return true;
    }

    //cập nhật giữ tiền nếu như muốn tăng
    public boolean updateReservedFunds(String auctionId, long newAmount){
        if(auctionId == null || auctionId.isBlank()){
            return false;
        }
        if(newAmount <= 0){
            return false;
        }
        long oldAmount = reservedByAuction.getOrDefault(auctionId, 0L);
        if(newAmount > getAvailableBalance() + oldAmount){
            return false;
        }
        reservedByAuction.put(auctionId, newAmount);
        return true;
    }


    // giải phóng tiền cho 1 phiên
    public boolean releaseFunds(String auctionId){
        if(auctionId == null){
            return false;
        }
        if(!reservedByAuction.containsKey(auctionId)){
            return false;
        }
        reservedByAuction.remove(auctionId);
        return true;
    }

    @Override
    public userRole getRole() {
        return userRole.BIDDER;
    }

    @Override
    public String getDescription() {
        return "model.Bidder: " + getUsername() + ", Balance: " + balance + ", Available: " + getAvailableBalance();
    }
}
