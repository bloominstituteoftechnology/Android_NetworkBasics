package com.example.joshh.android_networkbasics;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView comicTitle, comicAlt;
    private ImageView comicImage;

    private XkcdComic recentXkcdComic;
    private XkcdComic nextComic;
    private XkcdComic prevComic;
    private XkcdComic currentComic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        comicTitle = findViewById(R.id.comic_title);
        comicAlt = findViewById(R.id.comic_alt);
        comicImage = findViewById(R.id.comic_image);

        findViewById(R.id.navigation_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        prevComic = XkcdDao.getPreviousComic(currentComic);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("asdafgasgegagf", Integer.toString(prevComic.getNum()));
                                updateUI(prevComic);
                                currentComic = prevComic;
                            }
                        });
                    }
                })).start();
            }
        });

        findViewById(R.id.navigation_dashboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        recentXkcdComic = XkcdDao.getRandomComic();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateUI(recentXkcdComic);
                                currentComic = recentXkcdComic;
                            }
                        });
                    }
                })).start();
            }
        });

        findViewById(R.id.navigation_notifications).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (new Thread(new Runnable() {
                    @Override
                    public void run() {
                        nextComic = XkcdDao.getNextComic(currentComic);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateUI(nextComic);
                                currentComic = nextComic;
                            }
                        });
                    }
                })).start();
            }
        });

        (new Thread(new Runnable() {
            @Override
            public void run() {
                recentXkcdComic = XkcdDao.getRecentComic();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateUI(recentXkcdComic);
                        currentComic = recentXkcdComic;
                    }
                });
            }
        })).start();
    }

    private void updateUI(XkcdComic xkcdComic){
        comicTitle.setText(xkcdComic.getTitle());
        comicAlt.setText(xkcdComic.getAlt());
        comicImage.setImageBitmap(xkcdComic.getBitMap());
        if(xkcdComic.getNum() == recentXkcdComic.getNum()){
            findViewById(R.id.navigation_notifications).setVisibility(View.INVISIBLE);
        }else{
            findViewById(R.id.navigation_notifications).setVisibility(View.VISIBLE);
        }
        if(xkcdComic.getNum() == 1){
            findViewById(R.id.navigation_home).setVisibility(View.INVISIBLE);
        }else{
            findViewById(R.id.navigation_home).setVisibility(View.VISIBLE);
        }
    }

}
