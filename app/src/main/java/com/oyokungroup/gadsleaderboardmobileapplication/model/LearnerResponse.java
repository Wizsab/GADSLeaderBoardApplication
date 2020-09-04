package com.oyokungroup.gadsleaderboardmobileapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LearnerResponse {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("hours")
    private int hours;
    @SerializedName("badgeUrl")
    private String badgeUrl;
    @SerializedName("country")
    private String country;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
    public String getBadgeUrl() {
        return "https://res.cloudinary.com/mikeattara/image/upload/v1596700848/Top-learner.png";
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
