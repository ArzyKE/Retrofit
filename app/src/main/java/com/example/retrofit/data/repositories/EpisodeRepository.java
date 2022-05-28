package com.example.retrofit.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofit.App;
import com.example.retrofit.model.EpisodeModel;
import com.example.retrofit.model.RiskyAndMortyResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeRepository {

    public MutableLiveData<RiskyAndMortyResponse<EpisodeModel>> getList(int page) {
        MutableLiveData<RiskyAndMortyResponse<EpisodeModel>> data = new MutableLiveData<>();
        App.episodeApiServices.fetchEpisode(page).enqueue(new Callback<RiskyAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onResponse(Call<RiskyAndMortyResponse<EpisodeModel>> call, Response<RiskyAndMortyResponse<EpisodeModel>> response) {
                if (response.body() != null) {
                    App.episodeDao.insertAll(response.body().getResult());
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RiskyAndMortyResponse<EpisodeModel>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }


    public ArrayList<EpisodeModel> getEpisode() {
        ArrayList<EpisodeModel> list = new ArrayList<>();
        list.addAll(App.episodeDao.getAll());
        return list;
    }

    public MutableLiveData<EpisodeModel> fetchEpisodeId(int id) {
        MutableLiveData<EpisodeModel> mutableLiveDataID = new MutableLiveData<>();
        App.episodeApiServices.fetchEpisode(id).equals(new Callback<EpisodeModel>() {
            @Override
            public void onResponse(Call<EpisodeModel> call, Response<EpisodeModel> response) {
                if (response.body() != null) {
                    mutableLiveDataID.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<EpisodeModel> call, Throwable t) {
                mutableLiveDataID.setValue(null);
            }
        });
        return mutableLiveDataID;
    }
}
