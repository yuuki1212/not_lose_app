package com.example.kirin_dev31.toreger.activity.splash;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.os.Handler;

import com.example.kirin_dev31.toreger.R;
import com.example.kirin_dev31.toreger.activity.LoginActivity;
import com.example.kirin_dev31.toreger.activity.MainActivity;
import com.example.kirin_dev31.toreger.models.PreferenceUtil;
import com.example.kirin_dev31.toreger.models.User;
import com.example.kirin_dev31.toreger.network.loaders.GetUserLoader;
import com.example.kirin_dev31.toreger.views.Constants;


public class SplashActivity extends Activity{

    @Override
    public void onCreate(final Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.splash_view);

        // Preferenceからアクセストークンの取得
        String token = PreferenceUtil.getFindById(this, PreferenceUtil.ACCESS_TOKEN_KEY);
        if (token != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 取得できなければログイン画面に遷移
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                }
            }, 1000);
        } else {
            // 取得で来たらログイン処理を続行
            Bundle args = new Bundle();
            args.putString(Constants.KEY_TOKEN, token);
            getLoaderManager().initLoader(Constants.LOADER_ID.LOADER_USER_INFO_ID, args, mCallBack);
        }

    }

    private final LoaderManager.LoaderCallbacks<User> mCallBack = new LoaderManager.LoaderCallbacks<User>() {
        @Override
        public Loader<User> onCreateLoader(int id, Bundle args) {
            return new GetUserLoader(SplashActivity.this, args);
        }

        @Override
        public void onLoadFinished(Loader<User> loader, User user) {
            // ローダーの破棄
            getLoaderManager().destroyLoader(loader.getId());
            Intent intent = null;

            if (user == null) {
                // ユーザーが取得できなかった場合
                intent = new Intent(getApplicationContext(), LoginActivity.class);
            } else {
                // ログイン処理が完了
                intent = new Intent(getApplicationContext(), MainActivity.class);
            }
            // アクティビティの起動
            startActivity(intent);

            SplashActivity.this.finish();
        }

        @Override
        public void onLoaderReset(Loader<User> loader) {

        }
    };
}
