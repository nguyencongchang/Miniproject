package com.example.appbookroom.login;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.appbookroom.R;

import com.example.appbookroom.databinding.ActivityLoginBinding;
import com.example.appbookroom.databinding.DialogLoginBinding;
import com.example.appbookroom.entity.User;
import com.example.appbookroom.utility.NetworkChangeListener;
import com.example.appbookroom.view.MainActivity;

public class LoginActivity extends AppCompatActivity implements ILoginContract.ILoginView {
    ActivityLoginBinding binding;
    private LoginPresenter mLoginPresenter;

    NetworkChangeListener listener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLogin();
            }
        });

    }

    private void clickLogin() {
        String strEmail = binding.etEmail.getText().toString().trim();
        String stePassword = binding.etPassword.getText().toString().trim();
        User user = new User(strEmail, stePassword,"j");
        mLoginPresenter = new LoginPresenter(LoginActivity.this);
        mLoginPresenter.callGetLoginData(user);
    }

    public void moveActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
// sai định dạng, tài khoản ko tồn tại, sai tài khoản or mật khẩu
    public void showDialogAlert(String mgs) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        LayoutInflater layoutInflater = LayoutInflater.from(LoginActivity.this);
        DialogLoginBinding dialogLoginBinding = DialogLoginBinding.inflate(layoutInflater);
        builder.setView(dialogLoginBinding.getRoot());
        AlertDialog alertDialog = builder.create();
        dialogLoginBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        dialogLoginBinding.imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        dialogLoginBinding.tv.setText(mgs);
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.shape_dialog);
        alertDialog.show();
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



    @Override
    public void onGetLoginDataSuccess(User res) {
        moveActivity();
    }

    @Override
    public void onGetLoginDataFail(String res) {
        showDialogAlert(res);
    }
}