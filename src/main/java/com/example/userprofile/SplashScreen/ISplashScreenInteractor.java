package com.example.userprofile.SplashScreen;

import org.json.JSONObject;

public interface ISplashScreenInteractor {

    boolean storeData(JSONObject jsonObject);
    boolean CheckDataAvailablity();

}
