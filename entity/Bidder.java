package entity;

import java.util.HashSet;

public class Bidder extends User {
    private long balance;

    public Bidder(String userName, String password, String email, String fullName, long balance) {
        super(userName, password, email, fullName);
        this.balance = balance;
    }

    public long getBalance() {return this.balance;}

    public boolean canAfford(long amount) {
        if (amount <= balance) {
            return true;
        } else {return false;}
    }

    public void withdraw(long amount) {
        this.balance -= amount;
    }
    public void deposit(long amount) {
        this.balance += amount;
    }

    private HashSet<String> auctionList = new HashSet<>();
    public boolean findAuction(String auctionId) {
        if (auctionList.contains(auctionId)) {
            return true;
        }
        return false;
    }

    public boolean joinAuction(String auctionId) {
        if (findAuction(auctionId)) {
            return true;
        }
        return false;
    }

    public boolean leaveAuction() {
        return true;
    }

    @Override
    public Roles getRole() {return Roles.BIDDER;}

    @Override
    public String getDescription() {
        return "Bidder: " + super.getDescription() + "| Balance" + balance;
    }

}
