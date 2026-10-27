package vn.edu.uet.model;

public abstract class Item extends Entity {
    protected String name;
    protected String description;
    protected double startingPrice;

    public Item(String name, String description, double startingPrice) {
        super(); // Tự động sinh ID và thời gian khởi tạo từ Entity
        this.name = name;
        this.description = description;
        this.startingPrice = startingPrice;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getStartingPrice() {
        return startingPrice;
    }
}