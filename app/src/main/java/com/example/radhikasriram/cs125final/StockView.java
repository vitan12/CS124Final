package com.example.radhikasriram.cs125final;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.lang.String;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import java.math.BigDecimal;
import java.net.URL;


public class StockView extends AppCompatActivity {
    boolean error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("StockView", "Entered class");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_view);
        Bundle b = getIntent().getExtras();
        String getSymbol = "";
        if (b != null)
            getSymbol = b.getString("key");
        System.out.println(getSymbol);



        // https://financequotes-api.com/
        Log.d("StockView", "Failed before getting stock");
        error = false;
        new getInitPrice().execute(getSymbol);
        Log.d("StockView", "Failed After getting stock");
        if (error) {
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(StockView.this, android.R.style.Theme_Material_Dialog_Alert);
            builder.setTitle("Unable to get Quote")
                    .setMessage("We were unable to pull a quote for this symbol. Please check your network connectivity and spelling, and try again.")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActvity();
                        }
                    })

                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(getSymbol);

        final GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
        Button oneDay = (Button) findViewById(R.id.button5);
        oneDay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                graph.removeAllSeries();
                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                        new DataPoint(0, 1),
                        new DataPoint(1, 3),
                        new DataPoint(2, 5),
                        new DataPoint(3, 1),
                        new DataPoint(4, 4)
                });
                graph.addSeries(series);
            }
        });
        Button oneWeek = (Button) findViewById(R.id.button4);
        oneWeek.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                graph.removeAllSeries();
                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                        new DataPoint(0, 2),
                        new DataPoint(1, 1),
                        new DataPoint(2, 4),
                        new DataPoint(3, 3),
                        new DataPoint(4, 5)
                });
                graph.addSeries(series);
            }
        });
        Button oneMonth = (Button) findViewById(R.id.button3);
        oneMonth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                graph.removeAllSeries();
                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                        new DataPoint(0, 4),
                        new DataPoint(1, 3),
                        new DataPoint(2, 1),
                        new DataPoint(3, 5),
                        new DataPoint(4, 3)
                });
                graph.addSeries(series);
            }
        });
        Button sixMonth = (Button) findViewById(R.id.button2);
        sixMonth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                graph.removeAllSeries();
                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                        new DataPoint(0, 1),
                        new DataPoint(1, 2),
                        new DataPoint(2, 5),
                        new DataPoint(3, 1),
                        new DataPoint(4, 2)
                });
                graph.addSeries(series);
            }
        });

        final Button calculate = (Button) findViewById(R.id.calculate);
        final EditText text = (EditText) findViewById(R.id.editText);
        final TextView editable = (TextView) findViewById(R.id.textView2);
        final String symbol = getSymbol;
        calculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String calculation = text.getText().toString();
                int n;
                if (calculation.matches("\\d+")) {
                    n = Integer.parseInt(calculation);
                } else {
                    n = 0;
                }
                String finalt = "It will cost $" + n + " at " + symbol + " per share";
                editable.setText(finalt);
            }
        });

    }

    private void startActvity() {
        Intent myIntent = new Intent(StockView.this,
                MainActivity.class);
        startActivity(myIntent);
    }
    private class getInitPrice extends AsyncTask<String, Void, String> {
        BigDecimal price;
        protected String doInBackground(String... quote) {
            String symbol = quote[0];
            try {
                Stock stock = YahooFinance.get(symbol);
                price = stock.getQuote().getPrice();
            } catch (Exception e) {
                error = true;
                Log.d("Thing", "No work");
            }
            return "Executed";
        }
        protected void onPostExecute(String result) {
            if (!error) {
                TextView price = (TextView) findViewById(R.id.currentPrice);
                price.setText(price.toString());
            } else {
                Log.d("It", "no work :(");
            }
        }
    }
}

