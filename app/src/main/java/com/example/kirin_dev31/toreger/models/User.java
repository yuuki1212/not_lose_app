package com.example.kirin_dev31.toreger.models;

public class User {
    // ID デフォルト値は-1
    private int id = -1;
    // ユーザーID
    private String userId = "";
    // パスワード
    private String password = "";
    // メールアドレス
    private String mailAddress = "";
    // 電話番号
    private String phoneCode = "";
    // 姓
    private String lastName = "";
    // 名
    private String firstName = "";
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
}
