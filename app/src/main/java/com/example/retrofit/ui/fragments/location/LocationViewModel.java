package com.example.retrofit.ui.fragments.location;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit.data.repositories.LocationRepository;
import com.example.retrofit.model.LocationModel;
import com.example.retrofit.model.RiskyAndMortyResponse;

public class LocationViewModel extends ViewModel {

    private LocationRepository locationRepository = new LocationRepository();

    public int page = 1;

    public MutableLiveData<RiskyAndMortyResponse<LocationModel>> fetchLocation() {
        return locationRepository.fetchLocation(page);
    }
}
