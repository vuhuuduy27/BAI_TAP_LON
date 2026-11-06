package model;

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

    public void updateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Tên sản phẩm không hợp lệ");
        }
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Item item) {
            return this.getId().equals(item.getId());
        }
        return false;
    }
}
