package com.example.jacob.android_networkbasics;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    offloadTask task;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        task = new offloadTask();
        task.execute("");

/*        new Thread(new Runnable() {
            @Override
            public void run() {
                UpdateUI(XkcdDao.getRecentComic());
//               String result = NetworkAdapter.httpRequest("https://xkcd.com/info.0.json");
//                Log.i(getLocalClassName(), result);
            }
        }).start();*/

    }


    public void UpdateUI(XkcdComic comic) {
        ((TextView) findViewById(R.id.text_title)).setText(comic.getTitle());
        ((ImageView) findViewById(R.id.image_comic)).setImageBitmap(comic.getBitmap());
    }


    public class offloadTask extends AsyncTask<String, Integer, XkcdComic> {

        @Override
        protected void onPostExecute(XkcdComic comic) {
            ((TextView) findViewById(R.id.text_title)).setText(comic.getTitle());
            ((ImageView) findViewById(R.id.image_comic)).setImageBitmap(comic.getBitmap());
        }

        @Override
        protected XkcdComic doInBackground(String... strings) {
            return XkcdDao.getRecentComic();
        }
    }




}
