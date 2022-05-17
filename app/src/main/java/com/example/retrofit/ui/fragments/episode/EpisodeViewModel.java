package com.example.retrofit.ui.fragments.episode;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit.App;
import com.example.retrofit.model.EpisodeModel;
import com.example.retrofit.model.RiskyAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeViewModel extends ViewModel {
    public MutableLiveData<RiskyAndMortyResponse<EpisodeModel>>data = new MutableLiveData<>();

    public void getEpisodeList(){
        App.episodeApiServices.fetchEpisode().enqueue(new Callback<RiskyAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onResponse(Call<RiskyAndMortyResponse<EpisodeModel>> call, Response<RiskyAndMortyResponse<EpisodeModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RiskyAndMortyResponse<EpisodeModel>> call, Throwable t) {
           data.setValue(null);
            }
        });
    }
}
