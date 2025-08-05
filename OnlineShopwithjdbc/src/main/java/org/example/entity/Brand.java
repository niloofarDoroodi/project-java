package org.example.entity;

public class Brand {
    private long brandID;
    private String brandName;
    private String website;
    private String description;

    public Brand() {
    }

    public Brand(String brandName, String website, String description) {
        this.brandName = brandName;
        this.website = website;
        this.description = description;
    }

    public Brand(long brandID, String brandName, String website, String description) {
        this(brandName,website,description);
        this.brandID = brandID;
    }

    public long getBrandID() {
        return brandID;
    }

    public void setBrandID(long brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Brand: \n" +
                "brandID: " + brandID +
                ", brandName: " + brandName +
                ", website: " + website +
                ", description:" + description + "\n";
    }
}
