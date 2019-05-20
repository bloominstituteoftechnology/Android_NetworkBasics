package com.rybarstudios.comicviewer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

public class NetworkAdapter {
    public static final int TIMEOUT = 3000;

    public static String httpGetRequest(final String urlString) {
        String result = "";
        HttpURLConnection connection = null;
        InputStream stream = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(TIMEOUT);
            connection.setReadTimeout(TIMEOUT);
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                result = Integer.toString(responseCode);
                throw new IOException("HTTP error code: " + responseCode);
            }

            stream = connection.getInputStream();
            if (stream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                    line = reader.readLine();
                }
                result = builder.toString();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    //TODO implement this method
    public static Bitmap httpImageRequest(String urlString) {
        Bitmap comic = null;
        HttpURLConnection connection = null;
        InputStream stream = null;

        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(TIMEOUT);
            connection.setReadTimeout(TIMEOUT);
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                stream = connection.getInputStream();
                if(stream != null) {
                    comic = BitmapFactory.decodeStream(stream);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(connection != null) {
                connection.disconnect();
            }
            if(stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return comic;
    }
}
