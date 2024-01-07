package com.example.progettoandroid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("ranking")
    Call<List<User>> getUserInformation(@Query("sid") String sid);

    @GET("users/{uid}")
    Call<UserData> getUserData(
            @Path("uid") int uid,
            @Query("sid") String sid);
}

