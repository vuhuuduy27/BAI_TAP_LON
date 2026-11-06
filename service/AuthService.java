package service;

import manager.UserManager;
import model.Bidder;
import model.Seller;
import model.User;
import model.userRole;

public class AuthService {
    private UserManager userManager;
    private User currentUser;

    public AuthService(UserManager userManager){
        this.userManager = userManager;
        this.currentUser = null;
    }

    //Đăng nhập
    public boolean login(String username, String password){
        if(username == null || password == null){
            return false;
        }
        User user = userManager.getUserByUsername(username);
        if(user == null || !user.isActive()){
            return false;
        }
        if(!user.checkPassword(password)){
            return false;
        }
        currentUser = user;
        return true;
    }

    //Đăng xuất
    public void logout(){
        currentUser = null;
    }

    //Đăng ký
    public User register(String username, String rawPassword, String email, String fullName, userRole role){
        if (username == null || username.isBlank() || rawPassword == null || rawPassword.isBlank()
                || email == null || email.isBlank() || fullName == null || fullName.isBlank() || role == null) {
            return null;
        }

        if (userManager.getUserByUsername(username) != null) {
            return null;
        }
        User user;
        if(role == userRole.BIDDER){
            user = new Bidder(username, rawPassword, email, fullName);
        }
        else if(role == userRole.SELLER){
            user = new Seller(username, rawPassword, email, fullName);
        }
        else{
            user = null;
        }
        userManager.addUser(user); // sau khi đăng ký xong thì thêm tài khoản vào phần quản lý tài khoản manager.UserManager
        return user;
    }

    // lấy tài khoản đang đăng nhập
    public User getCurrentUser(){
        return currentUser;
    }
}
