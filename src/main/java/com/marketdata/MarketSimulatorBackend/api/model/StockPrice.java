package com.marketdata.MarketSimulatorBackend.api.model;

public class StockPrice {
    private String companyName;
    private double price;
    private String timestamp;

    public StockPrice(String companyName, double price, String timestamp) {
        this.companyName = companyName;
        this.price = price;
        this.timestamp = timestamp;
    }

    //Getters and setters
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
