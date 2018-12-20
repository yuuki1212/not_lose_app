package com.example.kirin_dev31.toreger.views;

/**
 * 定数クラス
 */
public final class Constants {
    private Constants(){}

    // ユーザーIDのKEY
    public static final String KEY_USER_ID = "user_id";
    // パスワード
    public static final String KEY_PASSWORD = "password";

    public static class Message {

    }

    /**
     * 非同期処理のローダー定数
     */
    public static class LOADER_ID {
        // ログイン
        public static final int LOADER_LOGIN_ID = 0;
    }

    /**
     * FILEに関する定数
     */
    public static class FILE {
        // UTF-8
        public static final String UTF_8 = "UTF-8";
        // 改行コード
        public static final String NEW_LINE_CODE = "\n";
        // 区切り
        public static final String SEPARATION = "/";
    }
}
