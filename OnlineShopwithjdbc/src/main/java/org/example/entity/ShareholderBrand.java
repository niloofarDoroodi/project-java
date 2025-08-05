package org.example.entity;

public class ShareholderBrand {
    private Shareholder shareholder;
    private Brand brand;

    public ShareholderBrand() {
    }

    public ShareholderBrand(Shareholder shareholder, Brand brand) {
        this.shareholder = shareholder;
        this.brand = brand;
    }

    public Shareholder getShareholder() {
        return shareholder;
    }

    public void setShareholder(Shareholder shareholder) {
        this.shareholder = shareholder;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "BrandID: " + brand.getBrandID() +
                ", brandName: " + brand.getBrandName() +
                ", website: " + brand.getWebsite() +
                ", description:" + brand.getDescription() + "\n" +
                "ShareholderName: " + shareholder.getShareholderName() +
                ", phoneNumber: " + shareholder.getPhoneNumber() +
                ", nationalCode: " + shareholder.getNationalCode();
    }
}
