package com.example.kirin_dev31.toreger.network.interfaces;

import com.example.kirin_dev31.toreger.models.User;
import com.example.kirin_dev31.toreger.network.ServiceGenerater;
import com.example.kirin_dev31.toreger.views.Constants;

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

    @FormUrlEncoded
    @POST(ServiceGenerater.SESSION.LOGIN)
    Call<String> login(@Field(Constants.KEY_USER_ID) String user_id, @Field(Constants.KEY_PASSWORD) String password);
}
