package org.sumit.spring.secondjdbcspring.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.sumit.spring.secondjdbcspring.entity.Product;

@Repository
@Qualifier("productDAO")
public class ProductDAOImpl implements ProductDAO 
{
	@Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    Environment env;

    RowMapper<Product> rowMapper;

    String sqlAddProduct;
    String sqlDeleteProduct;
    String sqlUpdateProduct;
    String sqlAllProduct;
    String sqlProductByProductId;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @PostConstruct
    public void initialize() {
    	sqlAddProduct = env.getProperty("sql.addProduct");
    	sqlDeleteProduct = env.getProperty("sql.deleteProduct");
    	sqlUpdateProduct= env.getProperty("sql.updateProduct");
    	sqlAllProduct = env.getProperty("sql.allProduct");
    	sqlProductByProductId = env.getProperty("sql.productByProductId");

        rowMapper = (result, rowNo) -> {
        	Product objProduct = new Product();
        	objProduct.setCategoryId(result.getInt(1));
        	objProduct.setProductId(result.getInt(2));
        	objProduct.setProductName(result.getString(3));
        	objProduct.setProductDescription(result.getString(4));
        	objProduct.setProductPrice(result.getDouble(5));
        	objProduct.setProductImageUrl(result.getString(6));
            return objProduct;
        };
    }

	@Override
	public boolean addProduct(int categoryId, int productId, String productName, String productDescription,
			double productPrice, String productImageUrl) {
		int count = jdbcTemplate.update(sqlAddProduct, categoryId, productId, productName, productDescription, productPrice, productImageUrl);
        return count > 0;
	}

	@Override
	public List<Product> allProduct() {
		return jdbcTemplate.query(sqlAllProduct, rowMapper);
	}

	@Override
	public Product getproductByProductId(int productId) {
//		return jdbcTemplate.queryForObject(sqlProductByProductId, rowMapper, productId);
		List<Product> products = jdbcTemplate.query(sqlProductByProductId, rowMapper, productId);
    	if (!products.isEmpty()) {
            return products.get(0);
        } else {
            return null;
        }
	}

	@Override
	public boolean deleteProduct(int productId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProduct(int categoryId, int productId, String productName, String productDescription,
			double productPrice, String productImageUrl) {
		// TODO Auto-generated method stub
		return false;
	}

}
