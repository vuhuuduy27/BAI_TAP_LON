package vn.edu.uet.model;

public class Vehicle extends Item {
    private String engineType; // Ví dụ: Động cơ Xăng, Điện, V8...

    public Vehicle(String name, String description, double startingPrice, String engineType) {
        super(name, description, startingPrice);
        this.engineType = engineType;
    }

    public String getEngineType() {
        return engineType;
    }

    @Override
    public void printInfo() {
        System.out.println("[Phương tiện] " + name + " | Động cơ: " + engineType 
            + " | Giá khởi điểm: " + startingPrice + " VNĐ");
    }
}