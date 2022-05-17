package com.example.retrofit.ui.fragments.episode;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.retrofit.R;
import com.example.retrofit.base.BaseFragment;
import com.example.retrofit.databinding.FragmentEpisodeBinding;
import com.example.retrofit.model.EpisodeModel;
import com.example.retrofit.model.RiskyAndMortyResponse;
import com.example.retrofit.ui.adapters.EpisodeAdapter;
import com.example.retrofit.ui.fragments.location.LocationViewModel;

public class EpisodeFragment extends BaseFragment<FragmentEpisodeBinding> {

    private FragmentEpisodeBinding binding;
    private EpisodeViewModel viewModel;
    private EpisodeAdapter episodeAdapter = new EpisodeAdapter(EpisodeAdapter.diffCallBack);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(EpisodeViewModel.class);
        return binding.getRoot();
    }

    @Override
    protected void setupViews() {
        binding.episodeResView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.episodeResView.setAdapter(episodeAdapter);
    }

    @Override
    protected void setupReguest() {
        viewModel.getEpisodeList();
    }

    @Override
    protected void setupObserve() {
        viewModel.data.observe(getViewLifecycleOwner(), new Observer<RiskyAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onChanged(RiskyAndMortyResponse<EpisodeModel> episodeModelRiskyAndMortyResponse) {
                episodeAdapter.submitList(episodeModelRiskyAndMortyResponse.getResult());
            }
        });
    }
}