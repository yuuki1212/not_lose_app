package com.example.kirin_dev31.toreger.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpSingleton {
    private OkHttpClient mOkHttpClient;

    private static OkHttpSingleton ourInstance = new OkHttpSingleton();

    public static OkHttpSingleton getInstance() {
        return ourInstance;
    }

    private OkHttpSingleton() {

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.connectTimeout(20, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(15, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(15, TimeUnit.SECONDS);
        okHttpBuilder.addInterceptor(getInterceptor());

        mOkHttpClient = okHttpBuilder.build();
    }

    private Interceptor getInterceptor() {

        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request newRequest = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .build();

                return chain.proceed(newRequest);
            }
        };
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }
}
