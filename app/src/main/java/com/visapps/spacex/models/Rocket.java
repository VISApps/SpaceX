package com.visapps.spacex.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Rocket {

    private String rocketId;
    private String rocketName;
    private String rocketType;

    public Rocket(JSONObject rocket) throws JSONException{
        rocketId = rocket.getString("rocket_id");
        rocketName = rocket.getString("rocket_name");
        rocketType = rocket.getString("rocket_type");
    }

    public String getRocketId() {
        return rocketId;
    }

    public void setRocketId(String rocketId) {
        this.rocketId = rocketId;
    }

    public String getRocketName() {
        return rocketName;
    }

    public void setRocketName(String rocketName) {
        this.rocketName = rocketName;
    }

    public String getRocketType() {
        return rocketType;
    }

    public void setRocketType(String rocketType) {
        this.rocketType = rocketType;
    }

}
