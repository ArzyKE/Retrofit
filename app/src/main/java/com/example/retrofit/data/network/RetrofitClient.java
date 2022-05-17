package com.example.retrofit.data.network;

import com.example.retrofit.data.network.apiservices.CharacterApiServices;
import com.example.retrofit.data.network.apiservices.EpisodeApiServices;
import com.example.retrofit.data.network.apiservices.LocationApiServices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public CharacterApiServices provideCharacterApiService() {
        return retrofit.create(CharacterApiServices.class);
    }

    public LocationApiServices provideLocationApiServices() {
        return retrofit.create(LocationApiServices.class);
    }

    public EpisodeApiServices provideEpisodeApiServices() {
        return retrofit.create(EpisodeApiServices.class);
    }
}
