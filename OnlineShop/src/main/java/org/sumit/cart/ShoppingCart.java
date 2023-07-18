package org.sumit.cart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import org.sumit.servlets.CartItem;

public class ShoppingCart implements Cart {
    ArrayList<CartItem> items = new ArrayList<>(); // Type Inference

    public void add(CartItem item) {
        items.add(item);
    }

    public void remove(int categoryId, int productId) {
        ListIterator<CartItem> allProducts = items.listIterator();

        while (allProducts.hasNext()) {
            CartItem item = allProducts.next();
            if (item.getProductId() == productId && item.getCategoryId() == categoryId) {
                allProducts.remove();
            }
        }
    }

    public Iterator<CartItem> allItems() {
        return items.iterator();
    }
}
