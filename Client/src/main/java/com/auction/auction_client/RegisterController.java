
package com.auction.auction_client;

import entity.Role;
import entityService.AuthService;
import entityService.Validator;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class RegisterController {

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFullName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private ComboBox<Role> cbRole;

    @FXML
    private Label lblMessage;

    private AuthService authService;

    @FXML
    public void initialize() {
        authService = new AuthService();

        // Chỉ cho phép đăng ký Bidder và Seller
        cbRole.getItems().addAll(Role.BIDDER, Role.SELLER);
    }

    private void showMessage(String message, Color color) {
        lblMessage.setText(message);
        lblMessage.setTextFill(color);
    }

    @FXML
    private void onRegisterButtonClick() {

        String username = txtUsername.getText().trim();
        String email = txtEmail.getText().trim();
        String fullName = txtFullName.getText().trim();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();
        Role role = cbRole.getValue();

        // 1. Kiểm tra trường trống
        if (username.isEmpty() || email.isEmpty()
                || fullName.isEmpty() || password.isEmpty()
                || confirmPassword.isEmpty() || role == null) {

            showMessage("Please fill in all fields!", Color.RED);
            return;
        }

        // 2. Kiểm tra username
        if (!Validator.validateUsername(username)) {
            showMessage(
                    "Username must be 4-20 characters and contain only letters, numbers or underscore!",
                    Color.RED
            );
            return;
        }

        // 3. Kiểm tra email
        if (!Validator.validateEmail(email)) {
            showMessage("Invalid email format!", Color.RED);
            return;
        }

        // 4. Kiểm tra password
        if (!Validator.validatePassword(password)) {
            showMessage(
                    "Password must have at least 8 characters, including uppercase, lowercase and a number!",
                    Color.RED
            );
            return;
        }

        // 5. Xác nhận password
        if (!password.equals(confirmPassword)) {
            showMessage("Passwords do not match!", Color.RED);
            return;
        }

        // 6. Gọi AuthService
        boolean success = authService.register(
                username, password, email, fullName, role
        );

        if (success) {
            showMessage("Registration successful! Please login.", Color.GREEN);

            // Xóa dữ liệu mật khẩu
            txtPassword.clear();
            txtConfirmPassword.clear();

        } else {
            showMessage(
                    "Username or email already exists, or registration failed!",
                    Color.RED
            );
        }
    }

    @FXML
    private void onBackToLoginClick() {
        SceneNavigator.switchTo("Login.fxml", lblMessage);
    }
}
