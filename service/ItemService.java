package service;

import manager.ItemManager;
import model.Item;
import model.Seller;
import model.User;

public class ItemService {
    private final ItemManager itemManager;
    private final AuthService authService;

    public ItemService(ItemManager itemManager, AuthService authService) {
        this.itemManager = itemManager;
        this.authService = authService;
    }

    // người bán đăng sản phẩm
    public boolean createItem(Item item){
        if(item == null){
            return false;
        }

        User currentUser = authService.getCurrentUser();
        if(!(currentUser instanceof Seller seller)){
            return false;
        }
        if(!seller.getId().equals(item.getSellerId())){
            return false;
        }
        if(!itemManager.addItem(item)){ // if này xem lại phương thức bên manager.ItemManager. Cập nhật ở manager.ItemManager
            return false;
        }
        seller.addItems(item); // thêm sản phẩm vào danh sách của người bán. Cập nhật ở model.Seller
        return true;
    }

    // Cập nhật tên sản phẩm
    public boolean updateItemName(String itemId, String newName){
        User currentUser = authService.getCurrentUser();
        if(!(currentUser instanceof Seller seller)){
            return false;
        }
        Item item = itemManager.getItemById(itemId);

        if(item == null){
            return false;
        }

        if(!seller.getId().equals(item.getSellerId())){
            return false;
        }

        item.updateName(newName);
        return true;
    }

    // người bán xóa sản phẩm
    public boolean deleteItem(String itemId) {
        User currentUser = authService.getCurrentUser();

        if (!(currentUser instanceof Seller seller)) {
            return false;
        }

        Item item = itemManager.getItemById(itemId);

        if (item == null) {
            return false;
        }

        if (!item.getSellerId().equals(seller.getId())) {
            return false;
        }

        seller.removeItem(itemId);
        itemManager.removeItem(itemId);

        return true;
    }

}