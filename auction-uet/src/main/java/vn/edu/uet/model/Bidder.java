package vn.edu.uet.model;

import vn.edu.uet.exception.AuctionClosedException;
import vn.edu.uet.exception.InsufficientBalanceException;
import vn.edu.uet.exception.InvalidBidException;
import vn.edu.uet.strategy.IBidStrategy;

public class Bidder extends User implements Observer {
    private double balance;
    private IBidStrategy bidStrategy; // Áp dụng Strategy Pattern

    public Bidder(String username, String password, String email, double balance) {
        super(username, password, email, "BIDDER");
        this.balance = balance;
    }

    // Thực hiện đặt giá thủ công
    public void placeBid(Auction auction, double amount) throws InvalidBidException, AuctionClosedException, InsufficientBalanceException {
        if (amount > this.balance) {
            throw new InsufficientBalanceException("Số dư tài khoản không đủ để đặt mức giá " + amount + " VNĐ!");
        }
        auction.placeBid(this, amount);
    }

    // Thực hiện đặt giá tự động dựa trên Strategy
    public void autoPlaceBid(Auction auction) throws InvalidBidException, AuctionClosedException, InsufficientBalanceException {
        if (this.bidStrategy != null) {
            double nextBid = this.bidStrategy.calculateNextBid(auction.getCurrentHighestBid());
            this.placeBid(auction, nextBid);
        }
    }

    // Nhận thông báo Realtime từ phiên đấu giá (Observer Pattern)
    @Override
    public void update(String message) {
        System.out.println("[Thông báo đến Bidder " + username + "]: " + message);
    }

    public void deductBalance(double amount) {
        this.balance -= amount;
    }

    public void refund(double amount) {
        this.balance += amount;
    }

    @Override
    public void printInfo() {
        System.out.println("[Bidder] " + username + " | Email: " + email + " | Số dư: " + balance + " VNĐ");
    }

    // Getters & Setters
    public double getBalance() { return balance; }
    public void setBidStrategy(IBidStrategy bidStrategy) { this.bidStrategy = bidStrategy; }
}