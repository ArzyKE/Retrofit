package com.example.retrofit.data.network.apiservices;

import com.example.retrofit.model.EpisodeModel;
import com.example.retrofit.model.RiskyAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EpisodeApiServices {
    @GET("api/episode")
    Call<RiskyAndMortyResponse<EpisodeModel>>fetchEpisode();
}
