package com.example.kirin_dev31.toreger.domain.network;

import com.example.kirin_dev31.toreger.util.OkHttpSingleton;

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
        // grant_type
        public static final String GRANT_TYPE = "password";
        // client_id
        public static final String CLIENT_ID = "3";
        // client_secret
        public static final String CLIENT_SECRET = "fR6NVyw7nvqB09exlwpqDOyDxDLlit1JnvJbjrPN";
        // scope
        public static final String SCOPE = "*";
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
