package com.example.appbookroom.home;

import android.util.Log;

import com.example.appbookroom.entity.Location;
import com.example.appbookroom.networkUtils.ClientApi;
import com.example.appbookroom.networkUtils.GetDataLocationService;
import com.example.appbookroom.networkUtils.GetDataRoomService;
import com.example.appbookroom.entity.Room;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeInteractor implements IHomeListContract.IModel {

    @Override
    public void getRoomList(OnRoomFinishedListener onRoomFinishedListener) {
        GetDataRoomService dataRoomService = ClientApi.getClientApi().create(GetDataRoomService.class);
        Call<List<Room>> call = dataRoomService.getAllRoom();
        call.enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                if (response.isSuccessful()) {
                    List<Room> mListRoom = response.body();
                    Log.e("TAG", "onResponse: " + mListRoom.size());
                    onRoomFinishedListener.onRoomFinished(mListRoom);
                }else {

                }
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable throwable) {
                Log.e("TAG", throwable.toString());
                onRoomFinishedListener.onRoomFailure(throwable);
            }
        });
    }

    @Override
    public void getLocationList(OnLocationFinishedListener onLocationFinishedListener) {
        GetDataLocationService dataLocationService = ClientApi.getClientApi().create(GetDataLocationService.class);
        Call<List<Location>> call = dataLocationService.getAllLocation();
        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                if (response.isSuccessful()) {
                    List<Location> mLocationList = new ArrayList<>();
                    mLocationList.add(new Location(100, "Tất cả"));
                    mLocationList.addAll(response.body());
                    Log.e("TAG", "onResponseLocation: " + mLocationList.size());
                    onLocationFinishedListener.onLocationFinished(mLocationList);
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable throwable) {
                Log.e("TAG", throwable.toString());
                onLocationFinishedListener.onLocationFailure(throwable);
            }
        });
    }

    @Override
    public void getRoomByIdList(OnRoomByIdFinishedListener onRoomByIdFinishedListener,int locationId) {
        GetDataRoomService dataRoomService = ClientApi.getClientApi().create(GetDataRoomService.class);
        Call<List<Room>> call = dataRoomService.getRoomByLocation(locationId);
        call.enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                if (response.isSuccessful()) {
                    List<Room> mListRoom = response.body();
                    Log.e("TAG", "onResponse: " + mListRoom.size());
                    onRoomByIdFinishedListener.onRoomByIdFinished(mListRoom);
                }
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable throwable) {
                Log.e("TAG", throwable.toString());
                onRoomByIdFinishedListener.onRoomByIdFailure(throwable);
            }
        });
    }


}
