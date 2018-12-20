package com.example.kirin_dev31.toreger.activity;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kirin_dev31.toreger.R;
import com.example.kirin_dev31.toreger.models.User;
import com.example.kirin_dev31.toreger.network.ServiceGenerater;
import com.example.kirin_dev31.toreger.network.loaders.LoginLoader;
import com.example.kirin_dev31.toreger.utils.UtilValidator;
import com.example.kirin_dev31.toreger.views.Constants;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmailView     = findViewById(R.id.email);
        mPasswordView  = findViewById(R.id.password);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView  = findViewById(R.id.login_progress);
        mProgressView.

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (EditorInfo.IME_ACTION_DONE == actionId || EditorInfo.IME_NULL == actionId) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mSubmitButton = findViewById(R.id.login_submit);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        mProgressView = findViewById(R.id.login_progress);
        mLoginFormView = findViewById(R.id.login_form);
    }

    /**
     * ログイン処理
     */
    private void attemptLogin () {
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;
        Map<TextView, String[]> validates = new HashMap<TextView, String[]>();
        // バリデーションルール
        String[] email_param = {email, "required|email|hankaku"};
        String[] password_param = {password, "required|hankaku"};
        validates.put(mEmailView, email_param);
        validates.put(mPasswordView, password_param);

        // バリデーションの実行
        UtilValidator validator = new UtilValidator(getApplicationContext());
        Map<TextView, String> validateResponse = validator.validate(validates);

        for (TextView key : validateResponse.keySet()) {
            key.setError(validateResponse.get(key));
            cancel = true;
            focusView = key;
        }


        if (cancel) {
            focusView.requestFocus();
        } else {
            // 引数を格納し、ログイン通信
            Bundle args = new Bundle();
            args.putString(Constants.KEY_USER_ID, mEmailView.getText().toString());
            args.putString(Constants.KEY_PASSWORD, mPasswordView.getText().toString());
            getLoaderManager().initLoader(Constants.LOADER_ID.LOADER_LOGIN_ID, args, mCallBack);
        }
    }

    private final LoaderManager.LoaderCallbacks<User> mCallBack = new LoaderManager.LoaderCallbacks<User>() {

        @Override
        public Loader<User> onCreateLoader(int id, Bundle args) {
            return new LoginLoader(LoginActivity.this, args);
        }

        @Override
        public void onLoadFinished(Loader<User> loader, User user) {
            // ローダーの破棄
            getLoaderManager().destroyLoader(loader.getId());
            Intent intent = null;
            if (user == null) {
            } else {
                intent = new Intent(getApplicationContext(), MainActivity.class);
            }

            startActivity(intent);
            LoginActivity.this.finish();
        }

        @Override
        public void onLoaderReset(Loader<User> loader) {

        }
    };
}
