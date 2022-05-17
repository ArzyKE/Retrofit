package com.example.retrofit.model;

import android.icu.text.IDNA;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RiskyAndMortyResponse<T> {

    @SerializedName("results")
    private List<T> result;

    @SerializedName("info")
    private InfoModel info;

    public List<T> getResult() {
        return result;
    }

    public InfoModel getInfo() {
        return info;
    }
}
