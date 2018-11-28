package com.example.jacob.android_networkbasics;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String RECENT = "Recent";
    public static final String NEXT = "Next";
    public static final String PREVIOUS = "Previous";
    public static final String RANDOM = "Random";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.button_back:
                    new offloadTask().execute(PREVIOUS);
//                    mTextMessage.setText(R.string.navigate_back);
                    return true;
                case R.id.button_random:
                    new offloadTask().execute(RANDOM);
//                    mTextMessage.setText(R.string.get_random);
                    return true;
                case R.id.button_forward:
                    new offloadTask().execute(NEXT);
//                    mTextMessage.setText(R.string.navigate_forward);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        new offloadTask().execute(RECENT);

/*        new Thread(new Runnable() {
            @Override
            public void run() {
                UpdateUI(XkcdDao.getRecentComic());
//               String result = NetworkAdapter.httpRequest("https://xkcd.com/info.0.json");
//                Log.i(getLocalClassName(), result);
            }
        }).start();*/

    }

    public class offloadTask extends AsyncTask<String, Integer, XkcdComic> {

        @Override
        protected void onPostExecute(XkcdComic comic) {
            if (comic != null) {
                ((TextView) findViewById(R.id.text_title)).setText(comic.getTitle());
                ((ImageView) findViewById(R.id.image_comic)).setImageBitmap(comic.getBitmap());

                if (comic.getNum() == 1) {
                    BottomNavigationView navigation = findViewById(R.id.navigation);
                    navigation.getMenu().getItem(0).setEnabled(false);
                } else if (comic.getNum() == XkcdDao.maxComicNumber) {
                    BottomNavigationView navigation = findViewById(R.id.navigation);
                    navigation.getMenu().getItem(2).setEnabled(false);
                } else {
                    BottomNavigationView navigation = findViewById(R.id.navigation);
                    navigation.getMenu().getItem(0).setEnabled(true);
                    navigation.getMenu().getItem(2).setEnabled(true);
                }

            }
        }

        @Override
        protected XkcdComic doInBackground(String... strings) {
            switch (strings[0]) {
                case RECENT:
                    return XkcdDao.getRecentComic();
                case NEXT:
                    return XkcdDao.getNextComic();
                case PREVIOUS:
                    return XkcdDao.getPreviousComic();
                case RANDOM:
                    return XkcdDao.getRandomComic();
            }
            return null;
        }
    }


}
