import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Auction { //phiên đấu giá
    private String id;
    private Item item;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long currentPrice;
    private String leadingBidderId;
    private List<Bid> bids;
    private AuctionStatus statusAuction;

    public Auction(Item item, LocalDateTime startTime, LocalDateTime endTime){
        this.id = UUID.randomUUID().toString();
        this.item = item;
        this.startTime = startTime;
        this.endTime = endTime;
        this.currentPrice = item.getStartingPrice();
        this.bids = new ArrayList<>();
        this.statusAuction = AuctionStatus.OPEN;

    }
    // xứ lý thêm luợt đặt giá vào phiên
    public boolean addBid(Bid bid){
        if(bid == null){
            return false;
        }
        if(statusAuction != AuctionStatus.RUNNING){
            return false;
        }
        if(!id.equals(bid.getAuctionId())){
            return false;
        }
        if(bid.getAmount() <= currentPrice){
            return false;
        }
        bids.add(bid);
        currentPrice = bid.getAmount();
        leadingBidderId = bid.getBidderId();
        return true;
    }

    //Getter
    public String getId() {
        return id;
    }
    public Item getItem() {
        return item;
    }
    public long getCurrentPrice() {
        return currentPrice;
    }
    public String getLeadingBidderId() {
        return leadingBidderId;
    }
    public List<Bid> getBids() {
        return List.copyOf(bids);// tránh sửa mảng gốc
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public AuctionStatus getStatusAuction() {
        return statusAuction;
    }

    // các phương thức hệ thống thực hiện bắt đầu phiên/ kết thúc phiên/ hủy phiên
    public boolean start(){
        if(statusAuction != AuctionStatus.OPEN){
            return false;
        }
        statusAuction = AuctionStatus.RUNNING;
        return true;
    }

    public boolean finish(){
        if(statusAuction != AuctionStatus.RUNNING) {
            return false;
        }
        statusAuction = AuctionStatus.FINISHED;
        return true;
    }

    public boolean cancel(){
        if(statusAuction != AuctionStatus.OPEN){
            return false;
        }
        statusAuction = AuctionStatus.CANCELED;
        return true;
    }
    public boolean isActive(){// có hay không đều được
        return statusAuction == AuctionStatus.RUNNING;
    }
}
