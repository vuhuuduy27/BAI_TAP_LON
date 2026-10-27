package vn.edu.uet.exception;
//Bắt lỗi đăng ký trùng tên tk
public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super();
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}