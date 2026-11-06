package entity;

import java.util.HashSet;

public class Bidder extends User {
    private long balance;
    private HashSet<String> auctionList = new HashSet<>();

    public Bidder(String userName, String password, String email, long balance) {
        super(userName, password, email);
        this.balance = balance;
    }

    public long getBalance() {return this.balance;}

    public boolean canAfford(long amount) {
        if (amount <= balance) {
            return true;
        } else {return false;}
    }

    public void withdraw(long amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
        }
    }
    public void deposit(long amount) {
        if (amount <= 0) {
            this.balance += amount;
        }
    }


    public boolean findAuction(String auctionId) {
        if (auctionList.contains(auctionId)) {
            return true;
        }
        return false;
    }

    public boolean joinAuction(String auctionId) {
        if (findAuction(auctionId)) {
            return auctionList.add(auctionId);
        }
        return false;
    }

    public boolean leaveAuction(String auctionId) {
        return auctionList.contains(auctionId);
    }

    @Override
    public Role getRole() {return Role.BIDDER;}

    @Override
    public String getDescription() {
        return "Bidder: " + super.getDescription() + "| Balance: " + balance;
    }

}
