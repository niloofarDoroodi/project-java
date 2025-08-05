package org.example.repository;

import org.example.entity.Shareholder;

public interface ShareholderRepo {

    int addNewShareholder(Shareholder shareholder);

    int updateShareholder(Shareholder shareholder);

    int deleteShareholder(Shareholder shareholder);

    Shareholder isExist(String shareholderName);

    Shareholder[] loadAllShareholders();
}
