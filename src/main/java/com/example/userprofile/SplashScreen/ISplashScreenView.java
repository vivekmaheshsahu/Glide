package com.example.userprofile.SplashScreen;

import com.example.userprofile.utility.IMvpView;

public interface ISplashScreenView extends IMvpView{

    public void openMainActivity();
    public void errorInDownloadData();
    void showProgressBar();
    void hideProgressBar();

}
