package com.example.radhikasriram.cs125final;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements getInitPrice.getInitPriceCallback {
    final String apple = "AAPL";
    final String ms = "MSFT";
    final String amazon = "AMZN";
    final String alibaba = "BABA";
    final String facebook = "FB";
    final String gm = "GM";
    final String nasdaq = "JPM";
    final String sandp = "MER-K";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button AAPL = (Button)findViewById(R.id.AAPL);
        AAPL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                startActvity(apple);
            }
        });
        Button AMZN = (Button)findViewById(R.id.AMZN);
        AMZN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0) {
                startActvity(amazon);
            }
        });
        Button BABA = (Button)findViewById(R.id.BABA);
        BABA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActvity(alibaba);
            }
        });
        Button MSFT = (Button)findViewById(R.id.MSFT);
        MSFT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActvity(ms);
            }
        });
        Button FB = (Button)findViewById(R.id.FB);
        FB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActvity(facebook);
            }
        });
        Button GM = (Button)findViewById(R.id.GM);
        GM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActvity(gm);
            }
        });
        Button NASDAQ = (Button)findViewById(R.id.NASDAQ);
        NASDAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActvity(nasdaq);
            }
        });
        Button SandP = (Button)findViewById(R.id.INDEXSP);
        SandP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActvity(sandp);
            }
        });
        final Button search = (Button)findViewById(R.id.Search);
        final EditText text = (EditText)findViewById(R.id.editText2);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = text.getText().toString();
                startActvity(value);
            }
        });
        new getInitPrice(this).execute(apple);
        new getInitPrice(this).execute(amazon);
        new getInitPrice(this).execute(alibaba);
        new getInitPrice(this).execute(ms);
        new getInitPrice(this).execute(facebook);
        new getInitPrice(this).execute(gm);
        new getInitPrice(this).execute(nasdaq);
        new getInitPrice(this).execute(sandp);


    }

    private void startActvity(String symbol) {
        Intent myIntent = new Intent(MainActivity.this,
                StockView.class);
        Bundle b = new Bundle();
        b.putString("key", symbol);
        myIntent.putExtras(b);
        Log.d("Main", "Exiting main");

        startActivity(myIntent);
    }
    public void onResultReceived(String result, String symbol) {
        Button price;
        if (symbol.equals(apple)) {
            price = (Button) findViewById(R.id.AAPL);
        } else if (symbol.equals(ms)) {
            price = (Button) findViewById(R.id.MSFT);
        } else if (symbol.equals(amazon)) {
            price = (Button) findViewById(R.id.AMZN);
        } else if (symbol.equals(alibaba)) {
            price = (Button) findViewById(R.id.BABA);
        } else if (symbol.equals(facebook)) {
            price = (Button) findViewById(R.id.FB);
        } else if (symbol.equals(gm)) {
            price = (Button) findViewById(R.id.GM);
        } else if (symbol.equals(nasdaq)) {
            price = (Button) findViewById(R.id.NASDAQ);
        } else {
            price = findViewById(R.id.INDEXSP);
        }
        price.setText(result);
    }

}
