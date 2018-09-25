package com.project.notend.notend.data.remote;

import com.project.notend.notend.entities.Account;
import com.project.notend.notend.entities.ChatMessage;
import com.project.notend.notend.entities.Friend;
import com.project.notend.notend.entities.TokenId;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {

    @POST("api/register")
    Call<Account> createAccount(@Body Account account);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("api/authenticate")
    Call<TokenId> loginAccount(@Body String body);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("api/account")
    Call<Account> getAccountInfo(@Header("Authorization") String authHeader);

    @POST("api/account/change-password")
    Call<Void> changePassword(@Body String body, @Header("Authorization") String authHeader);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("api/users/getall")
    Call<List<Account>> getAllDetailAccount(@Header("Authorization") String authHeader);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("api/users/{login}")
    Call<Account> getDetailUser(@Path("login") String login, @Header("Authorization") String authHeader);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("/api/friends")
    Call<Void> askFriend(@Body Friend body, @Header("Authorization") String authHeader);

    @POST("api/account")
    Call<Account> editAccount(@Body Account account, @Header("Authorization") String authHeader);

    @GET("api/account/upgrade")
    Call<Void> upgradeAccount(@Header("Authorization") String authHeader);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("api/friends")
    Call<List<Friend>> getAllFriendsChat(@Header("Authorization") String authHeader);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("api/messages")
    Call<List<ChatMessage>> getAllMessagers(@Header("Authorization") String authHeader);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("/api/messages")
    Call<ChatMessage> createMessager(@Body ChatMessage chatMessage,@Header("Authorization") String authHeader);
  
    @GET("api/friends")
    Call<List<Friend>> getFriendList(@Header("Authorization") String authHeader);

    @GET("api/friends/request")
    Call<List<Friend>> getRequestFriend(@Header("Authorization") String authHeader);

    @PUT("api/friends")
    Call<List<Friend>> acceptRequestFriend(@Body Friend body, @Header("Authorization") String authHeader);

    @PUT("api/friends/{id}")
    Call<List<Friend>> deleteRequestFriend(@Path("friendId") Integer friendId, @Header("Authorization") String authHeader);
}
