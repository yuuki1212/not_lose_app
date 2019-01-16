package com.example.kirin_dev31.toreger.domain.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * ログインPreference
 */
public class PreferenceUtil {

    // トークンファイル
    private static final String PREF_FILE_NAME = "token";

    public static final String ACCESS_TOKEN_KEY = "token.access";       // アクセストークン
    public static final String TOKEN_TYPE_KEY = "token.type";           // トークンタイプ
    public static final String EXPIRES_IN_KEY = "token.expire";
    public static final String REFRESH_TOKEN_KEY = "token.refresh";     // リフレッシュトークン


    // Utilなのでコンストラクタを生成しない
    private PreferenceUtil(){}

    /**
     * 保存している値の取得
     * @param context
     * @param key 取得キー
     * @return
     */
    public static String getFindById (Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);

        String result = sp.getString(key, null);

        return result;
    }

    public static SharedPreferences.Editor getEditor(Context context){
        return context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE).edit();
    }
}