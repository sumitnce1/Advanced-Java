package org.sumit.cart;

import java.util.Iterator;
import org.sumit.servlets.CartItem;

public interface Cart {
    public void add(CartItem item);
    public void remove(int categoryId, int productId);
    public Iterator<CartItem> allItems();
}