package com.example.kirin_dev31.toreger.domain.network.loaders.home;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.example.kirin_dev31.toreger.domain.models.GridItem;
import com.example.kirin_dev31.toreger.domain.network.ServiceGenerater;
import com.example.kirin_dev31.toreger.domain.network.interfaces.ApiServices;
import com.example.kirin_dev31.toreger.domain.network.loaders.BaseAsyncTaskLoader;
import com.example.kirin_dev31.toreger.util.Constants;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class GetSpaceItemLoader extends BaseAsyncTaskLoader {
    private Context context;
    public GetSpaceItemLoader(Context context, Bundle args) {
        super(context);
        this.context = context;
        this.args = args;
    }

    @Override
    public List<GridItem> loadInBackground() {
        ApiServices apiServices = ServiceGenerater.createService(ApiServices.class);
        try {
            // APIをコールする
            Response<List<GridItem>> response = apiServices.getSpaceItem(this.args.getInt(Constants.KEY_SPACE_ID),
                    this.args.getString(Constants.KEY_TOKEN), this.args.getString(Constants.KEY_CATEGORY_ID),
                    this.args.getInt(Constants.KEY_USER_ID), this.args.getInt(Constants.KEY_USAGES_ID),
                    this.args.getInt(Constants.KEY_MAIN_NO), this.args.getInt(Constants.KEY_TREE_NO)).execute();
            Toast toast = Toast.makeText(context, response.message(), Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
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
