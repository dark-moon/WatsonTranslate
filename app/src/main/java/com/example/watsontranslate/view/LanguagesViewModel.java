package com.example.watsontranslate.view;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.watsontranslate.LanguageRepository;
import com.example.watsontranslate.network.data.Language;
import com.example.watsontranslate.network.data.LanguagesList;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LanguagesViewModel extends AndroidViewModel {

    private final String TAG = "LanguagesViewModel";
    private final ExecutorService background;
    private final MutableLiveData<List<Language>> supportedLanguages;
    private final MutableLiveData<Boolean> loading;

    private final LanguageRepository repository;


    public LanguagesViewModel(@NonNull Application application) {
        super(application);
        background = Executors.newFixedThreadPool(2);
        supportedLanguages = new MutableLiveData<>();
        loading = new MutableLiveData<>();
        repository = new LanguageRepository(application);

    }

    public LiveData<List<Language>> getSupportedLanguages() {
        return supportedLanguages;
    }
    public LiveData<Boolean> isLoading() { return loading; }

    public void onLoadSupportedLanguages() {
        background.execute(() -> {
            loading.postValue(true);
            repository.getSupportedLanguagesFromDatabase(new LanguageRepository.OnLoadData<List<Language>>() {
                @Override
                public void onSuccess(List<Language> result) {
                    loading.postValue(false);
                    supportedLanguages.postValue(result);
                    Log.i(TAG, "onSuccess: successfully retrieved data from database");
                }

                @Override
                public void onFailure(Throwable error) {
                    Log.i(TAG, "onFailure: Data can't be retrieved from database");
                    repository.getSupportedLanguagesRemote(new LanguageRepository.OnLoadData<LanguagesList>() {
                        @Override
                        public void onSuccess(LanguagesList result) {
                            Log.i(TAG, "onSuccess: retrieved from server");
                            saveLanguagesInDatabase(result.getLanguages());
                            Log.i(TAG, "onSuccess: saved to database");
                            loading.postValue(false);
                            supportedLanguages.postValue(result.getLanguages());
                            Log.i(TAG, "onSuccess: posted to view");
                        }
                        @Override
                        public void onFailure(Throwable error) {
                            Log.i(TAG, "onFailure: failed to rertrieve data from server");
                        }
                    });
                }
            });
        });
    }

    private void saveLanguagesInDatabase(List<Language> languages) {
        background.execute(() -> {
            repository.saveLanguagesInDatabase(languages);
        });

    }
}
