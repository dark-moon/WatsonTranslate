package com.example.watsontranslate.view;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.watsontranslate.network.TranslationBasicAuthInterceptor;
import com.example.watsontranslate.network.WatsonTranslateService;
import com.example.watsontranslate.network.data.LanguagesList;
import com.example.watsontranslate.tools.Constants;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LanguagesViewModel extends AndroidViewModel {

    private final String TAG = "LanguagesViewModel";
    private final ExecutorService background;
    private final MutableLiveData<LanguagesList> _supportedLanguages;


    public LanguagesViewModel(@NonNull Application application) {
        super(application);
        background = Executors.newFixedThreadPool(2);
        _supportedLanguages = new MutableLiveData<>();
    }

    public LiveData<LanguagesList> get_supportedLanguages() {
        return _supportedLanguages;
    }

    public void onLoadSupportedLanguages() {
        background.execute(() -> {
            OkHttpClient translationClient = new OkHttpClient.Builder()
                    .addInterceptor(new TranslationBasicAuthInterceptor())
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.TRANSLATOR_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(translationClient)
                    .build();

            WatsonTranslateService service = retrofit.create(WatsonTranslateService.class);

            service.getSupportedLanguages().enqueue(new Callback<LanguagesList>() {
                @Override
                public void onResponse(Call<LanguagesList> call, Response<LanguagesList> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            _supportedLanguages.postValue(response.body());
                            Log.i(TAG, "onResponse: " + response.body().getLanguages().get(1).getNative_language_name());
                        }
                    }
                }

                @Override
                public void onFailure(Call<LanguagesList> call, Throwable t) {
                    Log.i(TAG, "onFailure: " + t.getMessage());
                }
            });
        });
    }
}
