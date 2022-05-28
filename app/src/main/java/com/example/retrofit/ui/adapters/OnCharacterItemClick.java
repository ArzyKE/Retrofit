package com.example.retrofit.ui.adapters;

import com.example.retrofit.model.CharacterModel;

public interface OnCharacterItemClick {
    default void onItemClick(CharacterModel model) {

    }
}
