package vn.edu.uet.strategy;

import java.io.Serializable;
import vn.edu.uet.model.Auction;

public interface IBidStrategy extends Serializable {
    double calculateNextBid(double currentPrice);
    //nhận vào giá cao nhất hiện tại của phiên đấu giá và trả về mức giá tiếp theo sẽ đưa ra.
}