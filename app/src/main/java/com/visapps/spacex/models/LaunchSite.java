package com.visapps.spacex.models;

import org.json.JSONException;
import org.json.JSONObject;

public class LaunchSite {

    private String siteId;
    private String siteName;
    private String siteNameLong;

    public LaunchSite(JSONObject launchsite) throws JSONException{
        siteId = launchsite.getString("site_id");
        siteName = launchsite.getString("site_name");
        siteNameLong = launchsite.getString("site_name_long");
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteNameLong() {
        return siteNameLong;
    }

    public void setSiteNameLong(String siteNameLong) {
        this.siteNameLong = siteNameLong;
    }

}
