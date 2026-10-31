import java.util.UUID;

public abstract class Item {
    private String id;
    private String name;
    private String description;
    private long startingPrice;
    private String sellerId;

    public Item(String name, long startingPrice, String sellerId){
        this.id = UUID.randomUUID().toString();
        this.startingPrice = startingPrice;
        this.name = name;
        this.sellerId = sellerId;

    }

    public String getId(){
        return id;
    }

    public long getStartingPrice() {
        return startingPrice;
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getInfo(){
        return id + " - " + name + " - " + sellerId;
    }
}
