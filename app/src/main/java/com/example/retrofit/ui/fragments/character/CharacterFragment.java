package com.example.retrofit.ui.fragments.character;

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
import com.example.retrofit.databinding.FragmentCharacterBinding;
import com.example.retrofit.model.CharacterModel;
import com.example.retrofit.model.RiskyAndMortyResponse;
import com.example.retrofit.ui.adapters.CharacterAdapter;

import java.util.ArrayList;

public class CharacterFragment extends BaseFragment<FragmentCharacterBinding> {

    private FragmentCharacterBinding binding;
    private CharacterViewModel viewModel;
    private LinearLayoutManager linearLayoutManager;
    private boolean loading = true;
    private int pastVisible, visibleCount, totalCount;
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
        linearLayoutManager = new LinearLayoutManager(requireContext());
        binding.characterRecView.setLayoutManager(linearLayoutManager);
        binding.characterRecView.setAdapter(characterAdapter);

        binding.characterRecView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleCount = linearLayoutManager.getChildCount();
                    totalCount = linearLayoutManager.getItemCount();
                    pastVisible = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleCount + pastVisible) >= totalCount) {
                        if (viewModel.page != totalCount && (viewModel.page < totalCount)) {
                            viewModel.page++;
                            if (!loading && (viewModel.page < totalCount))
                                loading = true;
                            fetchCharacters();
                            loading = false;
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void setupReguest() {
        viewModel.fetchCharacters().observe(getViewLifecycleOwner(), new Observer<RiskyAndMortyResponse<CharacterModel>>() {
            @Override
            public void onChanged(RiskyAndMortyResponse<CharacterModel> characterModelRiskyAndMortyResponse) {
                characterAdapter.submitList(characterModelRiskyAndMortyResponse.getResult());
            }
        });
    }

    private void fetchCharacters() {
        if (isNetwork()) {
            viewModel.fetchCharacters().observe(getViewLifecycleOwner(), new Observer<RiskyAndMortyResponse<CharacterModel>>() {
                @Override
                public void onChanged(RiskyAndMortyResponse<CharacterModel> characterModelRiskyAndMortyResponse) {
                    if (!loading) {
                        ArrayList<CharacterModel> list = new ArrayList(characterAdapter.getCurrentList());
                        list.addAll(characterModelRiskyAndMortyResponse.getResult());
                        characterAdapter.submitList(list);
                        if (list != characterAdapter.getCurrentList()) {
                            characterAdapter.submitList(list);
                        }
                    }
                }
            });
        }
    }

    private boolean isNetwork() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

//    @Override
//    protected void setupListener() {
//        characterAdapter.setOnItemClick(new OnItemClick() {
//            @Override
//            public void onItemClick(int position) {
//                Navigation.findNavController(requireView()).navigate(
//                        (NavDirections) CharacterFragmentDirections
//                );
//            }
//        });
}
