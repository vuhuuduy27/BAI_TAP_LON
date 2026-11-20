
package com.auction.auction_client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import entityService.AuthService;
import javafx.scene.paint.Color;

public class LoginController {

    // Components in Scene Builder
    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblMessage;

    private AuthService authService;

    public void initialize() {
        authService = new AuthService();
    }

    // Handle when user clicks the Register link
    @FXML
    private void onRegisterLinkClick() {
        SceneNavigator.switchTo("Register.fxml", lblMessage);
    }

    // Handle when user clicks the Login button
    @FXML
    protected void onLoginButtonClick() {

        // Get user input
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();

        // Basic validation
        if (username.isEmpty() || password.isEmpty()) {
            if (lblMessage != null) {
                lblMessage.setText("Please enter full username and password");
                lblMessage.setTextFill(Color.RED);
            }

            System.out.println("Error: the input fields are empty!");
            return;
        }

        // Check login
        boolean success = authService.login(username, password);

        if (success) {
            lblMessage.setText("Login successful!");
            lblMessage.setTextFill(Color.GREEN);
        } else {
            lblMessage.setText("Username or password not correct!");
            lblMessage.setTextFill(Color.RED);
        }
    }
}
