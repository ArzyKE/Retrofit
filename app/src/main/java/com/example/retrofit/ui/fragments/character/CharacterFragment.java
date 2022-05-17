package com.example.retrofit.ui.fragments.character;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.retrofit.base.BaseFragment;
import com.example.retrofit.databinding.FragmentCharacterBinding;
import com.example.retrofit.model.CharacterModel;
import com.example.retrofit.model.RiskyAndMortyResponse;
import com.example.retrofit.ui.adapters.CharacterAdapter;

public class CharacterFragment extends BaseFragment<FragmentCharacterBinding> {

    private FragmentCharacterBinding binding;
    private CharacterViewModel viewModel;
    private CharacterAdapter characterAdapter = new CharacterAdapter(CharacterAdapter.diffCallBack);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        return binding.getRoot();
    }

    @Override
    protected void setupViews() {
        binding.characterRecView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.characterRecView.setAdapter(characterAdapter);
    }

    @Override
    protected void setupReguest() {
        viewModel.getList();
    }

    @Override
    protected void setupObserve() {
        viewModel.data.observe(getViewLifecycleOwner(), new Observer<RiskyAndMortyResponse<CharacterModel>>() {
            @Override
            public void onChanged(RiskyAndMortyResponse<CharacterModel> characterModelRiskyAndMortyResponse) {
                characterAdapter.submitList(characterModelRiskyAndMortyResponse.getResult());
            }
        });
    }
}