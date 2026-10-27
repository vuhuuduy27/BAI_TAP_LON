package vn.edu.uet.exception;
//Bắt lỗi khi cố đặt giá phiên đã đóng, hủy
public class AuctionClosedException extends Exception {
    public AuctionClosedException() {
        super();
    }

    public AuctionClosedException(String message) {
        super(message);
    }
}