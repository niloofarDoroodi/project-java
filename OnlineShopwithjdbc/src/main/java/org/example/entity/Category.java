package org.example.entity;

public class Category {
    private long categoryID;
    private String categoryName;
    private String description;

    public Category() {
    }

    public Category(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    public Category(long categoryID, String categoryName, String description) {
        this(categoryName,description);
        this.categoryID = categoryID;
    }

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category \n" +
                "categoryID: " + categoryID +
                ", categoryName: " + categoryName +
                ", description: " + description + "\n";
    }
}
