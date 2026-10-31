public enum AuctionStatus {
    OPEN,       // Vừa tạo, chưa đến giờ đấu giá
    RUNNING,    // Đang trong thời gian đấu giá
    FINISHED,   // Đã hết giờ đấu giá
    PAID,       // Người thắng đã thanh toán
    CANCELED    // Phiên bị hủy bởi Admin hoặc Seller
}
