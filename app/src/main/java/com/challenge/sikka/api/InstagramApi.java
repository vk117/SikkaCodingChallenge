package com.challenge.sikka.api;

import com.challenge.sikka.models.Example2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InstagramApi {

    @GET("recent?access_token=8802462623.ba4c844.0faf4614002847648b4e724a3e4ffe94")
    Call<Example2> listOfPics();
}
