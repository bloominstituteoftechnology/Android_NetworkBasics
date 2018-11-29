package com.thadocizn.networkbasics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static final String HTTP_REQUEST = "https://xkcd.com/info.0.json";
    private Button previous, random, next;
    XkcdComic comicTracker, pre, nextComic, ran;
    TextView title;
    ImageView image;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        previous = findViewById(R.id.btnPrevious);
        random = findViewById(R.id.btnRandom);
        next = findViewById(R.id.btnNext);
        title = (TextView) findViewById(R.id.tvTitle);
        image = findViewById(R.id.ivComic);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                       pre = XkcdDao.getPreviousComic(comicTracker);
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               updateUI(pre);
                               comicTracker = pre;
                           }
                       });

                    }
                }).start();
            }
        });

        findViewById(R.id.btnRandom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                       ran = XkcdDao.getRandomComic();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateUI(ran);
                                comicTracker = ran;
                            }
                        });
                    }
                }).start();
            }
        });

        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                     nextComic =   XkcdDao.getNextComic(comicTracker);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            comicTracker = nextComic;
                            updateUI(nextComic);

                        }
                    });
                    }
                }).start();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                NetworkAdapter.httpRequest(HTTP_REQUEST);
                comicTracker = XkcdDao.getRecentComic();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateUI(comicTracker);
                    }
                });
            }
        }).start();
    }

    public void updateUI(XkcdComic comic){
        Log.i("Chrl","test" + comic.getTitle());
            title.setText(comic.getTitle());
            image.setImageBitmap(comic.getImage());
            if (comic.getNum() == XkcdDao.MAX){
                next.setEnabled(false);

            }else if (comic.getNum() == 1){
                previous.setEnabled(false);
            }
        }
    }
