package com.project.notend.notend.data.remote;

import com.project.notend.notend.entities.Account;
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

}
