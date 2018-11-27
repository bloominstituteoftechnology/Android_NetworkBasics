package com.example.patrickjmartin.network_basics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        (new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("Result String", NetworkAdapter.httpRequest("https://xkcd.com/info.0.json"));
            }
        })).start();
    }
}
