package com.example.retrofit.ui.fragments.episode;

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

public class EpisodeFragment extends BaseFragment<FragmentEpisodeBinding> {

    private FragmentEpisodeBinding binding;
    private EpisodeViewModel viewModel;
    private LinearLayoutManager linearLayoutManager;
    private boolean loading = true;
    private int pastVisible, visibleCount, totalCount;
    private EpisodeAdapter episodeAdapter = new EpisodeAdapter(EpisodeAdapter.diffCallBack);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(EpisodeViewModel.class);
        return binding.getRoot();
    }

    @Override
    protected void setupViews() {
        linearLayoutManager = new LinearLayoutManager(requireContext());
        binding.episodeResView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.episodeResView.setAdapter(episodeAdapter);

        binding.episodeResView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleCount = linearLayoutManager.getChildCount();
                    totalCount = linearLayoutManager.getItemCount();
                    pastVisible = linearLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleCount + pastVisible) >= totalCount) ;
                        loading = false;


                        viewModel.page++;
                        viewModel.fetchEpisode().observe(getViewLifecycleOwner(), new Observer<RiskyAndMortyResponse<EpisodeModel>>() {
                            @Override
                            public void onChanged(RiskyAndMortyResponse<EpisodeModel> episodeModelRiskyAndMortyResponse) {
                                episodeAdapter.submitList(episodeModelRiskyAndMortyResponse.getResult());
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    protected void setupReguest() {
        viewModel.fetchEpisode().observe(getViewLifecycleOwner(), new Observer<RiskyAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onChanged(RiskyAndMortyResponse<EpisodeModel> episodeModelRiskyAndMortyResponse) {
                episodeAdapter.submitList(episodeModelRiskyAndMortyResponse.getResult());
            }
        });
    }
}