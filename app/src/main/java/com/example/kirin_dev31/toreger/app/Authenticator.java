package com.example.kirin_dev31.toreger.app;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kirin_dev31.toreger.domain.models.Token;
import com.example.kirin_dev31.toreger.domain.network.ServiceGenerater;
import com.example.kirin_dev31.toreger.domain.network.interfaces.ApiServices;
import com.example.kirin_dev31.toreger.domain.prefs.PreferenceUtil;
import com.example.kirin_dev31.toreger.views.activity.AuthorizationActivity;

import java.io.IOException;

import retrofit2.Response;

public class Authenticator extends AbstractAccountAuthenticator {
    public static final String mAuthTokenType = "remodelingApp";
    private Context mContext;
    public Authenticator(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        return null;
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
        final Intent intent = new Intent(mContext.getApplicationContext(), AuthorizationActivity.class);

        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
        Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return bundle;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        Bundle result = new Bundle();
        final AccountManager manager = AccountManager.get(mContext.getApplicationContext());
        final String authToken = manager.peekAuthToken(account, authTokenType);

        if (authToken == null) {
            // authTokenが無い場合、アカウントに紐図いてるパスワードを取得
            String password = manager.getPassword(account);
            if (password != null) {
                // パスワードが取得できた場合、authTokenを取得しにサーバーに接続
                String message = "";
                ApiServices loginApi = ServiceGenerater.createService(ApiServices.class);

                // APIをコールする
                Response<Token> apiResponse = null;
                try {
                    apiResponse = loginApi.login(account.name, password, ServiceGenerater.SESSION.GRANT_TYPE,
                            ServiceGenerater.SESSION.CLIENT_ID, ServiceGenerater.SESSION.CLIENT_SECRET,
                            ServiceGenerater.SESSION.SCOPE).execute();
                    int code = apiResponse.code();
                    if (apiResponse.isSuccessful()) {
                        Token token = apiResponse.body();
                        // レスポンスが取得できた場合
                        SharedPreferences.Editor editor = PreferenceUtil.getEditor(mContext);
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
            }
        }

        if (authToken != null && !authToken.isEmpty()) {
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
            result.putString(AccountManager.KEY_AUTHTOKEN, authToken);
        } else {
            final Intent intent = new Intent(mContext.getApplicationContext(), AuthorizationActivity.class);
            intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
            result.putParcelable(AccountManager.KEY_INTENT, intent);
        }
        return result;
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
        return null;
    }
}
