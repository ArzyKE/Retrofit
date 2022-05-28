package com.example.retrofit.data.daos;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.retrofit.data.dao.CharacterDao;
import com.example.retrofit.data.dao.EpisodeDao;
import com.example.retrofit.data.dao.LocationDao;
import com.example.retrofit.model.CharacterModel;
import com.example.retrofit.model.EpisodeModel;
import com.example.retrofit.model.LocationModel;

@Database(entities = {CharacterModel.class, EpisodeModel.class, LocationModel.class}, version = 1,exportSchema = false)
abstract class AppDataBase extends RoomDatabase {
    public abstract CharacterDao characterDao();
    public abstract EpisodeDao episodeDao();
    public abstract LocationDao locationDao();


}
