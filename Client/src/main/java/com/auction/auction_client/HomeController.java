package com.auction.auction_client;

import entity.User;
import entity.Role;

import entityService.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class HomeController {

    @FXML
    private void loadFXML(String fxmlPath) {

        try {
            // Find FXML resource
            java.net.URL resource = getClass().getResource(fxmlPath);

            // Check whether the file exists
            if (resource == null) {
                System.out.println("Cannot find FXML resource: " + fxmlPath);
                return;
            }

            // Load FXML file
            FXMLLoader loader = new FXMLLoader(resource);
            Parent view = loader.load();

            // Clear old content
            contentArea.getChildren().clear();

            // Display new content
            contentArea.getChildren().add(view);

        } catch (IOException e) {
            System.out.println("Cannot load FXML: " + fxmlPath);
            e.printStackTrace();
        }
    }

    @FXML
    private Label lblWelcome;

    @FXML
    private Label lblRole;

    @FXML
    private VBox contentArea;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnAuctions;

    @FXML
    private Button btnMyProducts;

    @FXML
    private Button btnMyBids;

    @FXML
    private Button btnAccount;

    @FXML
    private Button btnLogout;

    @FXML
    public void initialize() {

        User user = UserSession.getCurrentUser();

        if (user == null) {
            lblWelcome.setText("Welcome!");
            lblRole.setText("Role: Unknown");
            return;
        }

        lblWelcome.setText("Welcome, " + user.getUserName() + "!");
        lblRole.setText("Role: " + user.getRole().name());

        setupSidebar(user.getRole());
    }

    private void showHomeContent() {
        User user = UserSession.getCurrentUser();

        contentArea.getChildren().clear();

        Label title = new Label("Welcome to Online Auction System");
        title.setStyle(
                "-fx-font-size: 22px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #243B55;"
        );

        Label description = new Label(
                "Welcome, " + user.getUserName() + "!\n" +
                        "Your role: " + user.getRole().name()
        );
        description.setStyle(
                "-fx-font-size: 15px;" +
                        "-fx-text-fill: #667085;"
        );

        contentArea.getChildren().addAll(title, description);
    }

    @FXML
    private void onHomeButtonClick() {
        showHomeContent();
    }

    private void setupSidebar(Role role) {

        // Ẩn tất cả các chức năng riêng biệt trước
        btnMyProducts.setVisible(false);
        btnMyProducts.setManaged(false);

        btnMyBids.setVisible(false);
        btnMyBids.setManaged(false);

        // Hiển thị chức năng theo Role
        if (role == Role.BIDDER) {
            btnMyBids.setVisible(true);
            btnMyBids.setManaged(true);

        } else if (role == Role.SELLER) {
            btnMyProducts.setVisible(true);
            btnMyProducts.setManaged(true);

        } else if (role == Role.ADMIN) {
            // Admin sử dụng các chức năng chung
        }
    }

    @FXML
    private void onLogoutButtonClick() {

        // End current user session
        UserSession.end();

        // Return to Login screen
        SceneNavigator.switchTo("/com/auction/auction_client/Login.fxml", btnLogout);
    }

    @FXML
    private void onMyProductsButtonClick() {
        loadFXML("/com/auction/auction_client/features/products/MyProducts.fxml");
    }

    @FXML
    private void onAuctionsButtonClick() {
        loadFXML("/com/auction/auction_client/features/auctions/Auctions.fxml");
    }

    @FXML
    private void onMyBidsButtonClick() {
        loadFXML("/com/auction/auction_client/features/bids/MyBids.fxml");
    }

    @FXML
    private void onAccountButtonClick() {
        loadFXML("/com/auction/auction_client/features/account/Account.fxml");
    }
}

