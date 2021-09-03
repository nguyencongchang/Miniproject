package com.example.appbookroom.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appbookroom.R;
import com.example.appbookroom.adapters.HomeListRoomAdapter;
import com.example.appbookroom.adapters.LocationSpinnerAdapter;

import com.example.appbookroom.databinding.FragmentHomeBinding;
import com.example.appbookroom.entity.Location;
import com.example.appbookroom.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IHomeListContract.IView {

    private HomeListRoomAdapter homeListRoomAdapter;
    private FragmentHomeBinding binding;
    private HomePresenter homePresenter;
    private LocationSpinnerAdapter locationSpinnerAdapter;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRoomAdapter();
        initSpinnerLocationAdapter();
        homePresenter = new HomePresenter(this);
        homePresenter.requestDataRoomFromServer();
        homePresenter.requestDataLocationFromServer();
    }

    private void initRoomAdapter(){
        homeListRoomAdapter = new HomeListRoomAdapter();
        List<Room> roomList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rcShowlistroom.setLayoutManager(linearLayoutManager);
        homeListRoomAdapter.setData(roomList);
        binding.rcShowlistroom.setAdapter(homeListRoomAdapter);
    }
    private void initSpinnerLocationAdapter(){
        List<Location> locationList = new ArrayList<>();
        locationSpinnerAdapter = new LocationSpinnerAdapter(getContext(), R.layout.item_selected_location, locationList);
        binding.spinnerLocation.setAdapter(locationSpinnerAdapter);
    }
    @Override
    public void updateDataRoomToRecyclerView(List<Room> roomList) {
        homeListRoomAdapter.setData(roomList);
    }

    @Override
    public void setDataToLocation(List<Location> locationList) {
        locationSpinnerAdapter.clear();
        locationSpinnerAdapter.addAll(locationList);
        locationSpinnerAdapter.notifyDataSetChanged();
        binding.spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Location locationSelected = locationSpinnerAdapter.getItem(i);
                if(!locationSelected.getName().equalsIgnoreCase("Tất cả")){
                    homePresenter.requestDataRoomByIdFromServer(locationSelected.getId());
                }else {
                    homePresenter.requestDataRoomFromServer();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void setDataToRecyclerview(List<Room> roomList) {
        homeListRoomAdapter.setData(roomList);
    }

    @Override
    public void onResponseRoomFailure(Throwable throwable) {
        Log.e("ERROR", throwable.getMessage());
        Toast.makeText(getContext(), "Error in getting data", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponseLocationFailure(Throwable throwable) {
        Log.e("ERROR", throwable.getMessage());
        Toast.makeText(getContext(), "Error in getting location data", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponseRoomByIdFailure(Throwable throwable) {
        Log.e("ERROR", throwable.getMessage());
        Toast.makeText(getContext(), "Error in getting room data in location", Toast.LENGTH_LONG).show();
    }
}
