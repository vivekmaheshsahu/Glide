package com.example.userprofile.utility;

public interface IBasePresenter<V> {

    void attachView(V view);

    void detachView();

}