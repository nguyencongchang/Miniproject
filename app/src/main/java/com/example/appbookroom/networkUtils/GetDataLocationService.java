package com.example.appbookroom.networkUtils;

import com.example.appbookroom.entity.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataLocationService {
    @GET("location")
    Call<List<Location>> getAllLocation();
}
