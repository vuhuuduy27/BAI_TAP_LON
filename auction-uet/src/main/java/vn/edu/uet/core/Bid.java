package vn.edu.uet.core;

import java.time.LocalDateTime;
import java.util.Objects;
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
        if(this == o){ // Bằng chính nó thì luôn đúng
            return true;
        }else if(o == null || getClass() != o.getClass()){
            // So sánh xem có đúng là Bid hay không
            return false;
        }
        // Kiểm tra xong thì chắc chắn là Bid
        Bid bid = (Bid) o;
        return Objects.equals(id, bid.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id); // Băm Id ra số nguyên
    }
}
