package com.marketdata.MarketSimulatorBackend.model;

import lombok.*;

@Data
public class OrderBookEntry {
    private String type; // "bid" or "ask"
    private double price;
    private int volume;

    public OrderBookEntry(String type, double price, int volume) {
        this.type = type;
        this.price = price;
        this.volume = volume;
    }
}
