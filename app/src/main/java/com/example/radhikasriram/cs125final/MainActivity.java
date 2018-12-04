package com.example.radhikasriram.cs125final;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    final String apple = "AAPL";
    final String ms = "MSFT";
    final String amazon = "AMZN";
    final String alibaba = "BABA";
    final String facebook = "FB";
    final String gm = "GM";
    final String nasdaq = "NASDAQ";
    final String sandp = "SandP";
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
        Button SandP = (Button)findViewById(R.id.SandP);
        SandP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActvity(sandp);
            }
        });
        final Button search = (Button)findViewById(R.id.Search);
        final EditText text = (EditText)findViewById(R.id.editText2);
        search.setOnClickListener(new View.OnClickListener() {
            String value = text.getText().toString();
            @Override
            public void onClick(View v) {
                startActvity(value);
            }
        });

    }

    private void startActvity(String symbol) {
        Intent myIntent = new Intent(MainActivity.this,
                StockView.class);
        Bundle b = new Bundle();
        b.putString("key", symbol);
        myIntent.putExtras(b);
        startActivity(myIntent);
    }

}
