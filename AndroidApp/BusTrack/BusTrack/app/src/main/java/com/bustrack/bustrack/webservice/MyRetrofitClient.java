package com.bustrack.bustrack.webservice;

import com.bustrack.bustrack.util.WebUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kripa on 06-08-2017.
 */

public class MyRetrofitClient {
    Retrofit retrofit = null;


    private OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.MINUTES)
                .readTimeout(60, TimeUnit.MINUTES)
                .build();
        return client;
    }


    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    public Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(WebUtil.URL).
                    addConverterFactory(GsonConverterFactory.create(gson)).
                    client(getClient()).
                    build();
        }
        return  retrofit;
    }

}
