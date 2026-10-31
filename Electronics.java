public class Electronics extends Item{
    private String brand;
    private int warranty;

    public Electronics(String name, long startingPrice, String sellerId, String brand, int warranty){
        super(name, startingPrice, sellerId);
        this.brand = brand;
        this.warranty = warranty;
    }

    public int getWarranty() {
        return warranty;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String getInfo() {
        return "Electronics: " + super.getInfo() + " - " + brand + " - " + warranty;
    }
}
