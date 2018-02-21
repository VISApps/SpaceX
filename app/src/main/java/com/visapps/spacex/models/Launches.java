package com.visapps.spacex.models;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Launches {

    private List<Launch> launches= new ArrayList<>();

    public Launches(String source) throws JSONException{
        JSONArray array = new JSONArray(source);
        for(int i=0; i<array.length(); i++){
            launches.add(new Launch(array.getJSONObject(i)));
        }
    }

    public void setLaunches(List<Launch> launches){
        this.launches = launches;
    }

    public List<Launch> getLaunches(){
        return launches;
    }
}
