package org.sumit.spring.boot.competeapp.services;

import java.util.List;

import org.sumit.spring.boot.competeapp.dto.CategoryDTO;

public interface CategoryService {
	boolean addNewCategoy(CategoryDTO categoryDTO);
	List<CategoryDTO> allCategories();
	CategoryDTO getCategoryById(int categoryId);
}
