package com.example.kirin_dev31.toreger.network.interfaces.Sessions;

import com.example.kirin_dev31.toreger.models.User;
import com.example.kirin_dev31.toreger.network.ServiceGenerater;
import com.example.kirin_dev31.toreger.views.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApi {
    @FormUrlEncoded
    @POST(ServiceGenerater.SESSION.LOGIN)
    Call<User> loginUser(@Field(Constants.KEY_USER_ID) String user_id, @Field(Constants.KEY_PASSWORD) String password);
}
