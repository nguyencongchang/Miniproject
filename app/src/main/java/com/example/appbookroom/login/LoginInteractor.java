package com.example.appbookroom.login;

import com.example.appbookroom.entity.User;
import com.example.appbookroom.networkUtils.ClientApi;
import com.example.appbookroom.networkUtils.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractor implements ILoginContract.ILoginModel {
    LoginService loginService;

    @Override
    public void getLoginData(User user, OnLoginOnLoadListener listener) {
        loginService = ClientApi.getClientApi().create(LoginService.class);
        loginService.login(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response != null) {
                    switch (response.code()) {
                        case 200:
                            listener.onSuccess(user);
                            break;
                        case 400:
                            listener.onFailure("Email hoặc Mật khẩu chưa chính xác");
                            break;
                        case 404:
                            listener.onFailure("Tài khoản không tồn tại");
                    }
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onFailure("Lỗi");
            }
        });
    }

    /**
     * CallBack
     */
    public interface OnLoginOnLoadListener {
        void onSuccess(User user);

        void onFailure(String msg);
    }

}
