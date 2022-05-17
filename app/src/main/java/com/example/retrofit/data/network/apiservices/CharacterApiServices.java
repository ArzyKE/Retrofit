package com.example.retrofit.data.network.apiservices;

import com.example.retrofit.model.CharacterModel;
import com.example.retrofit.model.RiskyAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CharacterApiServices {
    @GET("api/character")
    Call<RiskyAndMortyResponse<CharacterModel>> fetchCharacters();
}
