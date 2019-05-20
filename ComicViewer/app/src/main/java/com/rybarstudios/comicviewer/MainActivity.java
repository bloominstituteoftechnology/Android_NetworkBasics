package com.rybarstudios.comicviewer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
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
                    new AsyncPreviousComic().execute();
                    return true;
                case R.id.navigation_random:
                   new AsyncRandomComic().execute();
                    return true;
                case R.id.navigation_next:
                    new AsyncNextComic().execute();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.image_view);
        textView = findViewById(R.id.textView);

        new AsyncComicThread().execute();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void updateUi(XkcdComic xkcdComic) {
        mImageView.setImageBitmap(xkcdComic.getBitMap());
        mTextMessage.setText("Title: " + xkcdComic.getSafeTitle());
        mTextMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        textView.setText("Alt text: " + xkcdComic.getAlt());
        currentComic = xkcdComic;
    }

    class AsyncComicThread extends AsyncTask<XkcdComic, Void, XkcdComic> {

        @Override
        protected XkcdComic doInBackground(XkcdComic... xkcdComics) {
            XkcdComic comic = XkcdDao.getRecentComic();
            recentComic = comic;
            return comic;
        }

        @Override
        protected void onPostExecute(XkcdComic xkcdComic) {
            updateUi(xkcdComic);
        }
    }

    class AsyncPreviousComic extends AsyncTask<XkcdComic, Void, XkcdComic> {

        @Override
        protected XkcdComic doInBackground(XkcdComic... xkcdComics) {
            XkcdComic comic = XkcdDao.getPreviousComic(currentComic);
            return comic;
        }

        @Override
        protected void onPostExecute(XkcdComic xkcdComic) {
            if(currentComic.getNum() != FIRST_COMIC) {
                updateUi(xkcdComic);
            }
        }
    }

    class AsyncNextComic extends AsyncTask<XkcdComic, Void, XkcdComic> {

        @Override
        protected XkcdComic doInBackground(XkcdComic... xkcdComics) {
            XkcdComic comic = XkcdDao.getNextComic(currentComic);
            return comic;
        }

        @Override
        protected void onPostExecute(XkcdComic xkcdComic) {
            if(currentComic.getNum() != recentComic.getNum()) {
                updateUi(xkcdComic);
            }
        }
    }

    class AsyncRandomComic extends AsyncTask<XkcdComic, Void, XkcdComic> {

        @Override
        protected XkcdComic doInBackground(XkcdComic... xkcdComics) {
            XkcdComic comic = XkcdDao.getRandomComic();
            return comic;
        }

        @Override
        protected void onPostExecute(XkcdComic xkcdComic) {
            if(xkcdComic.getNum() <= recentComic.getNum() && xkcdComic.getNum() >= FIRST_COMIC) {
                updateUi(xkcdComic);
            }
        }
    }

}
