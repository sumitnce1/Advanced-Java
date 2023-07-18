package org.sumit.spring.secondjdbcspring.entity;

public class Product {
	int categoryId;
	int productId;
	String productName;
	String productDescription;
	double productPrice;
	String productImageUrl;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(int categoryId, int productId, String productName, String productDescription, double productPrice,
			String productImageUrl) {
		super();
		this.categoryId = categoryId;
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productImageUrl = productImageUrl;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	@Override
	public String toString() {
		return "Product [categoryId=" + categoryId + ", productId=" + productId + ", productName=" + productName
				+ ", productDescription=" + productDescription + ", productPrice=" + productPrice + ", productImageUrl="
				+ productImageUrl + "]";
	}
	
	
}
