
package com.auction.auction_client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public class SceneNavigator {

    public static void switchTo(String fxmlFile, Node source) {
        try {
            URL resource = LoginApplication.class.getResource(fxmlFile);

            if (resource == null) {
                System.out.println("Cannot find FXML file: " + fxmlFile);
                return;
            }

            FXMLLoader loader = new FXMLLoader(resource);
            Parent root = loader.load();

            source.getScene().setRoot(root);

        } catch (IOException e) {
            System.out.println("Cannot load FXML file: " + fxmlFile);
            e.printStackTrace();
        }
    }
}
