package com.example.kirin_dev31.toreger.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.kirin_dev31.toreger.R;

public class CustomProgress extends android.support.v7.widget.AppCompatImageView {

    public CustomProgress(Context context) {
        super(context);
        init(context);
    }

    public CustomProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init (Context context) {
        GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(this);
        Glide.with(context).load(R.drawable.progress).into(target);
    }
}
