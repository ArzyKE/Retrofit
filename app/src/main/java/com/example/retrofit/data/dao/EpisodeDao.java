package com.example.retrofit.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.retrofit.model.EpisodeModel;

import java.util.ArrayList;
import java.util.List;
@Dao
public interface EpisodeDao {
    @Query("SELECT * FROM episodeModel")
    List<EpisodeModel> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<EpisodeModel> episodeModels);

}
