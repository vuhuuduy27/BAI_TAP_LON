package manager;

import model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemManager {
    private Map<String, Item> itemMap;
    private static ItemManager instance = null;
    private ItemManager(){
        itemMap = new HashMap<>();
    }
    public static ItemManager getInstance(){
        if(instance == null){
            instance = new ItemManager();
        }
        return instance;
    }

    public boolean addItem(Item item){
        if(item == null){
            return false;
        }
        if(itemMap.containsKey(item.getId())){
            return false;
        }
        itemMap.put(item.getId(), item);
        return true;
    }

    public Item getItemById(String id){
        return itemMap.get(id);
    }

    public boolean removeItem(String id) {
        if (id == null || id.isBlank()) {
            return false;
        }
        return itemMap.remove(id) != null;
    }

    public Map<String, Item> getItemMap(){
        return Map.copyOf(itemMap);
    }

    public List<Item> getItemBySeller(String sellerId){
        List<Item> itemList = new ArrayList<>();
        for(Item item : itemMap.values()){
            if(item.getSellerId().equals(sellerId)){
                itemList.add(item);
            }
        }
        return List.copyOf(itemList);
    }

}
