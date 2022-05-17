package com.example.retrofit;

import android.app.Application;

import com.example.retrofit.data.network.RetrofitClient;
import com.example.retrofit.data.network.apiservices.CharacterApiServices;
import com.example.retrofit.data.network.apiservices.EpisodeApiServices;
import com.example.retrofit.data.network.apiservices.LocationApiServices;

public class App extends Application {

    public static CharacterApiServices characterApiServices;
    public static LocationApiServices locationApiServices;
    public static EpisodeApiServices episodeApiServices;
    private RetrofitClient retrofitClient;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient = new RetrofitClient();
        characterApiServices = retrofitClient.provideCharacterApiService();
        locationApiServices = retrofitClient.provideLocationApiServices();
        episodeApiServices = retrofitClient.provideEpisodeApiServices();
    }
}
