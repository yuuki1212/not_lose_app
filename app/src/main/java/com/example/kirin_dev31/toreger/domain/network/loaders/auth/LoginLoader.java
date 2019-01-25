package com.example.kirin_dev31.toreger.domain.network.loaders.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kirin_dev31.toreger.domain.network.loaders.BaseAsyncTaskLoader;
import com.example.kirin_dev31.toreger.domain.prefs.PreferenceUtil;
import com.example.kirin_dev31.toreger.domain.models.Token;
import com.example.kirin_dev31.toreger.domain.network.ServiceGenerater;
import com.example.kirin_dev31.toreger.domain.network.interfaces.ApiServices;
import com.example.kirin_dev31.toreger.util.Constants;

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
        Response<Token> response = null;
        try {
            response = loginApi.login(this.args.getString(Constants.KEY_USER_ID), this.args.getString(Constants.KEY_PASSWORD),
                    ServiceGenerater.SESSION.GRANT_TYPE, ServiceGenerater.SESSION.CLIENT_ID,
                    ServiceGenerater.SESSION.CLIENT_SECRET, ServiceGenerater.SESSION.SCOPE).execute();
            int code = response.code();
            if (response.isSuccessful()) {
                Token token = response.body();
                // レスポンスが取得できた場合
                SharedPreferences.Editor editor = PreferenceUtil.getEditor(getContext());
                editor.putString(PreferenceUtil.ACCESS_TOKEN_KEY, "Bearer " + token.access_token);
                editor.putString(PreferenceUtil.REFRESH_TOKEN_KEY, token.refresh_token);
                editor.putString(PreferenceUtil.EXPIRES_IN_KEY, token.expires_in);
                editor.putString(PreferenceUtil.TOKEN_TYPE_KEY, token.token_type);
                editor.apply();
            }
            if (code >= 200 && code < 300) {
            } else if (code == 401) {
                message = "Email又はパスワードが正しくありません。";
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
