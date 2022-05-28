package com.example.retrofit.ui.fragments.character;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit.data.repositories.CharacterRepository;
import com.example.retrofit.model.CharacterModel;
import com.example.retrofit.model.RiskyAndMortyResponse;

public class CharacterViewModel extends ViewModel {

    private CharacterRepository characterRepository = new CharacterRepository();

    public int page = 1;

    public MutableLiveData<RiskyAndMortyResponse<CharacterModel>> fetchCharacters() {
        return characterRepository.fetchCharacters(page);
    }
}
