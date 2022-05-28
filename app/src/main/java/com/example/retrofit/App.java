package com.example.retrofit;

import android.app.Application;

import com.example.retrofit.data.dao.CharacterDao;
import com.example.retrofit.data.dao.EpisodeDao;
import com.example.retrofit.data.dao.LocationDao;
import com.example.retrofit.data.daos.RoomClient;
import com.example.retrofit.data.network.RetrofitClient;
import com.example.retrofit.data.network.apiservices.CharacterApiServices;
import com.example.retrofit.data.network.apiservices.EpisodeApiServices;
import com.example.retrofit.data.network.apiservices.LocationApiServices;

public class App extends Application {

    public static CharacterApiServices characterApiServices;
    public static LocationApiServices locationApiServices;
    public static CharacterDao characterDao;
    public static LocationDao locationDao;
    public static EpisodeDao episodeDao;
    public static EpisodeApiServices episodeApiServices;
    private static RetrofitClient retrofitClient;
    private static RoomClient roomClient;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient = new RetrofitClient();
        characterApiServices = retrofitClient.provideCharacterApiService();
        locationApiServices = retrofitClient.provideLocationApiServices();
        episodeApiServices = retrofitClient.provideEpisodeApiServices();
        roomClient = new RoomClient();
        characterDao = roomClient.provideCharacterDao(roomClient.provideRoom(getApplicationContext()));
        episodeDao = roomClient.provideEpisodeDao(roomClient.provideRoom(getApplicationContext()));
        locationDao = roomClient.provideLocationDao(roomClient.provideRoom(getApplicationContext()));
    }
}
