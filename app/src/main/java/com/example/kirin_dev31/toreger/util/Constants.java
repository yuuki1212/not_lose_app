package com.example.kirin_dev31.toreger.util;

/**
 * 定数クラス
 */
public final class Constants {
    private Constants(){}

    public static final String KEY_USER_ID = "user_id";         // ユーザーID（email）
    public static final String KEY_PASSWORD = "password";       // パスワード
    public static final String KEY_TOKEN = "token";             // トークン

    public static class Message {

    }

    /**
     * 非同期処理のローダー定数
     */
    public static class LOADER_ID {
        // ログイン
        public static final int LOADER_LOGIN_ID = 1;
        // ユーザー情報取得
        public static final int LOADER_USER_INFO_ID = 2;
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
