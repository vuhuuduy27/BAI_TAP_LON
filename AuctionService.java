import java.time.LocalDateTime;

public class AuctionService {
    AuctionManager auctionManager;
    public AuctionService(AuctionManager auctionManager){
        this.auctionManager = auctionManager;
    }

    //kiểm tra và Gọi các thao tác nghiệp vụ cho phiên đấu giá
    //kiểm tra và gọi Mở phiên
    public boolean startAuction(String auctionId){
        Auction auction = auctionManager.getAuctionById(auctionId);
        if(auction == null){
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(auction.getStartTime()) || !now.isBefore(auction.getEndTime())){ // nằm ngoài khoảng thời gian
            return false;
        }
        return auction.start();
    }
    //kiểm tra và Đóng phiên
    public boolean finishAuction(String auctionId){
        Auction auction = auctionManager.getAuctionById(auctionId);
        if(auction == null){
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        if(now.isBefore(auction.getEndTime())){ // trước endTime
            return false;
        }
        return auction.finish();
    }
    //kiểm tra và gọi Hủy phiên
    public boolean cancelAuction(String auctionId){
        Auction auction = auctionManager.getAuctionById(auctionId);
        if(auction == null){
            return false;
        }
        return auction.cancel();
    }

    // Kiểm tra đặt giá hợp lệ và đặt giá
    public boolean placeBid(String auctionId, String bidderId, long amount){
        if (auctionId == null || bidderId == null || bidderId.isBlank() || amount <= 0) { //.isBlank(): tránh chuỗi rỗng
            return false;
        }
        Auction auction = auctionManager.getAuctionById(auctionId);
        if (auction == null) {
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(auction.getStartTime()) || !now.isBefore(auction.getEndTime())) {
            return false;
        }
        // có thể bổ sung 1 if kiểm tra trạng thái vào đây. Nó khác với 3 phuong thức ở trên vì ở trên nó return về phương thức thực hiện chuyển trạng thái
        Bid bid = new Bid(auctionId, bidderId, amount);
        return auction.addBid(bid);
    }
}
