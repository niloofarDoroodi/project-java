package org.example.service.implementation;

import org.example.entity.Brand;
import org.example.repository.BrandRepo;
import org.example.service.BrandService;
import org.example.util.Validation;

public class BrandServiceImpl implements BrandService {

    private final BrandRepo brandRepo;

    public BrandServiceImpl(BrandRepo brandRepo) {
        this.brandRepo = brandRepo;
    }

    @Override
    public String  addNewBrand(Brand brand) {
        if (brandRepo.isExist(brand.getBrandName()) == null && Validation.isWebsite(brand.getWebsite())) {
            brandRepo.addNewBrand(brand);
            return "Brand successfully added.";
        } else {
            return "the brand is either already existed or invalid website.";
        }
    }

    @Override
    public int updateBrand(Brand brand) {
        int affectedRows = 0;
        if (brandRepo.isExist(brand.getBrandName()) != null && Validation.isWebsite(brand.getWebsite())) {
            affectedRows = brandRepo.updateBrand(brand);
        }
        return affectedRows;
    }

    @Override
    public int deleteBrand(Brand brand) {
        int affectedRows = 0;
        if (brandRepo.isExist(brand.getBrandName()) != null) {
            affectedRows = brandRepo.deleteBrand(brand);
        }
        return affectedRows;
    }

    @Override
    public Brand[] loadAllBrands() {
        return brandRepo.loadAllBrands();
    }

    @Override
    public int recordCounter() {
        return brandRepo.recordCounter();
    }

    @Override
    public Brand isExist(String brandName) {
        return brandRepo.isExist(brandName);
    }

}
