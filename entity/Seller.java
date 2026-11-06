package entity;

import java.util.List;
import java.util.ArrayList;

public class Seller extends User  {
    private List<Item> itemsList;

    public Seller(String userName, String password, String email) {
        super(userName, password, email);
        this.itemsList = new ArrayList<>();
    }

    public void addItem(Item item) {
        this.itemsList.add(item);
    }

    public void updateItem(Item item) {
        for (int i =0; i < this.itemsList.size();i++) {
            Item currentItem = this.itemsList.get(i);
            if (currentItem.getId().equals(item.getId())) {
                this.itemsList.set(i, item);
                break;
            }
        }
    }

    public void removeItem(Item item) {
        for (int i =0; i < this.itemsList.size();i++) {
            Item currentItem = this.itemsList.get(i);
            if (currentItem.getId().equals(item.getId())) {
                this.itemsList.remove(i);
                break;
            }
        }
    }

    @Override
    public Role getRole() {
        return Role.SELLER;
    }

    @Override
    public String getDescription() {
        return "Seller: " + super.getDescription() + "| So luong items:" + this.itemsList.size();
    }

}
