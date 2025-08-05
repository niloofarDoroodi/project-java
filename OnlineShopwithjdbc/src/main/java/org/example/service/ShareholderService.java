package org.example.service;

import org.example.entity.Shareholder;

public interface ShareholderService {
    int addNewShareholder(Shareholder shareholder);

    int updateShareholder(Shareholder shareholder);

    int deleteShareholder(Shareholder shareholder);

    Shareholder isExist(String shareholderName);

    Shareholder[] loadAllShareholders();
}
