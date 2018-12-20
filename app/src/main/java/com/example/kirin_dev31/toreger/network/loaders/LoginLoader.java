package com.example.kirin_dev31.toreger.network.loaders;

import android.content.Context;
import android.os.Bundle;

import com.example.kirin_dev31.toreger.models.User;
import com.example.kirin_dev31.toreger.network.ServiceGenerater;
import com.example.kirin_dev31.toreger.network.interfaces.Sessions.LoginApi;
import com.example.kirin_dev31.toreger.views.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginLoader extends BaseAsyncTaskLoader {
    private User user = null;

    public LoginLoader(Context context, Bundle args) {
        super(context);
        this.args = args;
    }

    @Override
    public User loadInBackground() {
        LoginApi loginApi = ServiceGenerater.createService(LoginApi.class);

        // APIをコールする
        loginApi.loginUser(this.args.getString(Constants.KEY_USER_ID), this.args.getString(Constants.KEY_PASSWORD))
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        int code = response.code();
                        if (response.isSuccessful()) {
                            // レスポンスが取得できた場合
                            user = response.body();
                            return;
                        }
                        if (code >= 200 && code < 300) {
                        } else if (code == 401) {
                        } else if (code >= 400 && code < 500) {
                        } else if (code >= 500 && code < 600) {
                        } else {
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
        return user;
    }
}
