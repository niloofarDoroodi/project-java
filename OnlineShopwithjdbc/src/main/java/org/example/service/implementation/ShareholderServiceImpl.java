package org.example.service.implementation;

import org.example.entity.Shareholder;
import org.example.repository.ShareholderRepo;
import org.example.service.ShareholderService;
import org.example.util.Validation;

public class ShareholderServiceImpl implements ShareholderService {
    private final ShareholderRepo shareholderRepo;

    public ShareholderServiceImpl(ShareholderRepo shareholderRepo) {
        this.shareholderRepo = shareholderRepo;
    }

    @Override
    public int addNewShareholder(Shareholder shareholder) {
        int added = 0;
        if (shareholderRepo.isExist(shareholder.getShareholderName()) == null &&
                Validation.validPhoneNumber(String.valueOf(shareholder.getPhoneNumber())) &&
                Validation.validNationalCode(String.valueOf(shareholder.getNationalCode()))){
            added = shareholderRepo.addNewShareholder(shareholder);
        }
        return added;
    }

    @Override
    public int updateShareholder(Shareholder shareholder) {
        int edited = 0;
        if (shareholderRepo.isExist(shareholder.getShareholderName()) != null &&
                Validation.validPhoneNumber(String.valueOf(shareholder.getPhoneNumber())) &&
                Validation.validNationalCode(String.valueOf(shareholder.getNationalCode()))){
            edited = shareholderRepo.addNewShareholder(shareholder);
        }
        return edited;
    }

    @Override
    public int deleteShareholder(Shareholder shareholder) {
        int edited = 0;
        if (shareholderRepo.isExist(shareholder.getShareholderName()) != null &&
                Validation.validPhoneNumber(String.valueOf(shareholder.getPhoneNumber())) &&
                Validation.validNationalCode(String.valueOf(shareholder.getNationalCode()))){
            edited = shareholderRepo.addNewShareholder(shareholder);
        }
        return edited;
    }

    @Override
    public Shareholder isExist(String shareholderName) {
        return shareholderRepo.isExist(shareholderName);
    }

    @Override
    public Shareholder[] loadAllShareholders() {
        return shareholderRepo.loadAllShareholders();
    }
}
