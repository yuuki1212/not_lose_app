package com.example.kirin_dev31.toreger.activity.authorization;

import android.app.Activity;
import android.os.Bundle;

import com.example.kirin_dev31.toreger.R;
import com.example.kirin_dev31.toreger.fragments.authorization.LoginFragment;

public class AuthorizationActivity extends Activity {
    @Override
    public void onCreate(final Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.auth_activity);

        getFragmentManager().beginTransaction()
                .add(R.id.login_layout, new LoginFragment()).commit();
    }
}
