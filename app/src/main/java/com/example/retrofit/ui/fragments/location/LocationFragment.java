package com.example.retrofit.ui.fragments.location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.retrofit.base.BaseFragment;
import com.example.retrofit.databinding.FragmentLocationBinding;
import com.example.retrofit.model.LocationModel;
import com.example.retrofit.model.RiskyAndMortyResponse;
import com.example.retrofit.ui.adapters.LocationAdapter;

public class LocationFragment extends BaseFragment<FragmentLocationBinding> {


    private LocationViewModel viewModel;
    private LocationAdapter locationAdapter = new LocationAdapter(LocationAdapter.diffCallBack);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        return binding.getRoot();
    }

    @Override
    protected void setupViews() {
        binding.locationRecView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.locationRecView.setAdapter(locationAdapter);
    }

    @Override
    protected void setupReguest() {
        viewModel.getLocationList();
    }

    @Override
    protected void setupObserve() {
        viewModel.data.observe(getViewLifecycleOwner(), new Observer<RiskyAndMortyResponse<LocationModel>>() {
            @Override
            public void onChanged(RiskyAndMortyResponse<LocationModel> locationModelRiskyAndMortyResponse) {
                locationAdapter.submitList(locationModelRiskyAndMortyResponse.getResult());
            }
        });
    }
}