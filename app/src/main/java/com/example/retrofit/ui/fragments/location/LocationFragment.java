package com.example.retrofit.ui.fragments.location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.base.BaseFragment;
import com.example.retrofit.databinding.FragmentLocationBinding;
import com.example.retrofit.model.LocationModel;
import com.example.retrofit.model.RiskyAndMortyResponse;
import com.example.retrofit.ui.adapters.LocationAdapter;

public class LocationFragment extends BaseFragment<FragmentLocationBinding> {


    private LocationViewModel viewModel;
    private LinearLayoutManager linearLayoutManager;
    private boolean loading = true;
    private int pastVisible, visibleCount, totalCount;
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
        linearLayoutManager = new LinearLayoutManager(requireContext());
        binding.locationRecView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.locationRecView.setAdapter(locationAdapter);

        binding.locationRecView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleCount = linearLayoutManager.getChildCount();
                    totalCount = linearLayoutManager.getItemCount();
                    pastVisible = linearLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleCount + pastVisible) >= totalCount) ;
                        viewModel.page++;
                        loading = false;

                        viewModel.fetchLocation().observe(getViewLifecycleOwner(), new Observer<RiskyAndMortyResponse<LocationModel>>() {
                            @Override
                            public void onChanged(RiskyAndMortyResponse<LocationModel> locationModelRiskyAndMortyResponse) {
                                locationAdapter.submitList(locationModelRiskyAndMortyResponse.getResult());
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    protected void setupReguest() {
        viewModel.fetchLocation().observe(getViewLifecycleOwner(), new Observer<RiskyAndMortyResponse<LocationModel>>() {
            @Override
            public void onChanged(RiskyAndMortyResponse<LocationModel> locationModelRiskyAndMortyResponse) {
                locationAdapter.submitList(locationModelRiskyAndMortyResponse.getResult());
            }
        });
    }
}