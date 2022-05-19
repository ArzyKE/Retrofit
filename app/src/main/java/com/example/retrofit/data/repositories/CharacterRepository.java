package com.example.retrofit.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofit.App;
import com.example.retrofit.model.CharacterModel;
import com.example.retrofit.model.RiskyAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterRepository {


    public MutableLiveData<RiskyAndMortyResponse<CharacterModel>> fetchCharacters(int page) {
        MutableLiveData<RiskyAndMortyResponse<CharacterModel>> data = new MutableLiveData<>();
        App.characterApiServices.fetchCharacters(page).enqueue(new Callback<RiskyAndMortyResponse<CharacterModel>>() {
            @Override
            public void onResponse(Call<RiskyAndMortyResponse<CharacterModel>> call, Response<RiskyAndMortyResponse<CharacterModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RiskyAndMortyResponse<CharacterModel>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
