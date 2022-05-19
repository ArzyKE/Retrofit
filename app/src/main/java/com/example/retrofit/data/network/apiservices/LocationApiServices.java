package com.example.retrofit.data.network.apiservices;

import com.example.retrofit.model.LocationModel;
import com.example.retrofit.model.RiskyAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocationApiServices {
    @GET("api/location")
    Call<RiskyAndMortyResponse<LocationModel>> fetchLocations(
            @Query("page")
                    int page
    );
}
