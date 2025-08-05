package org.example.service;

import org.example.entity.Brand;

public interface BrandService {
    String addNewBrand(Brand brand);

    int updateBrand(Brand brand);

    int deleteBrand(Brand brand);

    Brand[] loadAllBrands();

    int recordCounter();

    Brand isExist(String brandName);
}
