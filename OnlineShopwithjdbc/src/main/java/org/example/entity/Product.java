package org.example.entity;

import java.util.Date;

public class Product {
    private long productID;
    private String productName;
    private Date createdDate;
    private long categoryID;
    private long brandID;

    public Product() {}

    public Product(String productName, Date createdDate, long categoryID, long brandID) {
        this.productName = productName;
        this.createdDate = createdDate;
        this.categoryID = categoryID;
        this.brandID = brandID;
    }

    public Product(long productID, String productName, Date createdDate, long categoryID, long brandID) {
        this(productName,createdDate,categoryID,brandID);
        this.productID = productID;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }

    public long getBrandID() {
        return brandID;
    }

    public void setBrandID(long brandID) {
        this.brandID = brandID;
    }

    @Override
    public String toString() {
        return "Product:\n" +
                "productID: " + productID +
                ", productName: " + productName +
                ", createdDate: " + createdDate +
                ", categoryID: " + categoryID +
                ", brandID: " + brandID + "\n";
    }
}
