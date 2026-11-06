package entity;


public class Seller extends User  {
    private List<Item> itemsList;

    public Seller(String userName, String password, String email, String fullName) {
        super(userName, password, email, fullName);
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
    public Roles getRole() {
        return Roles.SELLER;
    }

    @Override
    public String getDEscription() {
        return "Seller: " + super.getDescription() + "| So luong items:" + this.itemList.size();
    }

}
