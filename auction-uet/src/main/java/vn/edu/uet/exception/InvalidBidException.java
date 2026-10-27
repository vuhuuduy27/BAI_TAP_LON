package vn.edu.uet.exception;
//Bắt lỗi đặt giá sai quy định
public class InvalidBidException extends Exception {
    public InvalidBidException() {
        super();
    }

    public InvalidBidException(String message) {
        super(message);
    }
}