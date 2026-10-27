package vn.edu.uet.strategy;

public class AutoBidStrategy implements IBidStrategy {
    private double maxBid;    // Giá trần tối đa chấp nhận đặt
    private double increment; // Bước giá tự động tăng thêm khi có đối thủ ghi giá đè lên

    public AutoBidStrategy(double maxBid, double increment) {
        this.maxBid = maxBid;
        this.increment = increment;
    }

    @Override
    public double calculateNextBid(double currentPrice) {
        double nextBid = currentPrice + increment;
        //Nếu nextBid chưa vượt quá giá trần maxBid, hệ thống sẽ tự động đặt mức nextBid này để đòi lại vị trí dẫn đầu.
        if (nextBid > maxBid) {
            return maxBid;
        }//Nếu vượt quá maxBid, hệ thống dừng lại ở mức trần tối đa maxBid để tránh làm người dùng chi vượt ngân sách.
        return nextBid;
    }
}