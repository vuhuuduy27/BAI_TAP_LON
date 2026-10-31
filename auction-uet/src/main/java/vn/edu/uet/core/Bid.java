package vn.edu.uet.core;

import java.time.LocalDateTime;
import java.util.UUID;

public class Bid {

    private String id;
    private String auctionId;
    private String bidderId;
    private long amount;
    private LocalDateTime time;

    // Ghi vé đấu giá
    public Bid(String auctionId, String bidderId, long amount){
        this.id = UUID.randomUUID().toString(); // Tự tạo id
        this.auctionId = auctionId;
        this.bidderId = bidderId;
        this.amount = amount;
        this.time = LocalDateTime.now();
    }

    public String getId(){return id;}
    public String getAuctionId(){return auctionId;}
    public String getBidderId(){return bidderId;}
    public LocalDateTime getTime(){return time;}
    public long getAmount(){return amount;}

    @Override
    public boolean equals(Object o){
        if(o instanceof Bid){
            Bid bid = (Bid) o;
            return id.equals(bid.id);
         }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
