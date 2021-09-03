package com.example.appbookroom.view;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.appbookroom.R;
import com.example.appbookroom.home.HomeFragment;
import com.example.appbookroom.utility.NetworkChangeListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBtNavMainActivity;
    NetworkChangeListener listener = new NetworkChangeListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mBtNavMainActivity.setOnNavigationItemSelectedListener(navListener);
        setFragment(new HomeFragment());
    }

    private void init() {
        mBtNavMainActivity = findViewById(R.id.btnav_main_activity);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_home:
                    setFragment(new HomeFragment());
                    break;
                case R.id.action_calendar:
                    setFragment(new CalendarFragment());
                    break;
                case R.id.action_account:
                    setFragment(new AccountFragment());
                    break;
            }
            return true;
        }
    };

    private void setFragment(Fragment selectedFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment_main, selectedFragment).commit();
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