package com.thadocizn.networkbasics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String HTTP_REQUEST = "https://xkcd.com/info.0.json";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    NetworkAdapter.httpGetRequest(HTTP_REQUEST);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
