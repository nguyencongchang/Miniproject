package com.example.appbookroom.networkUtils;

import com.example.appbookroom.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("login")
    Call<User> login(@Body User user);
}
