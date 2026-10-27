package vn.edu.uet.model;

import java.time.LocalDateTime;

public class Seller extends User {
    private double rating;

    public Seller(String username, String password, String email) {
        super(username, password, email, "SELLER");
        this.rating = 5.0; // Mặc định điểm uy tín ban đầu là 5.0
    }

    // Khởi tạo một phiên đấu giá mới
    public Auction createAuction(Item item, LocalDateTime startTime, LocalDateTime endTime) {
        return new Auction(item, this, startTime, endTime);
    }

    @Override
    public void printInfo() {
        System.out.println("[Seller] " + username + " | Email: " + email + " | Đánh giá uy tín: " + rating + "/5.0");
    }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}