import java.time.LocalDateTime;
import java.util.UUID;

public class Bid { // lượt đặt giá
    private String id;
    private String auctionId;
    private String bidderId;
    private long amount;
    private LocalDateTime time;

    public Bid(String auctionId, String bidderId, long amount){
        this.id = UUID.randomUUID().toString();
        this.auctionId = auctionId;
        this.bidderId = bidderId;
        this.amount = amount;
        this.time = LocalDateTime.now();
    }

    //Getter
    public String getId() {
        return id;
    }
    public String getAuctionId() {
        return auctionId;
    }
    public String getBidderId() {
        return bidderId;
    }

    public LocalDateTime getTime() {
        return time;
    }
    public long getAmount() {
        return amount;
    }
}
