package com.visapps.spacex.models;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

public class Launch {

    private Integer flightNumber;
    private String launchYear;
    private Integer launchDateUnix;
    private String launchDateUtc;
    private String launchDateLocal;
    private Rocket rocket;
    private LaunchSite launchSite;
    private Boolean launchSuccess;
    private Links links;
    private String details;

    private Bitmap icon;

    public Launch(JSONObject launch) throws JSONException{
        flightNumber = launch.getInt("flight_number");
        launchYear = launch.getString("launch_year");
        launchDateUnix = launch.getInt("launch_date_unix");
        launchDateUtc = launch.getString("launch_date_utc");
        launchDateLocal = launch.getString("launch_date_local");
        rocket = new Rocket(launch.getJSONObject("rocket"));
        launchSite = new LaunchSite(launch.getJSONObject("launch_site"));
        launchSuccess = launch.getBoolean("launch_success");
        links = new Links(launch.getJSONObject("links"));
        details = launch.getString("details");
    }

    public Integer getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getLaunchYear() {
        return launchYear;
    }

    public void setLaunchYear(String launchYear) {
        this.launchYear = launchYear;
    }

    public Integer getLaunchDateUnix() {
        return launchDateUnix;
    }

    public void setLaunchDateUnix(Integer launchDateUnix) {
        this.launchDateUnix = launchDateUnix;
    }

    public String getLaunchDateUtc() {
        return launchDateUtc;
    }

    public void setLaunchDateUtc(String launchDateUtc) {
        this.launchDateUtc = launchDateUtc;
    }

    public String getLaunchDateLocal() {
        return launchDateLocal;
    }

    public void setLaunchDateLocal(String launchDateLocal) {
        this.launchDateLocal = launchDateLocal;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }


    public LaunchSite getLaunchSite() {
        return launchSite;
    }

    public void setLaunchSite(LaunchSite launchSite) {
        this.launchSite = launchSite;
    }

    public Boolean getLaunchSuccess() {
        return launchSuccess;
    }

    public void setLaunchSuccess(Boolean launchSuccess) {
        this.launchSuccess = launchSuccess;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setIcon(Bitmap icon){
        this.icon = icon;
    }

    public Bitmap getIcon(){
        return icon;
    }

}
