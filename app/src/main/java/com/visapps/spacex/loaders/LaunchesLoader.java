package com.visapps.spacex.loaders;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import com.visapps.spacex.api.ApiClient;
import com.visapps.spacex.models.Launches;


public class LaunchesLoader extends AsyncTaskLoader<Launches> {

    private int year;

    public LaunchesLoader(Context context, Bundle args) {
        super(context);
        year = args.getInt("year");
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Launches loadInBackground() {
        try {
            return ApiClient.getInstance().getLaunches(year);
        } catch (Exception e) {
            return null;
        }
    }


}
