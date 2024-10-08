package com.ra.model;



public class Account {
    private Integer accId;
    private String userName;
    private String password;
    private boolean permission;
    private boolean accStatus;

    public Account() {
    }

    public Account(Integer accId, String userName, String password, boolean permission, boolean accStatus) {
        this.accId = accId;
        this.userName = userName;
        this.password = password;
        this.permission = permission;
        this.accStatus = accStatus;
    }

    // Getters and Setters
    public Integer getAccId() {
        return accId;
    }

    public void setAccId(Integer accId) {
        this.accId = accId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public boolean isAccStatus() {
        return accStatus;
    }

    public void setAccStatus(boolean accStatus) {
        this.accStatus = accStatus;
    }
}
