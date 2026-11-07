public class Electronics extends Item {
    private String brand;
    private int WarrantyMonths;

    public Electronics(String id, String name, String description, long startingPrice, String SellerId, String brand, int WarrantyMonths) {
        super(id, name, description, SellerId);
        this.brand = brand;
        this.WarrantyMonths = WarrantyMonths;
    }

    public String getBrand() {
        return brand;
    }

    public int getWarrantyMonths() {
        return WarrantyMonths;
    }

    @Override
    public String getInfo () {
        return super.getInfo() + "-----Electronics-----" + "\nBrand: "+ brand +"\nWarrantyMonths: "+ WarrantyMonths;
    }
}