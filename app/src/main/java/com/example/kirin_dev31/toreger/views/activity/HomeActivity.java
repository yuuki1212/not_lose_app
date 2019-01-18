package com.example.kirin_dev31.toreger.views.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.kirin_dev31.toreger.R;
import com.example.kirin_dev31.toreger.views.fragments.home.GridFragment;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        getFragmentManager().beginTransaction().add(R.id.switch_view, new GridFragment()).commit();

    }
}
