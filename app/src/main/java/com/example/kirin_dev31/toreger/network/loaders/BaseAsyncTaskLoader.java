package com.example.kirin_dev31.toreger.network.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;

import com.example.kirin_dev31.toreger.network.ServiceGenerater;
import com.example.kirin_dev31.toreger.views.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseAsyncTaskLoader<D> extends AsyncTaskLoader<D> {

    private D mResult;

    private boolean mIsStarted = false;

    protected Bundle args = null;

    public BaseAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        mIsStarted = true;
    }

    @Override
    public void deliverResult(D data) {
        super.deliverResult(data);
        mResult = data;
    }

    @Override
    protected void onStartLoading() {
        if (mResult != null) {
            deliverResult(mResult);
            return;
        }
        if (!mIsStarted || takeContentChanged()) {
            forceLoad();
        }
        super.onStartLoading();
    }

    public static void errorResponse () {

    }
}
