package com.example.watsontranslate.network;

import com.example.watsontranslate.tools.Constants;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TranslationBasicAuthInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String credentials = Credentials.basic(Constants.USER_NAME.trim(), Constants.PASSWORD.trim());
        Request authorizedRequest = chain.request().newBuilder().header("Authorization", credentials).build();
        return chain.proceed(authorizedRequest);
    }
}
