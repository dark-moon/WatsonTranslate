package com.example.watsontranslate;

import android.app.Application;

import com.example.watsontranslate.database.LanguageDao;
import com.example.watsontranslate.database.LanguageDatabase;
import com.example.watsontranslate.database.LanguageEntity;
import com.example.watsontranslate.network.TranslationBasicAuthInterceptor;
import com.example.watsontranslate.network.WatsonTranslateService;
import com.example.watsontranslate.network.data.Language;
import com.example.watsontranslate.network.data.LanguagesList;
import com.example.watsontranslate.tools.Constants;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LanguageRepository {

    private final LanguageDatabase database;
    private final OkHttpClient translationClient;
    private final Retrofit retrofit;
    private final WatsonTranslateService service;

    public LanguageRepository(Application application) {
        database = LanguageDatabase.getInstance(application);
        translationClient = new OkHttpClient.Builder()
                .addInterceptor(new TranslationBasicAuthInterceptor())
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.TRANSLATOR_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(translationClient)
                .build();
        service = retrofit.create(WatsonTranslateService.class);
    }

    public void getSupportedLanguagesRemote(OnLoadData<LanguagesList> callback) {
        service.getSupportedLanguages().enqueue(new Callback<LanguagesList>() {
            @Override
            public void onResponse(Call<LanguagesList> call, Response<LanguagesList> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callback.onSuccess(response.body());
                    }
                }
            }
            @Override
            public void onFailure(Call<LanguagesList> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void saveLanguagesInDatabase(List<Language> languages) {
        List<LanguageEntity> languageEntities = new ArrayList<>();
        for (Language lang : languages) {
            languageEntities.add(new LanguageEntity(lang));
        }
        LanguageDao languageDao = database.languageDao();
        languageDao.insetLanguages(languageEntities);
    }

    public void getSupportedLanguagesFromDatabase(OnLoadData<List<Language>> callback) {
        List<Language> languages = database.languageDao().getAllLanguages();
        if (languages != null && languages.size() > 0) {
            callback.onSuccess(languages);
            return;
        }
        callback.onFailure(new Exception("Failed to retrieve any data from database"));
    }

    public interface OnLoadData<T> {
        void onSuccess(T result);
        void onFailure(Throwable error);
    }
}
