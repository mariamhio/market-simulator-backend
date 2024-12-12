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

    @Override
    public List<StockPrice> getStockPrices(List<String> symbols) {
        List<StockPrice> stockPrices = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();

        for (String symbol : symbols) {
            try {
                String url = String.format(
                        "%s?function=GLOBAL_QUOTE&symbol=%s&apikey=%s",
                        ALPHA_VANTAGE_URL, symbol, API_KEY
                );

                String response = restTemplate.getForObject(url, String.class);
                JSONObject json = new JSONObject(response);
                JSONObject globalQuote = json.getJSONObject("Global Quote");

                stockPrices.add(new StockPrice(
                        globalQuote.getString("01. symbol"),
                        globalQuote.getDouble("05. price"),
                        globalQuote.getString("07. latest trading day")
                ));
            } catch (Exception e) {
                System.err.println("Error fetching data for symbol " + symbol + ": " + e.getMessage());
                throw new RuntimeException("Failed to fetch data for symbol: " + symbol);
            }
        }

        return stockPrices;
    }
}
