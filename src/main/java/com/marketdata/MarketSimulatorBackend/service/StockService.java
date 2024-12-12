package com.marketdata.MarketSimulatorBackend.service;

import com.marketdata.MarketSimulatorBackend.model.StockPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StockService {
    private final StockServiceProvider stockPriceProvider;

    @Autowired
    public StockService(StockServiceProvider stockPriceProvider) {
        this.stockPriceProvider = stockPriceProvider;
    }

    public List<StockPrice> getStockPrices(List<String> symbols) {
        return stockPriceProvider.getStockPrices(symbols);
    }
}

