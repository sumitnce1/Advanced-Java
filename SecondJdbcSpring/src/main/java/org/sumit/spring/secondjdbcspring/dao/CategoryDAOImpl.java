package org.sumit.spring.secondjdbcspring.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.sumit.spring.secondjdbcspring.entity.Category;

@Repository
@Qualifier("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO 
{
	@Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    Environment env;

    RowMapper<Category> rowMapper;

    String sqlAddCategory;
    String sqlDeleteCategory;
    String sqlUpdateCategory;
    String sqlAllCategory;
    String sqlCategoryByCategoryId;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @PostConstruct
    public void initialize() {
    	sqlAddCategory = env.getProperty("sql.addCategory");
    	sqlDeleteCategory = env.getProperty("sql.deleteCategory");
    	sqlUpdateCategory = env.getProperty("sql.updateCategory");
    	sqlAllCategory = env.getProperty("sql.allCategory");
    	sqlCategoryByCategoryId = env.getProperty("sql.categoryByCategoryId");

        rowMapper = (result, rowNo) -> {
        	Category objCategory = new Category();
        	objCategory.setCategoryId(result.getInt(1));
        	objCategory.setCategoryName(result.getString(2));
        	objCategory.setCategoryDescription(result.getString(3));
        	objCategory.setCategoryImageUrl(result.getString(4));
            return objCategory;
        };
    }

	@Override
	public boolean addCategory(int categoryId, String categoryName, String categoryDescription,
			String categoryImageUrl) {
		int count = jdbcTemplate.update(sqlAddCategory, categoryId, categoryName, categoryDescription, categoryImageUrl);
        return count > 0;
	}

	@Override
	public List<Category> allCategory() {
		 return jdbcTemplate.query(sqlAllCategory, rowMapper);
	}

	@Override
	public Category getcategoryByCategoryId(int categoryId) {
		return jdbcTemplate.queryForObject(sqlCategoryByCategoryId, rowMapper, categoryId);
	}

	@Override
	public boolean deleteCategory(int categoryId) {
		int count = jdbcTemplate.update(sqlDeleteCategory, categoryId);
	    return count > 0;
	}

	@Override
	public boolean updateCategory(int categoryId, String categoryName, String categoryDescription, String categoryImageUrl) {
	    int count = jdbcTemplate.update(sqlUpdateCategory, categoryName, categoryDescription, categoryImageUrl, categoryId);
	    return count > 0;
	}

}
