public class Vehicle extends Item{
    private String brand;
    private int modelYear;
    private int mileage;

    public Vehicle(String name, long startingPrice, String sellerId, String brand, int modelYear, int mileage){
        super(name, startingPrice, sellerId);
        this.brand = brand;
        this.modelYear = modelYear;
        this.mileage = mileage;
    }

    public String getBrand() {
        return brand;
    }

    public int getModelYear() {
        return modelYear;
    }

    public int getMileage() {
        return mileage;
    }

    @Override
    public String getInfo() {
        return "Vehicle: " + super.getInfo() + " - " + brand + " - " + modelYear + " - " + mileage;
    }
}

