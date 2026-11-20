module com.auction.auction_client {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.auction.auction_client to javafx.fxml;
    exports com.auction.auction_client;
    exports entity;
    exports entityService;
}