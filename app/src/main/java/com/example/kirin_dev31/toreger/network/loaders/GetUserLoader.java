package com.example.kirin_dev31.toreger.network.loaders;

import android.content.Context;
import android.os.Bundle;
import com.example.kirin_dev31.toreger.models.User;
import com.example.kirin_dev31.toreger.network.ServiceGenerater;
import com.example.kirin_dev31.toreger.network.interfaces.ApiServices;
import com.example.kirin_dev31.toreger.views.Constants;

import java.io.IOException;
import retrofit2.Response;

public class GetUserLoader extends BaseAsyncTaskLoader {
    public GetUserLoader(Context context, Bundle args){
        super(context);
        this.args = args;
    }

    @Override
    public User loadInBackground() {
        ApiServices getUserApi = ServiceGenerater.createService(ApiServices.class);
        try {
            // APIをコールする
            Response<User> response = getUserApi.getUser(this.args.getString(Constants.KEY_TOKEN)).execute();
            if (response.isSuccessful()) {
                return response.body();
            }
// レスポンスが取得できた場合
            int code = response.code();
            if (code >= 200 && code < 300) {
            } else if (code == 401) {
            } else if (code >= 400 && code < 500) {
            } else if (code >= 500 && code < 600) {
            } else {
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
