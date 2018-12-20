package com.example.kirin_dev31.toreger.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.kirin_dev31.toreger.R;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class UtilValidator {
    // バリデーションコード
    public static final String REQUIRED = "required";
    public static final String EMAIL = "email";
    public static final String HANKAKU = "hankaku";
    // 終了コード
    public static final String END = "end";
    // バリデーションコードに合うチェックロジックを実行
    private static final Map<String, String> validators= new HashMap<String, String>();
    static {
        validators.put(REQUIRED, isNotNull());
        validators.put(EMAIL, isEmailValid());
        validators.put(HANKAKU, isHankaku());
    }
    // Context
    private static Context context = null;
    // チェック対象名
    private static String colum = "";

    public UtilValidator(Context context){
        this.context = context;
    }

    /**
     *
     * @return
     */
    private static String isNotNull() {
        if (colum == null) {
            return context.getString(R.string.valid_required);
        }
        return null;
    }

    /**
     * Emailのバリデーション
     * @return
     */
    private static String isEmailValid() {
        if (!colum.contains("@")) {
            return context.getString(R.string.valid_email);
        }
        return null;
    }

    /**
     * 半角チェック
     * @return
     */
    private static String isHankaku() {
        if (!Pattern.matches("^[0-9a-zA-Z]+$", colum)) {
            return context.getString(R.string.valid_hankaku);
        }
        return null;
    }

    /**
     * バリデーションチェック実行
     * @param validates
     * @return
     */
    public Map<TextView, String> validate(Map<TextView, String[]> validates) {
        this.context = context;
        Map<TextView, String> result = new HashMap<TextView, String>();

        for(TextView key : validates.keySet()) {
            String value = validates.get(key)[0];
            String rule = validates.get(key)[1];
            colum = value;
            for(String r : rule.split("|")) {
                if (r == END) {
                    break;
                }
                String message = validators.get(r);
                if (message != null) {
                    result.put(key, message);
                    break;
                }
            }
        }
        return result;
    }

    /**
     * バリデーションルール整形
     * @param args
     * @return
     */
    public static String createRule(String...args) {
        String result = "";
        for (String param : args) {
            result += param + "|";
        }
        return result;
    }
}
