package com.jakeesveld.android_networkbasics;

import android.content.ClipData;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.os.Bundle;
import android.service.autofill.FillContext;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewTitle;
    TextView textViewDetails;
    ImageView imageViewComic;
    static int currentComicId;
    BottomNavigationView navigation;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_previous:
                    if(currentComicId != 1) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                final Comic result = ComicDAO.getPrevious(currentComicId);
                                currentComicId = result.getId();

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        updateUI(result);
                                    }
                                });

                            }
                        }).start();
                    }

                    return true;
                case R.id.navigation_random:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final Comic result = ComicDAO.getRandom();

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    currentComicId = result.getId();
                                    updateUI(result);
                                }
                            });

                        }
                    }).start();

                    return true;
                case R.id.navigation_next:

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                if(currentComicId != ComicDAO.getLatest().getId()) {
                                    final Comic result = ComicDAO.getNext(currentComicId);

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            currentComicId = result.getId();
                                            updateUI(result);
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
        textViewDetails = findViewById(R.id.text_view_details);
        textViewTitle = findViewById(R.id.text_view_title);
        imageViewComic = findViewById(R.id.image_view_comic);
        navigation = findViewById(R.id.navigation);
        currentComicId = 100;
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Comic result = ComicDAO.getLatest();


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        currentComicId = result.getId();
                        updateUI(result);

                    }
                });

            }
        }).start();


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private void updateUI(Comic comic){
        textViewDetails.setText(String.format("Comic Number: %s  %s", comic.getId(), comic.getDate()));
        textViewTitle.setText(comic.getTitle());
        imageViewComic.setImageBitmap(comic.getImage());
        imageViewComic.setAdjustViewBounds(true);
    }

}
