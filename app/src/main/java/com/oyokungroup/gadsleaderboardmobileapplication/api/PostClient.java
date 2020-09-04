package com.oyokungroup.gadsleaderboardmobileapplication.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class PostClient {

    public static final String BASE_URL = "https://docs.google.com/forms/d/e/";
    public static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit == null){
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                        .readTimeout(60, TimeUnit.SECONDS)
                                        .writeTimeout(60, TimeUnit.SECONDS)
                                        .connectTimeout(60, TimeUnit.SECONDS)
                                        .build();
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create().asLenient())
                    .build();
        }
        return retrofit;
    }
}
