package com.example.watsontranslate.network;

import com.example.watsontranslate.network.data.LanguagesList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WatsonTranslateService {

    @GET("/v3/languages?version=2018-05-01")
    Call<LanguagesList> getSupportedLanguages();


}
