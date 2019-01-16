package com.example.kirin_dev31.toreger.domain.models;

public class User {
    // ID デフォルト値は-1
    public int id = -1;
    // ユーザーID
    public String email = "";
    // パスワード
    public String password = "";
    // 電話番号
    public String phone = "";
    // 姓
    public String last_name = "";
    // 名
    public String first_name = "";
    // 姓カナ
    public String last_name_kana = "";
    // 名カナ
    public String first_name_kana = "";
//    // 年齢
//    public int age;
    // 性別
    public int gender;
    // 生年月日
    public String birthday = "";
//    // 郵便番号
//    public String postCode;
//    // 都道府県
//    public int preferenceCode;
    // その他の住所
    public String address;

    public int is_delete = 0;
    public String deleted_user = "";
    public String deleted_at = "";
    public String created_at = "";
    public String updated_at = "";
}
