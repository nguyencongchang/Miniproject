package com.example.appbookroom.login;

import com.example.appbookroom.entity.User;


public interface ILoginContract {

    /**
     * View
    */
    interface ILoginView {

        void onGetLoginDataSuccess(User res);
        void onGetLoginDataFail(String res);
    }

    /**
     * Model
     */
    interface ILoginModel {
        void getLoginData(User user, LoginInteractor.OnLoginOnLoadListener listener);
    }

    /**
     * Presenter
     */
    interface ILoginPresenter {

        void callGetLoginData(User user);

    }
}
