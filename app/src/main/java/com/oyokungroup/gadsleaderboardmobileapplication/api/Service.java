package com.oyokungroup.gadsleaderboardmobileapplication.api;

import com.oyokungroup.gadsleaderboardmobileapplication.model.LearnerResponse;
import com.oyokungroup.gadsleaderboardmobileapplication.model.Post;
import com.oyokungroup.gadsleaderboardmobileapplication.model.SkillResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Service {
    @GET("/api/hours")
    Call<List<LearnerResponse>> getLearners();

    @GET("/api/skilliq")
    Call<List<SkillResponse>> getSkillIqs();

}
