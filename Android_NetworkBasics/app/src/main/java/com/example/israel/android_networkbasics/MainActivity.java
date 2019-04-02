package com.example.israel.android_networkbasics;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final int WHAT_UPDATE_UI = 0;
    private XkcdComic currentComic;
    private boolean isRequesting;
    enum ERequestType {
        Previous,
        Next,
        Random
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Handler updateUIHandler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == WHAT_UPDATE_UI) {
                    XkcdComic xkcdComic = (XkcdComic) msg.obj;
                    updateUI(xkcdComic);
                    isRequesting = false;
                }
            }
        };

        isRequesting = true;
        // TODO if getRecentComic fails then there's no way to get previous or next
        // TODO add recent button? or automatically call getRecentComic if there is no currentComic
        Thread getRecentThread = new Thread() {
            @Override
            public void run() {
                super.run();
                XkcdComic xkcdComic = XkcdDao.getRecentComic();
                updateUIHandler.obtainMessage(WHAT_UPDATE_UI, xkcdComic).sendToTarget();
            }
        };
        getRecentThread.start();

        findViewById(R.id.button_previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPrevious();
            }
        });

        findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestNext();
            }
        });

        findViewById(R.id.button_random).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestRandom();
            }
        });
    }

    private void updateUI(@Nullable XkcdComic xkcdComic) {
        if (xkcdComic == null) {
            return;
        }

        currentComic = xkcdComic;

        if (xkcdComic.getNum() == XkcdDao.MIN_COMIC_NUM) {
            findViewById(R.id.button_previous).setEnabled(false);
        } else {
            findViewById(R.id.button_previous).setEnabled(true);
        }

        if (xkcdComic.getNum() == XkcdDao.getMaxComicNum()) {
            findViewById(R.id.button_next).setEnabled(false);
        } else {
            findViewById(R.id.button_next).setEnabled(true);
        }

        ((ImageView)findViewById(R.id.image_view_image)).setImageBitmap(xkcdComic.getBitmap());
    }

    private void requestPrevious() {
        if (isRequesting) {
            return;
        }
        isRequesting = true;
        HttpRequestAsyncTask httpRequestAsyncTask = new HttpRequestAsyncTask();
        httpRequestAsyncTask.execute(ERequestType.Previous);
    }

    private void requestNext() {
        if (isRequesting) {
            return;
        }
        isRequesting = true;
        HttpRequestAsyncTask httpRequestAsyncTask = new HttpRequestAsyncTask();
        httpRequestAsyncTask.execute(ERequestType.Next);
    }

    private void requestRandom() {
        if (isRequesting) {
            return;
        }
        isRequesting = true;
        HttpRequestAsyncTask httpRequestAsyncTask = new HttpRequestAsyncTask();
        httpRequestAsyncTask.execute(ERequestType.Random);
    }

    private class HttpRequestAsyncTask extends AsyncTask<ERequestType, Void, XkcdComic> {

        @Override
        protected XkcdComic doInBackground(ERequestType... requestTypes) {

            // simulates slow loading
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            switch (requestTypes[0]) {
                case Previous: {
                    return XkcdDao.getPreviousComic(currentComic);
                }
                case Next: {
                    return XkcdDao.getNextComic(currentComic);
                }
                case Random: {
                    return XkcdDao.getRandomComic();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(XkcdComic xkcdComic) {
            super.onPostExecute(xkcdComic);

            updateUI(xkcdComic);
            isRequesting = false;
        }
    }


}
