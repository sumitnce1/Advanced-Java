package org.sumitshop.hibernate.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductPrimaryKey implements Serializable {
    private static final long serialVersionUID = -5210134876593075409L;

    @Column(name="categoryId")
    Integer categoryId;

    @Column(name="productId")
    int productId;

    public ProductPrimaryKey() {
    }

    public ProductPrimaryKey(Integer categoryId, int productId) {
        super();
        this.categoryId = categoryId;
        this.productId = productId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
