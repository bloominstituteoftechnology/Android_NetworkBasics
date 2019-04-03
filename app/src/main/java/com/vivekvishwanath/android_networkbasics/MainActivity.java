package com.vivekvishwanath.android_networkbasics;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView comicImage;
    private TextView comicText;
    private BottomNavigationItemView nextButton;
    private BottomNavigationItemView previousButton;
    private XkcdComic comic;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.previous_button:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            comic = XkcdDao.getPreviousComic(comic);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateUI(comic);
                                }
                            });
                        }
                    }).start();
                    return true;
                case R.id.random_button:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            comic = XkcdDao.getRandomComic();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateUI(comic);
                                }
                            });
                        }
                    }).start();
                    return true;
                case R.id.next_button:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            comic = XkcdDao.getNextComic(comic);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateUI(comic);
                                }
                            });
                        }
                    }).start();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comicImage = findViewById(R.id.comic_image);
        comicText = findViewById(R.id.comic_text);
        nextButton = findViewById(R.id.next_button);
        previousButton = findViewById(R.id.previous_button);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        new Thread(new Runnable() {
            @Override
            public void run() {
                comic = XkcdDao.getRecentComic();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateUI(comic);
                    }
                });
            }
        }).start();
    }

    private void updateUI (XkcdComic comic) {
        if (comic.getNum() == 1) {
            previousButton.setVisibility(View.GONE);
        } else if(comic.getNum() == XkcdDao.maxComicNumber) {
            nextButton.setVisibility(View.GONE);
        } else {
            nextButton.setVisibility(View.VISIBLE);
            previousButton.setVisibility(View.VISIBLE);
        }
        comicImage.setImageBitmap(comic.getImage());
        comicText.setText(comic.getAlt());
    }

}
