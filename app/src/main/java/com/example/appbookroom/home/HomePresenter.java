package com.example.appbookroom.home;

import android.view.View;

import com.example.appbookroom.entity.Location;
import com.example.appbookroom.entity.Room;

import java.util.List;

public class HomePresenter implements IHomeListContract.IPresenter {
    private IHomeListContract.IView homeView;
    private IHomeListContract.IModel homeListModel;

    public HomePresenter(IHomeListContract.IView homeView) {
        this.homeView = homeView;
        homeListModel = new HomeInteractor();
    }

    @Override
    public void onDestroy() {
        this.homeView = null;
    }

    @Override
    public void requestDataRoomFromServer() {
        if(homeView!=null){
            homeListModel.getRoomList(new IHomeListContract.IModel.OnRoomFinishedListener() {
                @Override
                public void onRoomFinished(List<Room> roomList) {
                    if (homeView != null)
                        homeView.setDataToRecyclerview(roomList);
                }

                @Override
                public void onRoomFailure(Throwable t) {
                    homeView.onResponseRoomFailure(t);
                }
            });
        }
    }

    @Override
    public void requestDataRoomByIdFromServer(int locationId) {
        if(homeView!=null){
            homeListModel.getRoomByIdList(new IHomeListContract.IModel.OnRoomByIdFinishedListener() {
                @Override
                public void onRoomByIdFinished(List<Room> roomList) {
                    homeView.updateDataRoomToRecyclerView(roomList);
                }

                @Override
                public void onRoomByIdFailure(Throwable t) {
                    homeView.onResponseRoomByIdFailure(t);
                }
            }, locationId);
        }
    }

    @Override
    public void requestDataLocationFromServer() {
        if(homeView!=null){
            homeListModel.getLocationList(new IHomeListContract.IModel.OnLocationFinishedListener() {
                @Override
                public void onLocationFinished(List<Location> locationList) {
                    homeView.setDataToLocation(locationList);
                }

                @Override
                public void onLocationFailure(Throwable t) {
                    homeView.onResponseLocationFailure(t);
                }
            });
        }
    }
}
