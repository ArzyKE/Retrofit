package com.example.retrofit.data.network.apiservices;

import com.example.retrofit.model.CharacterModel;
import com.example.retrofit.model.RiskyAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CharacterApiServices {

    @GET("api/character")
    Call<RiskyAndMortyResponse<CharacterModel>> fetchCharacters(
            @Query("page")
                    int page
    );

    @GET("api/character/{id}")
    Call<CharacterModel> fetchCharacter(
            @Path("id") int id);
}

