package org.sumit.spring.secondjdbcspring.dao;

import java.util.List;

import org.sumit.spring.secondjdbcspring.entity.Category;

public interface CategoryDAO {
	boolean addCategory(int categoryId, String categoryName, String categoryDescription, String categoryImageUrl);
    List<Category> allCategory();
    Category getcategoryByCategoryId(int categoryId);
    boolean deleteCategory(int categoryId);
    boolean updateCategory(int categoryId, String categoryName, String categoryDescription, String categoryImageUrl);

}
