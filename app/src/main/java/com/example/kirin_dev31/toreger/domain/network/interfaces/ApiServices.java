package com.example.kirin_dev31.toreger.domain.network.interfaces;

import com.example.kirin_dev31.toreger.domain.models.Token;
import com.example.kirin_dev31.toreger.domain.models.User;
import com.example.kirin_dev31.toreger.domain.network.ServiceGenerater;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiServices {
    @Headers("Accept: application/json")
    @GET(ServiceGenerater.SESSION.USER_INFO)
    Call<User> getUser(@Header("Authorization") String authorization);

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST(ServiceGenerater.SESSION.LOGIN)
    Call<Token> login(@Field("username") String user_id,
                      @Field("password") String password,
                      @Field("grant_type") String grant_type,
                      @Field("client_id") String client_id,
                      @Field("client_secret") String client_secret,
                      @Field("scope") String scope);
}
