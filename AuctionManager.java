import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuctionManager {
    private static AuctionManager instance = null;

    private Map<String, Auction> auctions;

    private AuctionManager() {
        auctions = new HashMap<>();
    }

    public static AuctionManager getInstance() {
        if (instance == null) {
            instance = new AuctionManager();
        }
        return instance;
    }

    //Thêm phiên vào hệ thống quản lý phiên
    public boolean addAuction(Auction auction) {
        if (auction == null) {
            return false;
        }
        if (auctions.containsKey(auction.getId())) {
            return false;
        }
        auctions.put(auction.getId(), auction);
        return true;
    }

    //Tìm phiên theo ID
    public Auction getAuctionById(String id) {
        return auctions.get(id);
    }

    //In ra danh sách các phiên đấu giá
    public Map<String, Auction> getAllAuctions() {
        return Map.copyOf(auctions);
    }

    //In ra danh sách các phiên theo trạng thái
    public List<Auction> getAuctionByStatus(AuctionStatus status) {
        List<Auction> auctionByStatus = new ArrayList<>();
        if (status == null) {
            return auctionByStatus;
        }
        for (Auction a : auctions.values()) {
            if (a.getStatusAuction() == status) {
                auctionByStatus.add(a);
            }
        }
        return auctionByStatus;
    }

    //Xóa phiên trong danh sách
}
