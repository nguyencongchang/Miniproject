package com.example.appbookroom.login;

import android.util.Log;

import com.example.appbookroom.entity.User;

public class LoginPresenter implements ILoginContract.ILoginPresenter {
    private ILoginContract.ILoginModel mLoginModel;
    private ILoginContract.ILoginView mLoginView;
    private String EMAIL_FORMAT = "@ttc-solutions.com.vn";
    private String PASSWORDL_FORMAT = "12345678";
    private LoginInteractor.OnLoginOnLoadListener onLoginOnLoadListener;

    public LoginPresenter(ILoginContract.ILoginView loginView) {
        this.mLoginView = loginView;
        mLoginModel = new LoginInteractor();
    }

    @Override
    public void callGetLoginData(User user) {
        //validate

        if (user.isValidEmail() || user.isValidPassword()) {
            mLoginView.onGetLoginDataFail("Email hoặc Mật khẩu không được để trống");
        } else if (user.getUsername().endsWith(EMAIL_FORMAT) || user.getPassword().equals(PASSWORDL_FORMAT)){
            mLoginView.onGetLoginDataFail("Email hoặc mật khẩu không đúng định dạng");
        }else {
            Log.e("TAG", user.toString() );
            mLoginModel.getLoginData(user, new LoginInteractor.OnLoginOnLoadListener() {
                @Override
                public void onSuccess(User user) {
                    if (mLoginView != null) {
                        mLoginView.onGetLoginDataSuccess(user);
                    }
                }

                @Override
                public void onFailure(String msg) {
                    if (mLoginView != null)
                        mLoginView.onGetLoginDataFail(msg);
                }


            });
        }
    }
}
