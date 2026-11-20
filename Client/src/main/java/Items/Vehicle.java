package Items;
public class Vehicle extends Item {
    String brand;
    int modelYear;
    int mileage;

    public Vehicle(String id, String name, String description, String SellerId, long startingPrice, String bnd, int modelYear, int mileage) {
        super(id, name, description, SellerId,startingPrice);
        this.brand = bnd;
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
    public String getInfo () {
        return super.getInfo() + "-----Vehicle-----" + "\nmodelYear: "+ modelYear +"\nMileage: "+ mileage;
    }
}