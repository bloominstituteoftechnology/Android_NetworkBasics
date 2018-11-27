package com.thadocizn.networkbasics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkAdapter {
    private static final int TIMEOUT = 3000;

    public static Bitmap httpImageRequest(String imageUrl) throws IOException {
        Bitmap bmResult = null;
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(imageUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            url.openConnection().setReadTimeout(TIMEOUT);
            url.openConnection().setConnectTimeout(TIMEOUT);
            httpURLConnection.connect();

            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream();
                if (inputStream != null){
                 bmResult =   BitmapFactory.decodeStream(inputStream);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                    inputStream.close();
                httpURLConnection.disconnect();
            }
        }
        return bmResult;
    }

    public static String httpGetRequest(String urlString, String request) throws IOException {
        String strResult = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;

            try {
                URL url = new URL(urlString);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                url.openConnection().setReadTimeout(TIMEOUT);
                url.openConnection().setConnectTimeout(TIMEOUT);
                httpURLConnection.connect();

                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK){
                    inputStream = httpURLConnection.getInputStream();
                    if (inputStream != null){
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        StringBuilder builder = new StringBuilder();
                        String line = reader.readLine();
                        while (line != null){
                            builder.append(line);
                            line = reader.readLine();
                        }
                        strResult = builder.toString();

                    }

                }

            } catch (java.io.IOException e) {
                e.printStackTrace();
            }finally {
                if (inputStream != null){
                    inputStream.close();
                    httpURLConnection.disconnect();
                }
            }
        return strResult;
    }
}
