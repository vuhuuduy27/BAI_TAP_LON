package vn.edu.uet.model;
import vn.edu.uet.status.AuctionStatus;

public class Admin extends User {

    public Admin(String username, String password, String email) {
        super(username, password, email, "ADMIN");
    }

    // Hủy phiên đấu giá khẩn cấp
    public void cancelAuction(Auction auction) {
        auction.setStatus(AuctionStatus.CANCELED);
        auction.notifyObservers("Phiên đấu giá đã bị ban quản trị hủy bỏ!");
    }

    @Override
    public void printInfo() {
        System.out.println("[Admin Quản trị] " + username + " | Email: " + email);
    }
}