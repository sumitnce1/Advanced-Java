package org.sumit.cart;

import java.util.ArrayList;
import java.util.Iterator;
import org.sumit.servlets.CartItem;

public class ProductCart implements Cart {
    private ArrayList<CartItem> items = new ArrayList<>();

    public void add(CartItem item) {
        items.add(item);
        System.out.println("Product added");
    }

    public void remove(int categoryId, int productId) {
        System.out.println("Product removed");
    }

    public Iterator<CartItem> allItems() {
        System.out.println("Product listed");
        return items.iterator();
    }
}
