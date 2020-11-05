package com.example.watsontranslate.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.watsontranslate.network.data.Language;

import java.util.List;

@Dao
public interface LanguageDao {

    @Insert
    void insetLanguages(List<LanguageEntity> langs);

    @Query("SELECT * FROM languages")
    List<Language> getAllLanguages();

    @Query("SELECT * FROM languages WHERE id = :id")
    Language getLanguageById(int id);
}
