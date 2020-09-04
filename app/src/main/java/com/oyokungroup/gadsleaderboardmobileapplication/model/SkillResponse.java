package com.oyokungroup.gadsleaderboardmobileapplication.model;

import com.google.gson.annotations.SerializedName;

public class SkillResponse {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("score")
    private int score;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getBadgeUrl() {
        return "https://res.cloudinary.com/mikeattara/image/upload/v1596700835/skill-IQ-trimmed.png";
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
