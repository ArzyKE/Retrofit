package com.example.retrofit.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofit.App;
import com.example.retrofit.model.CharacterModel;
import com.example.retrofit.model.RiskyAndMortyResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterRepository {


    public MutableLiveData<RiskyAndMortyResponse<CharacterModel>> fetchCharacters(int page) {
        MutableLiveData<RiskyAndMortyResponse<CharacterModel>> data = new MutableLiveData<>();
        App.characterApiServices.fetchCharacters(page).enqueue(new Callback<RiskyAndMortyResponse<CharacterModel>>() {
            @Override
            public void onResponse(Call<RiskyAndMortyResponse<CharacterModel>> call, Response<RiskyAndMortyResponse<CharacterModel>> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RiskyAndMortyResponse<CharacterModel>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public ArrayList<CharacterModel> getCharacters() {
        ArrayList<CharacterModel> list = new ArrayList<>();
        list.addAll(App.characterDao.getAll());
        return list;
    }

    public MutableLiveData<CharacterModel> fetchCharacterId(int id) {
        MutableLiveData<CharacterModel> mutableLiveDataID = new MutableLiveData<>();
        App.characterApiServices.fetchCharacter(id).enqueue(new Callback<CharacterModel>() {
            @Override
            public void onResponse(Call<CharacterModel> call, Response<CharacterModel> response) {
                if (response.body() != null) {
                    mutableLiveDataID.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CharacterModel> call, Throwable t) {
                mutableLiveDataID.setValue(null);

            }
        });
        return mutableLiveDataID;
    }

}
