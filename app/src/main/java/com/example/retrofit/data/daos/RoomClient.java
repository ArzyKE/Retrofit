package com.example.retrofit.data.daos;

import android.content.Context;

import androidx.room.Room;

import com.example.retrofit.data.dao.CharacterDao;
import com.example.retrofit.data.dao.EpisodeDao;
import com.example.retrofit.data.dao.LocationDao;

public class RoomClient {
    public AppDataBase provideRoom(Context context) {

        return Room.databaseBuilder(context, AppDataBase.class, "app_database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public CharacterDao provideCharacterDao(AppDataBase appDatabase) {
        return appDatabase.characterDao();
    }

    public EpisodeDao provideEpisodeDao(AppDataBase appDatabase) {
        return appDatabase.episodeDao();

    }

    public LocationDao provideLocationDao(AppDataBase appDatabase) {
        return appDatabase.locationDao();
    }

}

