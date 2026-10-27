package vn.edu.uet;

import vn.edu.uet.factory.ItemFactory;
import vn.edu.uet.manager.AuctionManager;
import vn.edu.uet.manager.DataManager;
import vn.edu.uet.model.*;
import vn.edu.uet.status.AuctionStatus;
import vn.edu.uet.strategy.AutoBidStrategy;
import vn.edu.uet.strategy.ManualBidStrategy;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== KHOI DONG HE THONG DAU GIA AUCTION UET ===");

        // 1. Nap du lieu cu neu co
        DataManager.loadData();
        AuctionManager manager = AuctionManager.getInstance();

        try {
            // 2. Dang ky nguoi dung moi (Neu chua co)
            Seller seller = new Seller("seller_uet", "123456", "seller@uet.vnu.edu.vn");
            Bidder bidder1 = new Bidder("nam_uettor", "123456", "nam@gmail.com", 50000000);
            Bidder bidder2 = new Bidder("ha_uettor", "123456", "ha@gmail.com", 60000000);

            try {
                manager.registerUser(seller);
                manager.registerUser(bidder1);
                manager.registerUser(bidder2);
            } catch (Exception e) {
                System.out.println("[Luu y]: " + e.getMessage());
            }

            // 3. Dang nhap kiem tra
            User loggedSeller = manager.login("seller_uet", "123456");

            // 4. Tao san pham qua ItemFactory
            Item laptop = ItemFactory.createItem(
                "ELECTRONICS", 
                "Laptop Dell G15 5530", 
                "Intel i7-13650HX, RTX 3050 6GB, 32GB RAM", 
                20000000, 
                24 // 24 thang bao hanh
            );

            // 5. Seller mo phien dau gia
            LocalDateTime now = LocalDateTime.now();
            Auction auction = ((Seller) loggedSeller).createAuction(laptop, now, now.plusMinutes(10));
            auction.setStatus(AuctionStatus.RUNNING); // Kich hoat phien chay

            manager.startNewAuction(auction);

            // 6. Dang ky Observer nhan thong bao Realtime
            auction.addObserver(bidder1);
            auction.addObserver(bidder2);

            System.out.println("\n--- BAT DAU PHIEN DAU GIA ---");

            // 7. Bidder 1 dat gia thu cong (Manual Strategy)
            bidder1.setBidStrategy(new ManualBidStrategy(21000000));
            bidder1.autoPlaceBid(auction);

            // 8. Bidder 2 dat gia tu dong (AutoBid Strategy: Max 25tr, tang 1tr moi lan)
            bidder2.setBidStrategy(new AutoBidStrategy(25000000, 1000000));
            bidder2.autoPlaceBid(auction);

            // 9. Gia han Anti-sniping kiem tra
            auction.checkAndExtendEndTime();

            // 10. Dong phien dau gia va ket xuat nguoi thang
            System.out.println("\n--- KET THUC PHIEN DAU GIA ---");
            auction.closeAuction();

            // 11. Ghi nhan ket qua va luu file auctions.dat
            System.out.println("\n--- LUU DU LIEU HE THONG ---");
            DataManager.saveData();

        } catch (Exception e) {
            System.err.println("Loi trong qua trinh kiem thu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}