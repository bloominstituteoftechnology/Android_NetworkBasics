package com.example.android_networkbasics;

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
import java.util.Map;

public class NetworkAdapter {

    public static final int CONNECT_TIMEOUT = 3000;
    public static final int READ_TIMEOUT = 3000;

    static Bitmap httpImageRequest(String stringUri) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        Bitmap imgBitmap = null;

        try {
            URL url = new URL(stringUri);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
            httpURLConnection.setReadTimeout(READ_TIMEOUT);

            httpURLConnection.connect();

            final int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                if (inputStream != null) {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                    imgBitmap = BitmapFactory.decodeStream(bufferedInputStream);
                } else {
                    throw new IOException();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return imgBitmap;
    }

    static String httpRequest(String stringUri) {
        return httpRequest(stringUri, null);
    }

    static String httpRequest(String stringUri, Map<String, String> headerProperties) {
        String result = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = new URL(stringUri);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
            httpURLConnection.setReadTimeout(READ_TIMEOUT);

            httpURLConnection.connect();

            if(headerProperties != null) {
                for(Map.Entry<String, String> entry: headerProperties.entrySet()) {
                    httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            final int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                if (inputStream != null) {
                    InputStreamReader isReader = new InputStreamReader(inputStream);
                    BufferedReader reader = new BufferedReader(isReader);
                    StringBuilder builder = new StringBuilder();


                    String line = reader.readLine();
                    while (line != null) {
                        builder.append(line);
                        line = reader.readLine();
                    }

                    result = builder.toString();
                } else {
                    throw new IOException();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            result = "MalformedURL";
        } catch (IOException e) {
            e.printStackTrace();
            result = "IOexception";
        } finally {

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return result;
    }
}
