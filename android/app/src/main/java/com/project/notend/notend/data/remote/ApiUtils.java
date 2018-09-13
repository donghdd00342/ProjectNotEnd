package com.project.notend.notend.data.remote;

public class ApiUtils {
    public static final String SERVER_URL_ACCOUNT = "http://171.244.49.71:8080/";

    public static APIService getApiServiceAccount(){
        return RetrofitClient.getClientForNull(SERVER_URL_ACCOUNT).create(APIService.class);
    }
    public static APIService getApiService(){
        return RetrofitClient.getClient(SERVER_URL_ACCOUNT).create(APIService.class);
    }
}
