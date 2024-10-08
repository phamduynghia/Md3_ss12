package com.ra.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {
    private Integer productId;
    private String productName;
    private String manufacturer;
    private Date created;
    private byte batch;
    private int quantity;
    private boolean productStatus;

    public Product() {
    }

    public Product(Integer productId, String productName, String manufacturer, Date created, byte batch, int quantity, boolean productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.created = created;
        this.batch = batch;
        this.quantity = quantity;
        this.productStatus = productStatus;
    }

    public Product(Integer productId, String productName, String manufacturer,byte batch, int quantity, boolean productStatus) {

        this.productId = productId;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.batch = batch;
        this.quantity = quantity;
        this.productStatus = productStatus;
    }

    // Getters and Setters
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public byte getBatch() {
        return batch;
    }

    public void setBatch(byte batch) {
        this.batch = batch;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }
    public String formatDate(){
        return new SimpleDateFormat("dd/MM/yyyy").format(created);
    }
}
