package com.example.radhikasriram.cs125final;

import android.os.AsyncTask;
import android.util.Log;

import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;

public class getInitPrice extends AsyncTask<String, Void, String> {

    getInitPriceCallback listener;

    public getInitPrice (getInitPriceCallback listener) {
        this.listener = listener;
    }

    //BigDecimal price;
    protected String doInBackground(String... stockQuote) {
        String symbol = stockQuote[0];
        //https://github.com/WojciechZankowski/iextrading4j#quick-start
        try {
            final IEXTradingClient iexTradingClient = IEXTradingClient.create();
            final Quote quote = iexTradingClient.executeRequest(new QuoteRequestBuilder().withSymbol(symbol).build());
            return quote.toString();


        } catch (Exception e) {
            Log.d("doInBackground", "No work");
            Log.d("doInBackground", e.toString());
        }
        return "Executed";
    }

    protected void onPostExecute(String result) {
        listener.onResultReceived(result);
    }

    public interface getInitPriceCallback {
        void onResultReceived(String result);
    }

}


