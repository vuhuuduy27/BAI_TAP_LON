import java.util.ArrayList;
import java.util.List;

public class Seller extends User{
    List<Item> items;

    public Seller(String username, String rawPassword, String email, String fullName){
        super(username, rawPassword, email, fullName);
        this.items = new ArrayList<>();
    }

    public void addItems(Item item){
        items.add(item);
    }

    public void updateItem(Item item){

    }

    public void removeItem(String itemId){
        for(Item item : items){
            if(item.getId().equals(itemId)){
                items.remove(item);
                break;
            }
        }
    }
}
