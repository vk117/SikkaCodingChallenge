package com.challenge.sikka.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;


public class InstagramClient {

    static final String BASE_URL = "https://api.instagram.com/v1/tags/sikkasoftware/media/";

    public InstagramApi start(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        InstagramApi api = retrofit.create(InstagramApi.class);

        return api;
    }

}
