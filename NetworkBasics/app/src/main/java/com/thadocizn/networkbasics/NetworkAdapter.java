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

    public static String httpRequest(String urlString){
        String result = "";
        InputStream stream = null;
        HttpURLConnection connection = null;
        try{
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(TIMEOUT);
            connection.setConnectTimeout(TIMEOUT);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                stream = connection.getInputStream();
                if(stream != null){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null){
                        sb.append(line).append('\n');
                    }
                    result = sb.toString();
                }
            }else{
                throw new IOException("HTTP Error code: " + responseCode);
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
            result = e.getMessage();
        }finally {
            if(stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                connection.disconnect();
            }
        }
        return result;
    }

    public static Bitmap httpImageRequest(String urlString){
        Bitmap image = null;
        InputStream stream = null;
        HttpURLConnection connection = null;
        try{
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(TIMEOUT);
            connection.setConnectTimeout(TIMEOUT);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                stream = connection.getInputStream();
                if(stream != null){
                    image = BitmapFactory.decodeStream(stream);
                }
            }else{
                throw new IOException("HTTP Error code: " + responseCode);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                connection.disconnect();
            }
        }
        return image;
    }
   /* public static Bitmap httpImageRequest(String imageUrl){
        Bitmap bmResult = null;
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(imageUrl);
            try {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                url.openConnection().setReadTimeout(TIMEOUT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                url.openConnection().setConnectTimeout(TIMEOUT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpURLConnection.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK){
                try {
                    inputStream = httpURLConnection.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (inputStream != null){
                 bmResult =   BitmapFactory.decodeStream(inputStream);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                httpURLConnection.disconnect();
            }
        }
        return bmResult;
    }

    public static String httpGetRequest(String urlString) {
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
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    httpURLConnection.disconnect();
                }
            }
        return strResult;
    }*/
}
