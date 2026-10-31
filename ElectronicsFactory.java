public class ElectronicsFactory extends ItemFactory{
    private String brand;
    private int warrantyMonths;
    public ElectronicsFactory(String brand, int warrantyMonths){
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public Item createItem(String name, long startingPrice, String sellerId) {
        return new Electronics(name, startingPrice, sellerId, brand, warrantyMonths);
    }
}
