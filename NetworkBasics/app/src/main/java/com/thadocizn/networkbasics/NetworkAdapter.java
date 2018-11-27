package com.thadocizn.networkbasics;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkAdapter {
    private static final int TIMEOUT = 3000;

    public static String httpGetRequest(String urlString, String request){
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
                    InputStream stream = null;
                    stream = httpURLConnection.getInputStream();
                    if (stream != null){
                        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
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
                if (httpURLConnection != null){
                    httpURLConnection.disconnect();
                }
            }
        return strResult;
    }
}
