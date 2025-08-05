package org.example.entity;

import java.util.Date;

public class Shareholder {
    private long shareholderID;
    private String shareholderName;
    private long phoneNumber;
    private long nationalCode;

    public Shareholder() {
    }

    public Shareholder(String shareholderName, long phoneNumber, long nationalCode) {
        this.shareholderName = shareholderName;
        this.phoneNumber = phoneNumber;
        this.nationalCode = nationalCode;
    }

    public Shareholder(long shareholderID, String shareholderName, long phoneNumber, long nationalCode) {
        this(shareholderName,phoneNumber,nationalCode);
        this.shareholderID = shareholderID;
    }

    public long getShareholderID() {
        return shareholderID;
    }

    public void setShareholderID(long shareholderID) {
        this.shareholderID = shareholderID;
    }

    public String getShareholderName() {
        return shareholderName;
    }

    public void setShareholderName(String shareholderName) {
        this.shareholderName = shareholderName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(long nationalCode) {
        this.nationalCode = nationalCode;
    }

    @Override
    public String toString() {
        return "Shareholder: \n" +
                "shareholderName: " + shareholderName +
                ", phoneNumber: " + phoneNumber +
                ", nationalCode: " + nationalCode;
    }
}
