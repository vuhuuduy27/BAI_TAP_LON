
package com.auction.auction_client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import entityService.AdminInitializer;

public class LoginApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        // Initialize default Admin before showing Login screen
        AdminInitializer adminInitializer = new AdminInitializer();
        adminInitializer.initializeDefaultAdmin();

        FXMLLoader fxmlLoader = new FXMLLoader(
                LoginApplication.class.getResource("Login.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("OnlineAuctionSystem");
        stage.setScene(scene);
        stage.show();
    }
}
