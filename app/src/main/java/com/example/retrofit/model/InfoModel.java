package com.example.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class InfoModel {
    @SerializedName("count")
    private int count;

    @SerializedName("pages")
    private int pages;

    @SerializedName("next")
    private String next;

    @SerializedName("prev")
    private String prev;
}
