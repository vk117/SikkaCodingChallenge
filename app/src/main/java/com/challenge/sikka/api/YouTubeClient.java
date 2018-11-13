package com.challenge.sikka.api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

public class YouTubeClient  {

    static final String BASE_URL = "https://www.googleapis.com/youtube/v3/";

    public YouTubeApi start(){
        Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

        YouTubeApi api = retrofit.create(YouTubeApi.class);

        return api;
    }


}
