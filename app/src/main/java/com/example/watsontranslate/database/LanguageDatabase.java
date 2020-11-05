package com.example.watsontranslate.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {LanguageEntity.class}, exportSchema = false)
public abstract class LanguageDatabase extends RoomDatabase {

    private static volatile LanguageDatabase instance;
    private static Object lock = new Object();

    public static LanguageDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, LanguageDatabase.class, "languages_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }

    public abstract LanguageDao languageDao();
}
