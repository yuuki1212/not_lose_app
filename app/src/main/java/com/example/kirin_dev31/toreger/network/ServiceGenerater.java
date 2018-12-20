package com.example.kirin_dev31.toreger.network;

import com.example.kirin_dev31.toreger.utils.OkHttpSingleton;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerater {
    // host
    public static final String HOST = "http://192.168.33.110";

    /**
     * セッション
     */
    public static final class SESSION {
        // ユーザー情報の取得
        public static final String LOGIN = "/session/login";
    }

    private static OkHttpClient httpClient = OkHttpSingleton.getInstance().getOkHttpClient();

    private static Retrofit retrofit =
            new Retrofit.Builder()
                    .baseUrl(HOST)
                    .addConverterFactory(GsonConverterFactory.create()).build();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
