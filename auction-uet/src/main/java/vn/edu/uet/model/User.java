package vn.edu.uet.model;

import vn.edu.uet.exception.AuthenticationException;

public abstract class User extends Entity {
    protected String username;
    protected String password;
    protected String email;
    protected String role;

    public User(String username, String password, String email, String role) {
        super(); // Sinh id và createdAt từ Entity
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    // Kiểm tra mật khẩu đăng nhập
    public boolean checkPassword(String inputPassword) throws AuthenticationException {
        if (!this.password.equals(inputPassword)) {
            throw new AuthenticationException("Mật khẩu không chính xác!");
        }
        return true;
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
}