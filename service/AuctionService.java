package service;

import manager.AuctionManager;
import manager.ItemManager;
import model.*;

import java.time.LocalDateTime;

public class AuctionService {
    AuctionManager auctionManager;
    AuthService authService;
    ItemManager itemManager;// để thiết lập điều kiện với item
    public AuctionService(AuctionManager auctionManager, AuthService authService, ItemManager itemManager){
        this.auctionManager = auctionManager;
        this.authService = authService;
        this.itemManager = itemManager;
    }

    //kiểm tra và Gọi các thao tác nghiệp vụ cho phiên đấu giá
    //kiểm tra và gọi Mở phiên
    public boolean startAuction(String auctionId){
        if(auctionId == null || auctionId.isBlank()){
            return false;
        }
        Auction auction = auctionManager.getAuctionById(auctionId);
        if(auction == null){
            return false;
        }
        return auction.start(LocalDateTime.now());
    }

    //kiểm tra và Đóng phiên
    public boolean finishAuction(String auctionId){
        if(auctionId == null || auctionId.isBlank()){
            return false;
        }
        Auction auction = auctionManager.getAuctionById(auctionId);
        if(auction == null){
            return false;
        }

        return auction.finish(LocalDateTime.now());
    }

    //kiểm tra và gọi Hủy phiên
    public boolean cancelAuction(String auctionId){
        if(auctionId == null || auctionId.isBlank()){
            return false;
        }
        Auction auction = auctionManager.getAuctionById(auctionId);
        if(auction == null){
            return false;
        }
        return auction.cancel(LocalDateTime.now());
    }

    //Tạo phiên đấu giá
    public boolean createAuction(Item item, LocalDateTime startTime, LocalDateTime endTime){
        if(item == null || startTime == null || endTime == null){
            return false;
        }

        User currentUser = authService.getCurrentUser();
        if(!(currentUser instanceof Seller seller)){// Người dùng hiện tại có phải model.Seller không?
            return false;
        }

        if(itemManager.getItemById(item.getId()) == null){//model.Item có thực sự tồn tại trong manager.ItemManager không?
            return false;
        }

        if(!item.getSellerId().equals(seller.getId())) { //model.Item có thuộc model.Seller đang đăng nhập không?
            return false;
        }
        if(auctionManager.getAuctionByItem(item) != null){ //model.Item đã từng được tạo phiên đấu giá chưa?
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        if(!startTime.isBefore(endTime) || startTime.isBefore(now)){
            return false;
        }

        Auction auction = new Auction(item, startTime, endTime);
        return auctionManager.addAuction(auction);
    }

    // Kiểm tra đặt giá hợp lệ và đặt giá
    public boolean placeBid(String auctionId, long amount){
        if (auctionId == null || auctionId.isBlank() || amount <= 0) { //.isBlank(): tránh chuỗi rỗng
            return false;
        }
        User currentUser = authService.getCurrentUser();
        if(!(currentUser instanceof Bidder bidder)){
            return false;
        }

        Auction auction = auctionManager.getAuctionById(auctionId);
        if (auction == null) {
            return false;
        }

        Bid bid = new Bid(auctionId, bidder.getId(), amount);
        return auction.addBid(bid,LocalDateTime.now());
    }
}
