package com.marketdata.MarketSimulatorBackend.api.model;

public class OrderBookEntry {
    private String type; // "bid" or "ask"
    private double price;
    private int volume;

    public OrderBookEntry(String type, double price, int volume) {
        this.type = type;
        this.price = price;
        this.volume = volume;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
