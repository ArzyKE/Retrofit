package com.example.retrofit.ui.fragments.location;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit.App;
import com.example.retrofit.model.LocationModel;
import com.example.retrofit.model.RiskyAndMortyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationViewModel extends ViewModel {

    public MutableLiveData<RiskyAndMortyResponse<LocationModel>> data = new MutableLiveData<>();

    public void getLocationList() {
        App.locationApiServices.fetchLocations().enqueue(new Callback<RiskyAndMortyResponse<LocationModel>>() {
            @Override
            public void onResponse(Call<RiskyAndMortyResponse<LocationModel>> call, Response<RiskyAndMortyResponse<LocationModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RiskyAndMortyResponse<LocationModel>> call, Throwable t) {
                data.setValue(null);
            }
        });
    }

}
