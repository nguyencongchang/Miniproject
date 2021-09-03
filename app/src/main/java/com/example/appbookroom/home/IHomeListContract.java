package com.example.appbookroom.home;


import com.example.appbookroom.entity.Location;
import com.example.appbookroom.entity.Room;

import java.util.List;

public interface IHomeListContract {
    interface IModel {

        interface OnRoomFinishedListener {
            void onRoomFinished(List<Room> roomList);

            void onRoomFailure(Throwable t);
        }

        interface OnLocationFinishedListener {
            void onLocationFinished(List<Location> locationList);

            void onLocationFailure(Throwable t);
        }

        interface OnRoomByIdFinishedListener {
            void onRoomByIdFinished(List<Room> roomList);

            void onRoomByIdFailure(Throwable t);
        }

        void getRoomList(OnRoomFinishedListener onRoomFinishedListener);

        void getLocationList(OnLocationFinishedListener onLocationFinishedListener);

        void getRoomByIdList(OnRoomByIdFinishedListener onRoomByIdFinishedListener, int locationId);

    }

    interface IView {
        void updateDataRoomToRecyclerView(List<Room> roomList);

        void setDataToLocation(List<Location> locationList);

        void setDataToRecyclerview(List<Room> roomList);

        void onResponseRoomFailure(Throwable throwable);

        void onResponseLocationFailure(Throwable throwable);

        void onResponseRoomByIdFailure(Throwable throwable);
    }

    interface IPresenter {

        void onDestroy();

        void requestDataRoomFromServer();

        void requestDataRoomByIdFromServer(int locationId);

        void requestDataLocationFromServer();
    }
}
