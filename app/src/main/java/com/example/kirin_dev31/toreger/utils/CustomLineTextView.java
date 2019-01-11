package com.example.kirin_dev31.toreger.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class CustomLineTextView extends android.support.v7.widget.AppCompatTextView {
    public CustomLineTextView(Context context) {
        super(context);
    }

    public CustomLineTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLineTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init (Context context, AttributeSet attrs){
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.attr.)
    }
}
