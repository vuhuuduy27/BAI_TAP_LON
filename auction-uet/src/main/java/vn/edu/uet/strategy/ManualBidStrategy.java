package vn.edu.uet.strategy;
//Xử lý trường hợp người dùng muốn tự tay nhập con số cụ thể trên màn hình giao diện.
public class ManualBidStrategy implements IBidStrategy {
    private double userPrice;
    //Nhận vào số tiền người dùng nhập
    public ManualBidStrategy(double userPrice) {
        this.userPrice = userPrice;
    }

    @Override
    public double calculateNextBid(double currentPrice) {
        return userPrice;
    }
}