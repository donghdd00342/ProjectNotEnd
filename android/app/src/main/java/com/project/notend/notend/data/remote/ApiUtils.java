package com.project.notend.notend.data.remote;

public class ApiUtils {
    public static final String SERVER_URL_ACCOUNT = "http://171.244.49.71:8080/";

    private ApiUtils() {};

    public static APIService getApiServiceAccount(){
        return RetrofitClient.getClient(SERVER_URL_ACCOUNT).create(APIService.class);
    }
}
