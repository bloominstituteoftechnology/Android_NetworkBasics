package com.example.android_networkbasics;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int FIRST_COMIC = 1;
    private TextView mTextMessage, textView;
    ImageView mImageView;
    private XkcdComic currentComic;
    private XkcdComic recentComic;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_previous:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final XkcdComic comic = XkcdDao.getPreviousComic(currentComic);
                            if(currentComic.getNum() != FIRST_COMIC) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        updateUi(comic);
                                    }
                                });
                            }
                        }
                    }).start();
                    return true;
                case R.id.navigation_random:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final XkcdComic comic = XkcdDao.getRandomComic();
                            if(comic.getNum() <= recentComic.getNum() && comic.getNum() >= FIRST_COMIC) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        updateUi(comic);
                                    }
                                });
                            }
                        }
                    }).start();
                    return true;
                case R.id.navigation_next:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final XkcdComic comic = XkcdDao.getNextComic(currentComic);
                            if(currentComic.getNum() != recentComic.getNum()) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        updateUi(comic);
                                    }
                                });
                            }
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

        mImageView = findViewById(R.id.comic_image_view);
        textView = findViewById(R.id.text_view);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final XkcdComic comic = XkcdDao.getRecentComic();
                recentComic = comic;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateUi(comic);
                    }
                });
            }
        }).start();

        mTextMessage =  findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void updateUi(XkcdComic xkcdComic) {
        mImageView.setImageBitmap(xkcdComic.getBitmap());
//        mTextMessage.setText("Title: " + xkcdComic.getSafe_title());
//        mTextMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        textView.setText(xkcdComic.getSafe_title());
        currentComic = xkcdComic;
    }

}