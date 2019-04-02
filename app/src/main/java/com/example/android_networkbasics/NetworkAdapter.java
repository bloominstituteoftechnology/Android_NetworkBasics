package com.example.android_networkbasics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkAdapter {

    public static final int TIMEOUT = 3000;

    public static String httpRequest(String urlString, String requestType){
        String result = "";
        InputStream stream = null;
        HttpURLConnection connection = null;

        try {
            URL url= new URL(urlString);
           connection = (HttpURLConnection) url.openConnection();
           connection.setConnectTimeout(TIMEOUT);
           connection.setReadTimeout(TIMEOUT);

           int responseCode = connection.getResponseCode();
           if(responseCode == HttpURLConnection.HTTP_OK){
               stream = connection.getInputStream();
               if(stream != null){
                   InputStreamReader isReader = new InputStreamReader(stream);
                   BufferedReader reader = new BufferedReader(isReader);
                   StringBuilder builder = new StringBuilder();
                   String line  = reader.readLine();
                   while(line != null){
                       builder.append(line);
                       line = reader.readLine();
                   }
                   result = builder.toString();
               }

           }else{
               throw new IOException();
           }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
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

}
