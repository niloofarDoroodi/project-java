package org.example.repository;

import org.example.entity.Brand;
import org.example.entity.Shareholder;
import org.example.entity.ShareholderBrand;

public interface ShareholderBrandRepo {

    int addRelation(Shareholder shareholder,Brand brand);
    ShareholderBrand[] loadByShareholder(Shareholder shareholder);
    ShareholderBrand[] loadByBrand(Brand brand);
    ShareholderBrand[] loadAll();



}
