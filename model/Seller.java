package model;

import java.util.ArrayList;
import java.util.List;

public class Seller extends User {
    List<Item> items;

    public Seller(String username, String rawPassword, String email, String fullName){
        super(username, rawPassword, email, fullName);
        this.items = new ArrayList<>();
    }

    public boolean addItems(Item item){
        if (item == null) {
            throw new IllegalArgumentException("model.Item không được để trống");
        }
        for(Item i : items){
            if(i.getId().equals(item.getId())){
               return false;
            }
        }
        items.add(item);
        return true;
    }

    public boolean removeItem(String itemId) {
        if (itemId == null || itemId.isBlank()) {
            throw new IllegalArgumentException("model.Item ID không hợp lệ");
        }
        return items.removeIf(item -> item.getId().equals(itemId));// thay cho vòng lặp for phòng trường hợp khi đang duyệt mà xóa có thể gây lỗi
    }

    @Override
    public userRole getRole() {
        return userRole.SELLER;
    }

    @Override
    public String getDescription() {
        return "model.Seller: " + getUsername() + ", Number of items: " + items.size();
    }
}

