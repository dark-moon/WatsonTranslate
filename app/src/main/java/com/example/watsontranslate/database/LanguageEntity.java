package com.example.watsontranslate.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.watsontranslate.network.data.Language;

@Entity(tableName = "languages")
public class LanguageEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String language;
    private String language_name;
    private String native_language_name;
    private String country_code;
    private boolean words_separated;
    private String direction;
    private boolean supported_as_source;
    private boolean supported_as_target;
    private boolean identifiable;

    public LanguageEntity() { }

    @Ignore
    public LanguageEntity(Language language) {
        this.language = language.getLanguage();
        this.language_name = language.getLanguage_name();
        this.native_language_name = language.getNative_language_name();
        this.country_code = language.getCountry_code();
        this.words_separated = language.isWords_separated();
        this.direction = language.getDirection();
        this.supported_as_source = language.isSupported_as_source();
        this.supported_as_target = language.isSupported_as_target();
        this.identifiable = language.isIdentifiable();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage_name() {
        return language_name;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }

    public String getNative_language_name() {
        return native_language_name;
    }

    public void setNative_language_name(String native_language_name) {
        this.native_language_name = native_language_name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public boolean isWords_separated() {
        return words_separated;
    }

    public void setWords_separated(boolean words_separated) {
        this.words_separated = words_separated;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean isSupported_as_source() {
        return supported_as_source;
    }

    public void setSupported_as_source(boolean supported_as_source) {
        this.supported_as_source = supported_as_source;
    }

    public boolean isSupported_as_target() {
        return supported_as_target;
    }

    public void setSupported_as_target(boolean supported_as_target) {
        this.supported_as_target = supported_as_target;
    }

    public boolean isIdentifiable() {
        return identifiable;
    }

    public void setIdentifiable(boolean identifiable) {
        this.identifiable = identifiable;
    }
}
