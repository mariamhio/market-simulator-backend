package com.marketdata.MarketSimulatorBackend.service;

import com.marketdata.MarketSimulatorBackend.model.StockPrice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StockService {

    private List<StockPrice> stockList;

    public StockService() {
        stockList = new ArrayList<>(Arrays.asList(
                new StockPrice("Company A", 150, "2024-11-01T10:00:00"),
                new StockPrice("Company A", 180, "2024-11-01T11:00:00"),
                new StockPrice("Company A", 210, "2024-11-01T12:00:00"),
                new StockPrice("Company B", 200, "2024-11-01T10:00:00"),
                new StockPrice("Company C", 250, "2024-11-01T11:00:00")
        ));
    }

    //make an object of the service inside the controller and return it
    //public StockPrice getStock(Integer Id) {}
}
