package com.marketdata.MarketSimulatorBackend.service;

import com.marketdata.MarketSimulatorBackend.model.StockPrice;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlphaVantageProvider implements StockServiceProvider {

    private static final String ALPHA_VANTAGE_URL = "https://www.alphavantage.co/query";
    private static final String API_KEY = "OYPLC5RZ9VIXXVG5"; // Replace with your actual key
    private static final int MAX_API_CALLS_PER_DAY = 25;
    private int apiCallCount = 0;

    @Override
    public List<StockPrice> getStockPrices(List<String> symbols) {
        if (apiCallCount >= MAX_API_CALLS_PER_DAY) {
            System.out.println("Maximum API calls reached. Using pseudo data.");
            List<StockPrice> fallbackData = new ArrayList<>();
            for (String symbol : symbols) {
                fallbackData.addAll(getPseudoData(symbol));
            }
            return fallbackData;
        }

        apiCallCount++;
        return fetchFromApi(symbols); // Separate method for actual API calls
    }

    private List<StockPrice> fetchFromApi(List<String> symbols) {
        // Logic for fetching from Alpha Vantage
        List<StockPrice> stockPrices = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();

        for (String symbol : symbols) {
            try {
                String url = String.format(
                        "%s?function=TIME_SERIES_DAILY&symbol=%s&apikey=%s",
                        ALPHA_VANTAGE_URL, symbol, API_KEY
                );

                String response = restTemplate.getForObject(url, String.class);
                JSONObject json = new JSONObject(response);
                JSONObject timeSeries = json.getJSONObject("Time Series (Daily)");

                // Extract the last 7 days of data
                timeSeries.keys().forEachRemaining(date -> {
                    JSONObject dailyData = timeSeries.getJSONObject(date);
                    stockPrices.add(new StockPrice(
                            symbol,
                            dailyData.getDouble("4. close"), // Closing price
                            date
                    ));
                });

            } catch (Exception e) {
                System.err.println("Error fetching data for symbol " + symbol + ": " + e.getMessage());
                stockPrices.addAll(getPseudoData(symbol)); // Fallback to pseudo data
            }
        }
        return stockPrices;
    }
    
    // Method to provide pseudo data
    private List<StockPrice> getPseudoData(String symbol) {
        List<StockPrice> pseudoData = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            pseudoData.add(new StockPrice(
                    symbol,
                    100.0 + Math.random() * 50, // Random price between 100 and 150
                    "2024-01-0" + i // Example dates
            ));
        }
        return pseudoData;
    }

    @Override
    public List<String> autocompleteSymbols(String query) {
        // Hardcoded symbol list; will replace with dynamic data later
        List<String> allSymbols = List.of("AAPL", "GOOGL", "AMZN", "MSFT", "TSLA", "META", "NFLX", "NVDA", "ORCL", "IBM");
        List<String> matchingSymbols = new ArrayList<>();
        for (String symbol : allSymbols) {
            if (symbol.toLowerCase().startsWith(query.toLowerCase())) {
                matchingSymbols.add(symbol);
            }
        }
        return matchingSymbols;
    }
}
