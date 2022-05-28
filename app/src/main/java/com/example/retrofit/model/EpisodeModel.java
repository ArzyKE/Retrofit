package com.example.retrofit.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "episodeModel")
public class EpisodeModel {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    @SerializedName("name")
    private String name;

    @SerializedName("air_date")
    private String air_date;

    @SerializedName("episode")
    private String episode;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAir_date() {
        return air_date;
    }

    public String getEpisode() {
        return episode;
    }
}
