package com.example.appbookroom.networkUtils;

import com.example.appbookroom.entity.Room;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataRoomService {
    @GET("room")
    Call<List<Room>> getAllRoom();
    @GET("room/location/{location}")
    Call<List<Room>> getRoomByLocation(@Path("location") int location);
}
