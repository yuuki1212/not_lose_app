package com.example.kirin_dev31.toreger.network.loaders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kirin_dev31.toreger.models.PreferenceUtil;
import com.example.kirin_dev31.toreger.network.ServiceGenerater;
import com.example.kirin_dev31.toreger.network.interfaces.ApiServices;
import com.example.kirin_dev31.toreger.views.Constants;

import java.io.IOException;

import retrofit2.Response;

public class LoginLoader extends BaseAsyncTaskLoader {
    public LoginLoader(Context context, Bundle args) {
        super(context);
        this.args = args;
    }

    @Override
    public String loadInBackground() {
        String message = "";
        ApiServices loginApi = ServiceGenerater.createService(ApiServices.class);

        // APIをコールする
        Response<String> response = null;
        try {
            response = loginApi.login(this.args.getString(Constants.KEY_USER_ID), this.args.getString(Constants.KEY_PASSWORD)).execute();
            int code = response.code();
            if (response.isSuccessful()) {
                // レスポンスが取得できた場合
                SharedPreferences.Editor editor = PreferenceUtil.getEditor(getContext());
                System.out.println(response.body());
//                            editor.putString(PreferenceUtil.ACCESS_TOKEN_KEY, response.body());
//                            return;

            }
            if (code >= 200 && code < 300) {
            } else if (code == 401) {
            } else if (code >= 400 && code < 500) {
            } else if (code >= 500 && code < 600) {
            } else {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return message;
    }
}
