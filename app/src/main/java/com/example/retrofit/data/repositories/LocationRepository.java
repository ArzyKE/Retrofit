package com.example.retrofit.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofit.App;
import com.example.retrofit.model.LocationModel;
import com.example.retrofit.model.RiskyAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRepository {

    public MutableLiveData<RiskyAndMortyResponse<LocationModel>> fetchLocation(int page) {
        MutableLiveData<RiskyAndMortyResponse<LocationModel>> data = new MutableLiveData<>();
        App.locationApiServices.fetchLocations(page).enqueue(new Callback<RiskyAndMortyResponse<LocationModel>>() {
            @Override
            public void onResponse(Call<RiskyAndMortyResponse<LocationModel>> call, Response<RiskyAndMortyResponse<LocationModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RiskyAndMortyResponse<LocationModel>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
