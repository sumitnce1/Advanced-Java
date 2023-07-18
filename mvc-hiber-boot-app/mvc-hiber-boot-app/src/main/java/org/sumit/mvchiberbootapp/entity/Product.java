package org.sumit.mvchiberbootapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="products_1234")
@NamedQueries({
	@NamedQuery(name="allProducts", query="select OBJECT(oProduct) from Product oProduct where oProduct.prodId.categoryId=?1")
})
public class Product {
    @EmbeddedId
    ProductPrimaryKey prodId;

    @Column(name="productname")
    String productName;

    @Column(name="productdescription")
    String productDescription;

    @Column(name="productprice")
    float productPrice;
    
    @Column(name="productimageurl")
    String productImageUrl;

    public Product() {
        // TODO Auto-generated constructor stub
    }

    public Product(ProductPrimaryKey prodId, String productName, String productDescription, float productPrice, String productImageUrl) {
        super();
        this.prodId = prodId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productImageUrl = productImageUrl;
    }

    public ProductPrimaryKey getProdId() {
        return prodId;
    }

    public void setProdId(ProductPrimaryKey prodId) {
        this.prodId = prodId;
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

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }
}
