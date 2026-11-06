package factory;

import model.Item;

public abstract class ItemFactory {

    public abstract Item createItem(String name, long startingPrice, String sellerId);
}
