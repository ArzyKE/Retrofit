package com.example.retrofit.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.retrofit.model.LocationModel;

import java.util.ArrayList;
import java.util.List;
@Dao
public interface LocationDao {
    @Query("SELECT * FROM locationModel")
    List<LocationModel> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<LocationModel> locationModels);
}
