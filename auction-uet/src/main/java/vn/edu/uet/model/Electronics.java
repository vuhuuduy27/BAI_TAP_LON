package vn.edu.uet.model;

public class Electronics extends Item {
    private int warrantyMonths; // Số tháng bảo hành

    public Electronics(String name, String description, double startingPrice, int warrantyMonths) {
        super(name, description, startingPrice);
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    @Override
    public void printInfo() {
        System.out.println("[Điện tử] " + name + " | Giá khởi điểm: " + startingPrice 
            + " VNĐ | Bảo hành: " + warrantyMonths + " tháng | Mô tả: " + description);
    }
}