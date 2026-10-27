package vn.edu.uet.exception;
//Bắt lỗi tài khoản không đủ tiền
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException() {
        super();
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }
}