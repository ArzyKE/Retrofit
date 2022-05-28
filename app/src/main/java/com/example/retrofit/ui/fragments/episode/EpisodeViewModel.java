package com.example.retrofit.ui.fragments.episode;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit.App;
import com.example.retrofit.data.repositories.EpisodeRepository;
import com.example.retrofit.model.EpisodeModel;
import com.example.retrofit.model.RiskyAndMortyResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeViewModel extends ViewModel {

    private EpisodeRepository episodeRepository = new EpisodeRepository();
    public int page = 1;


    public MutableLiveData<RiskyAndMortyResponse<EpisodeModel>> getList(){
        return episodeRepository.getList(page);
    }
    public ArrayList <EpisodeModel> episodeModelArrayList = new ArrayList<>();
}
