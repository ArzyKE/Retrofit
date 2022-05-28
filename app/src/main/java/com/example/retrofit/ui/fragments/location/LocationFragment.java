package com.example.retrofit.ui.fragments.location;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import java.util.ArrayList;
import java.util.List;

public class LocationFragment extends BaseFragment<FragmentLocationBinding> {


    private LocationViewModel locationViewModel;
    private LinearLayoutManager linearLayoutManager;
    private boolean loading = true;
    private int pastVisible, visibleCount, totalCount;
    private LocationAdapter locationAdapter = new LocationAdapter(LocationAdapter.diffCallBack);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(inflater, container, false);
        locationViewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        return binding.getRoot();
    }

    @Override
    protected void setupViews() {
        linearLayoutManager = new LinearLayoutManager(requireContext());
        binding.locationRecView.setLayoutManager(linearLayoutManager);
        binding.locationRecView.setAdapter(locationAdapter);
    }

    @Override
    protected void setupListener() {
        binding.locationRecView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleCount = linearLayoutManager.getChildCount();
                    totalCount = linearLayoutManager.getItemCount();
                    pastVisible = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleCount + pastVisible) >= totalCount) {
                        if (locationViewModel.page != totalCount && (locationViewModel.page < totalCount)) {
                            locationViewModel.page++;
                            if (!loading && (locationViewModel.page < totalCount)) {

                                fethLocation();
                            }
                        }


                    }
                }

            }

        });
    }


    private void fethLocation() {
        if (isNetwork()) {
            locationViewModel.getList().observe(getViewLifecycleOwner(), new Observer<RiskyAndMortyResponse<LocationModel>>() {
                @Override
                public void onChanged(RiskyAndMortyResponse<LocationModel> locationModelRiskyAndMortyResponse) {
                    if (!loading) {
                        ArrayList<LocationModel> list = new ArrayList<>(locationAdapter.getCurrentList());
                        list.addAll(locationModelRiskyAndMortyResponse.getResult());
                        locationAdapter.submitList(list);

                    }
                }
            });
        } else
            locationAdapter.submitList((List<LocationModel>) locationViewModel.getList());
    }

    private boolean isNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loading = true;
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();

        locationViewModel.getList().observe(getViewLifecycleOwner(), new Observer<RiskyAndMortyResponse<LocationModel>>() {
            @Override
            public void onChanged(RiskyAndMortyResponse<LocationModel> locationModelRiskyAndMortyResponse) {
                if (loading) {
                    ArrayList<LocationModel> list = new ArrayList<>(locationAdapter.getCurrentList());
                    list.addAll(locationModelRiskyAndMortyResponse.getResult());
                    locationAdapter.submitList(list);
                    loading = false;
                }
            }
        });
    }
}

//
//    private void fethLocation() {
//        if (isNetwork())
//            locationViewModel.fetchLocation().observe(getViewLifecycleOwner(), new Observer<RiskyAndMortyResponse<LocationModel>>() {
//                @Override
//                public void onChanged(RiskyAndMortyResponse<LocationModel> locationModelRiskyAndMortyResponse) {
//                    locationAdapter.submitList(locationModelRiskyAndMortyResponse.getResult());
//                }
//            });
//    }
//});
//     }
//


//
//@Override
//protected void setupReguest(){
//        locationViewModel.fetchLocation().observe(getViewLifecycleOwner(),new Observer<RiskyAndMortyResponse<LocationModel>>(){
//@Override
//public void onChanged(RiskyAndMortyResponse<LocationModel> locationModelRiskyAndMortyResponse){
//        locationAdapter.submitList(locationModelRiskyAndMortyResponse.getResult());
//        }
//        });
//
//
//private boolean isNetwork(){
//        ConnectivityManager connectivityManager=
//        (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
//        return networkInfo!=null&&networkInfo.isConnected();
//        }
//        }