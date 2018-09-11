package com.project.notend.notend.data.remote;

import com.project.notend.notend.entities.Account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @POST("api/register")
    Call<Account> createAccount(@Body Account account);

//    @Field("email") String email,
//    @Field("firstName") String firstName,
//    @Field("lastName") String lastName,
//    @Field("login") String login,
//    @Field("password") String password
}
