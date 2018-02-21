package com.visapps.spacex.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Links {

    private String missionPatch;
    private String articleLink;
    private String videoLink;

    public Links(JSONObject links) throws JSONException{
        missionPatch = links.getString("mission_patch");
        articleLink = links.getString("article_link");
        videoLink = links.getString("video_link");
    }

    public String getMissionPatch() {
        return missionPatch;
    }

    public void setMissionPatch(String missionPatch) {
        this.missionPatch = missionPatch;
    }

    public String getArticleLink() {
        return articleLink;
    }

    public void setArticleLink(String articleLink) {
        this.articleLink = articleLink;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

}
