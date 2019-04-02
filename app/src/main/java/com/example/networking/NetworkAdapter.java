package com.example.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkAdapter {
    static int TIMEOUT = 3000;


    public static String httpRequest(String urlString){
        String result = "";
        InputStream input = null;
        HttpURLConnection urlConnection = null;
        int responseCode;
        try{
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setReadTimeout(TIMEOUT);
            urlConnection.setConnectTimeout(TIMEOUT);
            urlConnection.connect();
            responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                input =  urlConnection.getInputStream();
                if(input!=null){
                    InputStreamReader inputReader = new InputStreamReader(input);
                    BufferedReader reader = new BufferedReader(inputReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while((line = reader.readLine())!=null){
                        stringBuilder.append(line).append('\n');
                    }
                    result = stringBuilder.toString();
            }
            }
        }catch(IOException e){e.printStackTrace();}
        finally {
            if(input != null){
                try{input.close();}
                catch (IOException e){e.printStackTrace();}
            }
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
            return result;
        }

    }
}
