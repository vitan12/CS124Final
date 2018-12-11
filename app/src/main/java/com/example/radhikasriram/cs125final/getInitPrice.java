package com.example.radhikasriram.cs125final;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

//import pl.zankowski.iextrading4j.api.stocks.Quote;
//import pl.zankowski.iextrading4j.client.IEXTradingClient;
//import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;

public class getInitPrice extends AsyncTask<String, Void, String> {

    getInitPriceCallback listener;

    public getInitPrice (getInitPriceCallback listener) {
        this.listener = listener;
    }

    //BigDecimal price;
    protected String doInBackground(String... stockQuote) {
        String symbol = stockQuote[0];

        if (getOfficialPrice(symbol) != null) {
            return getOfficialPrice(symbol);
        }
        if (getClosingPrice(symbol) != null) {
            return getClosingPrice(symbol);
        }
        return "Error";
    }

    String getOfficialPrice(String symbol) {
        try {
            return readDataFromAPI("https://api.iextrading.com/1.0/deep/official-price?symbols=" + symbol);
        } catch (Exception e) {
            Log.d("Exception occurred", e.getMessage());
        }

        return null;
    }

    String readDataFromAPI(String url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");
        Log.d("Response", Integer.toString(conn.getResponseCode()));
        StringBuilder content;

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            content = new StringBuilder();

            while ((line = in.readLine()) != null) {
                content.append(line);
            }
        } finally {
            conn.disconnect();
        }
        Log.d("ResponseData", content.toString());
        HashMap<String, String> dataMap = new Gson().fromJson(content.toString(),
        new TypeToken<HashMap<String, String>>(){}.getType());

        if (dataMap.containsKey("close")) {
            return dataMap.get("close");
        } else if (dataMap.containsKey("price")) {
            return dataMap.get("price");
        }
        return null;
    }
    String getClosingPrice(String symbol) {
        try {
            return readDataFromAPI("https://api.iextrading.com/1.0/stock/" + symbol + "/previous");
        } catch (Exception e) {
            Log.d("Exception occurred", e.getMessage());
        }

        return null;
    }

    String getHistoricalStockData(String symbol, String range) throws MalformedURLException, IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL("https://api.iextrading.com/1.0/stock/" + symbol
                + "/chart/" + range).openConnection();

        conn.setRequestMethod("GET");
        Log.d("Response", Integer.toString(conn.getResponseCode()));
        StringBuilder content;

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            content = new StringBuilder();

            while ((line = in.readLine()) != null) {
                content.append(line);
            }
        } finally {
            conn.disconnect();
        }

        Log.d("ResponseData", content.toString());
        HashMap<String, String> dataMap = new Gson().fromJson(content.toString(),
                new TypeToken<HashMap<String, String>>(){}.getType());
        return dataMap.toString();
    }

    protected void onPostExecute(String result) {
        listener.onResultReceived(result);
    }

    public interface getInitPriceCallback {
        void onResultReceived(String result);
    }

}


