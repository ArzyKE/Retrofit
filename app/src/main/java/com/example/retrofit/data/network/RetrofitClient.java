package com.example.retrofit.data.network;

import com.example.retrofit.data.network.apiservices.CharacterApiServices;
import com.example.retrofit.data.network.apiservices.EpisodeApiServices;
import com.example.retrofit.data.network.apiservices.LocationApiServices;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private OkHttpClient okHttpClient = new OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();

    private HttpLoggingInterceptor loggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public CharacterApiServices provideCharacterApiService() {
        return retrofit.create(CharacterApiServices.class);
    }

    public LocationApiServices provideLocationApiServices() {
        return retrofit.create(LocationApiServices.class);
    }

    public EpisodeApiServices provideEpisodeApiServices() {
        return retrofit.create(EpisodeApiServices.class);
    }
}
