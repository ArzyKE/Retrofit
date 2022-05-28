package com.example.retrofit.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "characterModel")
public class CharacterModel {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    @SerializedName("name")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @SerializedName("image")
    private String image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
