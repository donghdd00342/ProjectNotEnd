package com.project.notend.notend.data.remote;

import com.project.notend.notend.entities.Account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {
    @POST("api/register")
    Call<Account> createAccount(@Body Account account);
}
