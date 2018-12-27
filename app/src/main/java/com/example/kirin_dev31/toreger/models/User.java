package com.example.kirin_dev31.toreger.models;

public class User {
    // ID デフォルト値は-1
    private int id = -1;
    // ユーザーID
    private String email = "";
    // パスワード
    private String password = "";
    // 電話番号
    private String phone = "";
    // 姓
    private String last_name = "";
    // 名
    private String first_name = "";
    // 姓カナ
    private String last_name_kana = "";
    // 名カナ
    private String first_name_kana = "";
    // 年齢
    private int age;
    // 性別
    private int gender;
    // 生年月日
    private String birthday = "";
    // 郵便番号
    private String postCode;
    // 都道府県
    private int preferenceCode;
    // その他の住所
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneCode() {
        return phone;
    }

    public void setPhoneCode(String phone) {
        this.phone = phone;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public int getPreferenceCode() {
        return preferenceCode;
    }

    public void setPreferenceCode(int preferenceCode) {
        this.preferenceCode = preferenceCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastNameKana() {
        return last_name_kana;
    }

    public void setLastNameKana(String last_name_kana) {
        this.last_name_kana = last_name_kana;
    }

    public String getFirstNameKana() {
        return first_name_kana;
    }

    public void setFirstNameKana(String first_name_kana) {
        this.first_name_kana = first_name_kana;
    }
}
