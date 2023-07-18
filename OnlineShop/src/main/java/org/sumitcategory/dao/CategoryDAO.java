package org.sumitcategory.dao;

import java.util.ArrayList;
import org.sumit.pojo.Category;

public interface CategoryDAO {
    public default boolean addCategory(String categoryName, String categoryDescription, String categoryImageUrl) {
        return true;
    }

    public default boolean updateCategory(int categoryId, String categoryName, String categoryDescription, String categoryImageUrl) {
        return true;
    }

    public default boolean deleteCategory(int categoryId) {
        return true;
    }

    public ArrayList<Category> allCategories();
}
