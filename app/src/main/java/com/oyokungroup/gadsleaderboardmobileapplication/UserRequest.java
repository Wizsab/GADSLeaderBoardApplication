package com.oyokungroup.gadsleaderboardmobileapplication;

public class UserRequest {

    private String first_name;
    private String last_name;
    private String email;
    private String github;

    public String getGithub() {
        return github;
    }

    public String setGithub(String github) {
        this.github = github;
        return github;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String setFirst_name(String first_name) {
        this.first_name = first_name;
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String setLast_name(String last_name) {
        this.last_name = last_name;
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail(String email) {
        this.email = email;
        return email;
    }
}
