package com.example.josycom.paginglibrary.network;

import com.example.josycom.paginglibrary.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("users")
    Call<UserResponse> getUsers(@Query("page") long page);
}
