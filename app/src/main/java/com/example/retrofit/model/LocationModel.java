package com.example.retrofit.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity (tableName = "locationModel")
public class LocationModel {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    @SerializedName("name")
    private String name;

    @SerializedName("dimension")
    private String dimension;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDimension() {
        return dimension;
    }
}
