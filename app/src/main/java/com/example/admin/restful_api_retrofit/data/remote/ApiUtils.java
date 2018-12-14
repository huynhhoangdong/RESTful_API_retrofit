package com.example.admin.restful_api_retrofit.data.remote;

public class ApiUtils {
    public static final String BASE_URL = "https://api.stackexchange.com/2.2/";
    public static SOService getSOSerive(){
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }

}
