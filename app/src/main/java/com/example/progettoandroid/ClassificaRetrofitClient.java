package com.example.progettoandroid;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClassificaRetrofitClient {
    public static Retrofit retrofit;

    public static Retrofit getRetrofitIstance() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://develop.ewlab.di.unimi.it/mc/mostri/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}