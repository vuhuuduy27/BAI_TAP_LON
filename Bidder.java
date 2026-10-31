import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Bidder extends User{
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
    public boolean deposit(long amount){
        if(amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }
    //Rút tiền
    public boolean withdraw(long amount){
        if(amount > 0 && amount <= getAvailableBalance()){
            balance -= amount;
            return true;
        }
        return false;
    }


//QUẢN LÝ PHIÊN ĐẤU GIÁ

    //Kiểm tra xem tham gia phiên đấu giá chưa
    public boolean hasJoinAuction(String auctionId){
        return joinedAuctionIds.contains(auctionId);
    }
    //Tham gia phiên đấu giá(chưa cần đặt giá)
    public boolean joinAuction(String auctionId){
        if(!hasJoinAuction(auctionId)) { // if này cần thêm một số điều kiện của phiên đấu giá nữa
            return joinedAuctionIds.add(auctionId);

        }
        return false;
    }
    //Rời phiên đấu giá nếu đủ điều kiện
    public boolean leaveAuction(String auctionId){
        if(hasJoinAuction(auctionId)) { // if này cần thêm một số điều kiện của phiên đấu giá nữa
            return joinedAuctionIds.remove(auctionId);
        }
        return false;
    }

// QUẢN LÝ GIỮ TIỀN
    // giữ tiền cho 1 phiên
    public boolean reserveFunds(String auctionId, long amount){
        if(hasJoinAuction(auctionId)) {
            reservedByAuction.put(auctionId, amount);
            return true;
        }
        return false;
    }
    // trả lại tiền khi rời phiên
    public boolean releaseFunds(String auctionId){
        if(leaveAuction(auctionId)){
            reservedByAuction.remove(auctionId);
            return true;
        }
        return false;
    }
}
