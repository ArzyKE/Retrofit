package com.example.retrofit.ui.fragments.character;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit.App;
import com.example.retrofit.model.CharacterModel;
import com.example.retrofit.model.RiskyAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterViewModel extends ViewModel {

    public MutableLiveData<RiskyAndMortyResponse<CharacterModel>> data = new MutableLiveData<>();

    public void getList(){
        App.characterApiServices.fetchCharacters().enqueue(new Callback<RiskyAndMortyResponse<CharacterModel>>() {
            @Override
            public void onResponse(Call<RiskyAndMortyResponse<CharacterModel>> call, Response<RiskyAndMortyResponse<CharacterModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RiskyAndMortyResponse<CharacterModel>> call, Throwable t) {
             data.setValue(null);
            }
        });
    }
}
