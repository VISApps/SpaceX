package com.visapps.spacex;

import android.app.Application;

import com.visapps.spacex.api.ApiClient;

public class SpaceX extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ApiClient.initInstance();
    }

}
