package com.marketdata.MarketSimulatorBackend.model;

import lombok.*;

@Data
public class StockPrice {
    private String companyName;
    private double price;
    private String timestamp;

    public StockPrice(String companyName, double price, String timestamp) {
        this.companyName = companyName;
        this.price = price;
        this.timestamp = timestamp;
    }
}
