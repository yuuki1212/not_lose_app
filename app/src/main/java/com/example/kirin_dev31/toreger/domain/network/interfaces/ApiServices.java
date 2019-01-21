package com.example.kirin_dev31.toreger.domain.network.interfaces;

import com.example.kirin_dev31.toreger.domain.models.GridItem;
import com.example.kirin_dev31.toreger.domain.models.Token;
import com.example.kirin_dev31.toreger.domain.models.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {
    /**
     * ユーザー情報の取得
     * @param authorization
     * @return
     */
    @Headers("Accept: application/json")
    @GET("/api/user")
    Call<User> getUser(@Header("Authorization") String authorization);

    /**
     * ログイン
     * @param user_id
     * @param password
     * @param grant_type
     * @param client_id
     * @param client_secret
     * @param scope
     * @return
     */
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("/oauth/token")
    Call<Token> login(@Field("username") String user_id,
                      @Field("password") String password,
                      @Field("grant_type") String grant_type,
                      @Field("client_id") String client_id,
                      @Field("client_secret") String client_secret,
                      @Field("scope") String scope);

    /**
     * スペース情報の取得
     * @param authorization
     * @param category
     * @param user_id
     * @param usages_id
     * @param main_no
     * @param tree_no
     * @return
     */
    @Headers("Accept: applivation/json")
    @GET("/api/space/")
    Call<GridItem.Space> getSpace(@Header("Authorization") String authorization,
                                  @Query("category_id") String category,
                                  @Query("user_id") int user_id,
                                  @Query("usages_id") int usages_id,
                                  @Query("main_no") int main_no,
                                  @Query("tree_no") int tree_no);
}