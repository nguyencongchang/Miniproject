package com.example.appbookroom.view;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.appbookroom.R;
import com.example.appbookroom.databinding.ActivityBookRoomBinding;
import com.example.appbookroom.fragment.BlurAlertDialogFragment;
import com.example.appbookroom.utility.NetworkChangeListener;

public class BookRoomActivity extends AppCompatActivity {
    ActivityBookRoomBinding roomBinding;
    NetworkChangeListener listener = new NetworkChangeListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        roomBinding = DataBindingUtil.setContentView(this, R.layout.activity_book_room);

        roomBinding.tvTime.setOnClickListener(v -> {
            BlurAlertDialogFragment blurAlertDialogFragment = new BlurAlertDialogFragment("showDialogChoseTime", new BlurAlertDialogFragment.OnBlurAlertDialogListener() {
                @Override
                public void onUpdated(String from, String to) {

                    Log.d("TAG", "onUpdated: " + from + to);
                    roomBinding.tvTime.setText(from + " - " + to);
                }
            });
            blurAlertDialogFragment.show(getSupportFragmentManager(), blurAlertDialogFragment.getClass().getSimpleName());
        });
        roomBinding.btnBook.setOnClickListener(v -> {
            BlurAlertDialogFragment blurAlertDialogFragment = new BlurAlertDialogFragment("showDialogStatusSuccess", new BlurAlertDialogFragment.OnBlurAlertDialogListener() {
                @Override
                public void onUpdated(String from, String to) {
                }
            });
            blurAlertDialogFragment.show(getSupportFragmentManager(), blurAlertDialogFragment.getClass().getSimpleName());
        });
        roomBinding.imgInsert.setOnClickListener(v -> {
            BlurAlertDialogFragment blurAlertDialogFragment = new BlurAlertDialogFragment("showDialogInsertUser", new BlurAlertDialogFragment.OnBlurAlertDialogListener() {
                @Override
                public void onUpdated(String from, String to) {
                }
            });
            blurAlertDialogFragment.show(getSupportFragmentManager(), blurAlertDialogFragment.getClass().getSimpleName());
        });
    }



    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(listener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(listener);
        super.onStop();
    }


}
