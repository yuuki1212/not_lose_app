package com.example.kirin_dev31.toreger.views.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.os.Handler;

import com.example.kirin_dev31.toreger.R;
import com.example.kirin_dev31.toreger.app.Authenticator;
import com.example.kirin_dev31.toreger.domain.models.ApiModel;
import com.example.kirin_dev31.toreger.domain.network.ServiceGenerater;
import com.example.kirin_dev31.toreger.domain.prefs.PreferenceUtil;
import com.example.kirin_dev31.toreger.domain.network.loaders.auth.GetUserLoader;
import com.example.kirin_dev31.toreger.util.Constants;

import java.io.IOException;


public class SplashActivity extends Activity{
    private AccountManager manager;

    @Override
    public void onCreate(final Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.splash_view);

        // このアプリのアカウントタイプのアカウントを取得
        manager = AccountManager.get(this);
        final Account[] accounts =manager.getAccountsByType(Authenticator.mAuthTokenType);


        // Preferenceからアクセストークンの取得
        String token = PreferenceUtil.getFindById(this, PreferenceUtil.ACCESS_TOKEN_KEY);
//        if (token == null) {
        if (accounts.length == 0) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // アカウントが無い場合は、ログイン画面に遷移
                    Intent intent = new Intent(getApplicationContext(), AuthorizationActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                }
            }, 1000);
        } else {
            manager.getAuthToken(accounts[0], ServiceGenerater.SESSION.SCOPE, null,
                    this, new AccountManagerCallback<Bundle>() {
                        @Override
                        public void run(AccountManagerFuture<Bundle> future) {
                            Bundle bundle = null;
                            try {
                                bundle = future.getResult();

                                String accountName = bundle.getString(AccountManager.KEY_ACCOUNT_NAME);
                                String accountType = bundle.getString(AccountManager.KEY_ACCOUNT_TYPE);
                                String authToken = bundle.getString(AccountManager.KEY_AUTHTOKEN);


                            } catch (AuthenticatorException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (OperationCanceledException e) {
                                e.printStackTrace();
                            }
                        }
                    }, null);
//            // 取得で来たらログイン処理を続行
//            Bundle args = new Bundle();
//            args.putString(Constants.KEY_TOKEN, token);
//            getLoaderManager().initLoader(Constants.LOADER_ID.LOADER_USER_INFO_ID, args, mCallBack);
        }

    }

    private final LoaderManager.LoaderCallbacks<ApiModel> mCallBack = new LoaderManager.LoaderCallbacks<ApiModel>() {
        @Override
        public Loader<ApiModel> onCreateLoader(int id, Bundle args) {
            switch (id) {
                case Constants.LOADER_ID.LOADER_USER_INFO_ID :
                    return new GetUserLoader(SplashActivity.this, args);
            }
            return null;
        }

        @Override
        public void onLoadFinished(Loader<ApiModel> loader, ApiModel model) {
            // ローダーの破棄
            getLoaderManager().destroyLoader(loader.getId());
            Intent intent = null;

            if (model == null) {
                // ユーザーが取得できなかった場合
                intent = new Intent(getApplicationContext(), AuthorizationActivity.class);
            } else {
                // ログイン処理が完了
                intent = new Intent(getApplicationContext(), HomeActivity.class);
            }
            // アクティビティの起動
            startActivity(intent);

            SplashActivity.this.finish();
        }

        @Override
        public void onLoaderReset(Loader<ApiModel> loader) {

        }
    };
}
