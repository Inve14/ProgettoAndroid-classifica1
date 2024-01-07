package com.example.progettoandroid;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiDataUser {
    @GET("users/{uid}")
    Call<UserData> getUserData(
            @Path("uid") int uid,
            @Query("sid") String sid);
}
