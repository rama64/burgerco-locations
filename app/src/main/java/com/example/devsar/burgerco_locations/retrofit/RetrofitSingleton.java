package com.example.devsar.burgerco_locations.retrofit;

import com.example.devsar.burgerco_locations.BuildConfig;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://private-ee036-burgerco.apiary-mock.com/";
    private static RetrofitSingleton singleton = null;

    private RetrofitSingleton(){
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ?    HttpLoggingInterceptor.Level.BODY :
                HttpLoggingInterceptor.Level.NONE);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

         retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                 .client(okHttpClient)
                .build();
    }

    public static Retrofit getInstance(){
        if(singleton == null){
            singleton = new RetrofitSingleton();
        }
        return retrofit;
    }

}
