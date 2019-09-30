package com.example.retrofit.data.model.remote;

import retrofit2.Retrofit;

public class ApiUtils {
    public ApiUtils() {
    }

    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";
    private static final String BASE_URL_COMMENTS = "https://api.stackexchange.com/2.2/";

    public static APIService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

    public static APIService getApiServiceComments(){
        return RetrofitClient.getClient(BASE_URL_COMMENTS).create(APIService.class);
    }
}
