public class VehicleFactory extends ItemFactory{
    private String brand;
    private int modelYear;
    private int mileage;
    public VehicleFactory(String brand, int modelYear, int mileage){
        this.brand = brand;
        this.modelYear = modelYear;
        this.mileage = mileage;
    }

    @Override
    public Item createItem(String name, long startingPrice, String sellerId) {
        return new Vehicle(name, startingPrice, sellerId, brand, modelYear, mileage);
    }
}
