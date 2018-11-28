package com.thadocizn.networkbasics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    private static final String HTTP_REQUEST = "https://xkcd.com/info.0.json";
    private Button previous, random, next;
    XkcdComic comics;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        previous = findViewById(R.id.btnPrevious);
        random = findViewById(R.id.btnRandom);
        next = findViewById(R.id.btnNext);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XkcdDao.getPreviousComic();
            }
        });

        findViewById(R.id.btnRandom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XkcdDao.getRandomComic();
            }
        });

        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XkcdDao.getNextComic();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    NetworkAdapter.httpGetRequest(HTTP_REQUEST);
                   comics = XkcdDao.getRecentComic();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



   /* Thread next = new Thread(new Runnable() {
        @Override
        public void run() {

        }
    }).start();

    Thread random = new Thread(new Runnable() {
        @Override
        public void run() {

        }
    }).start();*/

    public void updateUI(XkcdComic comic){
        if (comic != null){
            TextView title = (TextView) findViewById(R.id.tvTitle);
            title.setText(comic.getTitle());
            ImageView image = findViewById(R.id.ivComic);

            if (comic.getNum() == XkcdDao.MAX){
                next.setEnabled(false);

            }else if (comic.getNum() == 1){
                previous.setEnabled(false);
            }
        }
    }
}
