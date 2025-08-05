package org.example.service.implementation;

import org.example.entity.Brand;
import org.example.entity.Shareholder;
import org.example.entity.ShareholderBrand;
import org.example.repository.ShareholderBrandRepo;
import org.example.service.ShareholderBrandService;

public class ShareholderBrandServiceImpl implements ShareholderBrandService {
    private final ShareholderBrandRepo shareholderBrandRepo;

    public ShareholderBrandServiceImpl(ShareholderBrandRepo shareholderBrandRepo) {
        this.shareholderBrandRepo = shareholderBrandRepo;
    }

    @Override
    public int addRelation(Shareholder shareholder, Brand brand) {
        return shareholderBrandRepo.addRelation(shareholder,brand);
    }

    @Override
    public ShareholderBrand[] loadByShareholder(Shareholder shareholder) {
        return shareholderBrandRepo.loadByShareholder(shareholder);
    }

    @Override
    public ShareholderBrand[] loadByBrand(Brand brand) {
        return shareholderBrandRepo.loadByBrand(brand);
    }

    @Override
    public ShareholderBrand[] loadAll() {
        return shareholderBrandRepo.loadAll();
    }
}
