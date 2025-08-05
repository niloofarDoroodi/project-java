package org.example.repository;

import org.example.entity.Brand;

public interface BrandRepo {
    void addNewBrand(Brand brand);

    int updateBrand(Brand brand);

    int deleteBrand(Brand brand);

    Brand isExist(String brandName);

    Brand[] loadAllBrands();

    int recordCounter();
}
