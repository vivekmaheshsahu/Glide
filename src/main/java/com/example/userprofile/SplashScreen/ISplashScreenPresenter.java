package com.example.userprofile.SplashScreen;

import com.example.userprofile.utility.IBasePresenter;

import java.util.List;

public interface ISplashScreenPresenter<v> extends IBasePresenter<v> {

    boolean checkPermissions();

    void getPermissions(List<String> listPermissionsNeeded);

    void initializeDBHelper();

    void networkCall();

}
