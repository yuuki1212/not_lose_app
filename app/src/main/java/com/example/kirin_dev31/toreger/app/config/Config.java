package com.example.kirin_dev31.toreger.app.config;

public class Config {
    // host
    private static final String HOST = "http://192.168.33.110";

    public static String getHOST() {
        return HOST;
    }

    public static String[] getAccessTokenParam() {
        return accessTokenParam;
    }

    // アクセストークン取得時の固定パラメータ
    private static final String[] accessTokenParam = {
            "password", "3", "fR6NVyw7nvqB09exlwpqDOyDxDLlit1JnvJbjrPN", "*"
    };
}
