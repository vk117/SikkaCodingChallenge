package com.challenge.sikka.api;

import com.challenge.sikka.models.Example;


import retrofit2.Call;
import retrofit2.http.GET;

public interface YouTubeApi {

    @GET("playlistItems?playlistId=UU_-I_H2iR4MaAghRSDzVBCQ&maxResults=25&part=snippet%2CcontentDetails&key=AIzaSyB9upIxNC2LjANOzh4r28BezoZMsV1FIWA")
    Call<Example> listOfVideos();
}
