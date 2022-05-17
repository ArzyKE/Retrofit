package com.example.retrofit.data.network.apiservices;

import com.example.retrofit.model.CharacterModel;
import com.example.retrofit.model.LocationModel;
import com.example.retrofit.model.RiskyAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LocationApiServices {
    @GET("api/location")
    Call<RiskyAndMortyResponse<LocationModel>> fetchLocations();
}
