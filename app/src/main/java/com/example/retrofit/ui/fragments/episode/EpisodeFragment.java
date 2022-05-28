package com.example.retrofit.ui.fragments.episode;

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
import com.example.retrofit.databinding.FragmentEpisodeBinding;
import com.example.retrofit.model.EpisodeModel;
import com.example.retrofit.model.RiskyAndMortyResponse;
import com.example.retrofit.ui.adapters.EpisodeAdapter;

import java.util.ArrayList;
import java.util.List;

public class EpisodeFragment extends BaseFragment<FragmentEpisodeBinding> {

    private FragmentEpisodeBinding binding;
    private EpisodeViewModel episodeViewModel;
    private LinearLayoutManager linearLayoutManager;
    private boolean loading = true;
    private int pastVisible, visibleCount, totalCount;
    private EpisodeAdapter episodeAdapter = new EpisodeAdapter(EpisodeAdapter.diffCallBack);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false);
        episodeViewModel = new ViewModelProvider(requireActivity()).get(EpisodeViewModel.class);
        return binding.getRoot();
    }

    @Override
    protected void setupViews() {
        linearLayoutManager = new LinearLayoutManager(requireContext());
        binding.episodeResView.setLayoutManager(linearLayoutManager);
        binding.episodeResView.setAdapter(episodeAdapter);

        binding.episodeResView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleCount = linearLayoutManager.getChildCount();
                    totalCount = linearLayoutManager.getItemCount();
                    pastVisible = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleCount + pastVisible) >= totalCount) {
                        if (episodeViewModel.page != totalCount && (episodeViewModel.page < totalCount)) {
                            episodeViewModel.page++;
                            if (!loading && (episodeViewModel.page < totalCount))
                                //   loading = true;
                                fetchEpisode();
                            //  loading = false;
                        }
                    }
                }
            }
        });
    }

//    @Override
//    protected void setupReguest() {
//        episodeViewModel.getList().observe(getViewLifecycleOwner(), new Observer<RiskyAndMortyResponse<EpisodeModel>>() {
//            @Override
//            public void onChanged(RiskyAndMortyResponse<EpisodeModel> episodeModelRiskyAndMortyResponse) {
//                episodeAdapter.submitList(episodeModelRiskyAndMortyResponse.getResult());
//            }
//        });

    // }

    private void fetchEpisode() {
        if (isNetwork()) {
            episodeViewModel.getList().observe(getViewLifecycleOwner(), new Observer<RiskyAndMortyResponse<EpisodeModel>>() {
                @Override
                public void onChanged(RiskyAndMortyResponse<EpisodeModel> episodeModelRiskyAndMortyResponse) {
                    if (!loading) {
                        ArrayList<EpisodeModel> list = new ArrayList(episodeAdapter.getCurrentList());
                        list.addAll(episodeModelRiskyAndMortyResponse.getResult());
                        episodeAdapter.submitList(list);
                        if (list != episodeAdapter.getCurrentList()) {
                            episodeAdapter.submitList(list);
                        }

                    }
                }

            });

        } else
            episodeAdapter.submitList((List<EpisodeModel>) episodeViewModel.getList());
    }

    private boolean isNetwork() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        episodeViewModel.page = 1;
        binding = null;
        loading = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        episodeViewModel.getList().observe(getViewLifecycleOwner(), new Observer<RiskyAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onChanged(RiskyAndMortyResponse<EpisodeModel> episodeModelRiskyAndMortyResponse) {
                if (loading) {
                    ArrayList<EpisodeModel> list = new ArrayList<>(episodeAdapter.getCurrentList());
                    list.addAll(episodeModelRiskyAndMortyResponse.getResult());
                    episodeAdapter.submitList(list);
                    loading = false;

                }
            }
        });
    }
}