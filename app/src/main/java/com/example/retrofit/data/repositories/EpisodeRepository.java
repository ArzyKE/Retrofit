package com.example.retrofit.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofit.App;
import com.example.retrofit.model.EpisodeModel;
import com.example.retrofit.model.RiskyAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeRepository {

    public MutableLiveData<RiskyAndMortyResponse<EpisodeModel>> fetchEpisode(int page) {
        MutableLiveData<RiskyAndMortyResponse<EpisodeModel>> data = new MutableLiveData<>();
        App.episodeApiServices.fetchEpisode(page).enqueue(new Callback<RiskyAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onResponse(Call<RiskyAndMortyResponse<EpisodeModel>> call, Response<RiskyAndMortyResponse<EpisodeModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RiskyAndMortyResponse<EpisodeModel>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
