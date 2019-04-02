package com.example.networking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    String httpRequestString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        httpRequestString = NetworkAdapter.httpRequest("https://xkcd.com/info.0.json");
        (new Thread(new Runnable(){public void run() { ​ Log.i("test2", httpRequestString);}})).start();
    }
}
