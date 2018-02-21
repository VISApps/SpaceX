package com.visapps.spacex.api;

import com.visapps.spacex.models.Launches;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {

    private static final String apilink = "https://api.spacexdata.com/v2/launches";
    private static ApiClient instance;

    private ApiClient(){

    }

    public static void initInstance(){
        if(instance == null){
            instance = new ApiClient();
        }
    }

    public static ApiClient getInstance(){
        return instance;
    }

    public Launches getLaunches(int year) throws Exception{
        URL url = new URL(apilink + "?launch_year=" + String.valueOf(year));
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder source = new StringBuilder();
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            source.append(line);
        }
        return new Launches(source.toString());
    }

}
