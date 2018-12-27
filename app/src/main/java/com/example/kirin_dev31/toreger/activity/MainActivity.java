package com.example.kirin_dev31.toreger.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kirin_dev31.toreger.R;
import com.example.kirin_dev31.toreger.models.PreferenceUtil;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView v = findViewById(R.id.main_id);

        v.setText(PreferenceUtil.getFindById(this, PreferenceUtil.ACCESS_TOKEN_KEY));
    }
}
