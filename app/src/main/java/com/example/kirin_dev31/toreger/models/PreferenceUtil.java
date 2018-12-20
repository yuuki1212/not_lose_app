package com.example.kirin_dev31.toreger.models;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * ログインPreference
 */
public class PreferenceUtil {

    // ファイル名
    private static final String PREF_FILE_NAME = "loginInfo";

    // ユーザーIDキー
    public static final String USER_ID_KEY = "user.id";
    // パスワードキー
    public static final String PASSWPRD_KEY = "user.password";

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
}
