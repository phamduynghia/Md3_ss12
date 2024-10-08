package com.ra.model;



import java.util.Date;

public class Bill {
    private int billId;
    private String billCode;
    private boolean billType;
    private int accIdCreated;
    private Date created;
    private Date authDate;

    public Bill(int billId, String billCode, boolean billType, int accIdCreated, Date created, Date authDate) {
        this.billId = billId;
        this.billCode = billCode;
        this.billType = billType;
        this.accIdCreated = accIdCreated;
        this.created = created;
        this.authDate = authDate;
    }

    // Getters and Setters
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public boolean isBillType() {
        return billType;
    }

    public void setBillType(boolean billType) {
        this.billType = billType;
    }

    public int getAccIdCreated() {
        return accIdCreated;
    }

    public void setAccIdCreated(int accIdCreated) {
        this.accIdCreated = accIdCreated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getAuthDate() {
        return authDate;
    }

    public void setAuthDate(Date authDate) {
        this.authDate = authDate;
    }
}
