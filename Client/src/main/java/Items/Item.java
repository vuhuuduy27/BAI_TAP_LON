package Items;

public class Item {
    private String id;
    private String name;
    private String description;
    private String SellerId;
    private long startingPrice;

    public Item(String id, String name, String description, String SellerId, long sP) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.SellerId = SellerId;
        this.startingPrice = sP;
    }

    public String getInfo() {
        return "Item info: \n id: " + id + "\n name: " + name + "\n description: "
                + description + "\n startingPrice: " + startingPrice + "\n Seller id: " + SellerId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getStartingPrice() {
        return startingPrice;
    }
}