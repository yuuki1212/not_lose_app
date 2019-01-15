package com.example.kirin_dev31.toreger.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.example.kirin_dev31.toreger.R;

public class CustomLineTextView extends android.support.v7.widget.AppCompatTextView {
    int line_weight;
    int line_type;
    public CustomLineTextView(Context context) {
        super(context);
    }

    public CustomLineTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomLineTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init (Context context, AttributeSet attrs){
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CustomLineTextView, 0, 0);

        try {
            line_weight = typedArray.getInteger(R.styleable.CustomLineTextView_line_weight, 1);
            line_type = typedArray.getInteger(R.styleable.CustomLineTextView_line_type, 0);
        } finally {
            typedArray.recycle();
        }

        setLine();
    }

    private void setLine() {
        switch (line_type) {
            case 0 :
                setPaintFlags(getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                break;
            case 1 :
                break;
            case 2 :
                break;
            case 3 :
                break;
        }
    }
}
