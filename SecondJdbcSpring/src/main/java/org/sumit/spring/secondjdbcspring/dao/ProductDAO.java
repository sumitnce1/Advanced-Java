package org.sumit.spring.secondjdbcspring.dao;

import java.util.List;

import org.sumit.spring.secondjdbcspring.entity.Product;

public interface ProductDAO {
	boolean addProduct(int categoryId, int productId, String productName, String productDescription, double productPrice, String productImageUrl);
    List<Product> allProduct();
    Product getproductByProductId(int productId);
    boolean deleteProduct(int productId);
    boolean updateProduct(int categoryId, int productId, String productName, String productDescription, double productPrice, String productImageUrl);

}
