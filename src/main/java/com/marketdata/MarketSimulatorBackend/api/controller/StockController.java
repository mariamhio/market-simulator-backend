package com.marketdata.MarketSimulatorBackend.api.controller;

import com.marketdata.MarketSimulatorBackend.model.StockPrice;
import com.marketdata.MarketSimulatorBackend.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * StockController is a REST controller that provides endpoints
 * to serve stock price data.
 */
@RestController
@RequestMapping("/api") // Base URL for all endpoints in this controller
public class StockController {

    private StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stocks")
    public List<StockPrice> getMockStockPrices() {
        return Arrays.asList(
                new StockPrice("Company A", 150, "2024-11-01T10:00:00"),
                new StockPrice("Company A", 180, "2024-11-01T11:00:00"),
                new StockPrice("Company A", 210, "2024-11-01T12:00:00"),
                new StockPrice("Company B", 200, "2024-11-01T10:00:00"),
                new StockPrice("Company C", 250, "2024-11-01T11:00:00")
        );
    }


}
