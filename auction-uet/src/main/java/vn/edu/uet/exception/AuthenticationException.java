package vn.edu.uet.exception;
//Bắt lỗi đăng nhập sai tk/mk
public class AuthenticationException extends Exception {
    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String message) {
        super(message);
    }
}