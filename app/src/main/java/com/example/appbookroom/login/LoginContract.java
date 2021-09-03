package com.example.appbookroom.login;

public interface LoginContract {

    //Model
    interface Model{
        interface OnFinishedListener {
            void onFinished();
            void onFailure();
        }
        void getMovieList(OnFinishedListener onFinishedListener, int pageNo);
    }

    //View
    interface View{
        void showProgress();
        void hideProgress();
        void setDataToRecyclerview();
        void onResponseFailure();
    }

    //Presenter
    interface Presenter{

    }
}
