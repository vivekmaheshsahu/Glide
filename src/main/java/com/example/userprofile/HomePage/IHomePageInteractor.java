package com.example.userprofile.HomePage;

import android.database.Cursor;

import org.json.JSONObject;

public interface IHomePageInteractor {

    Cursor fetchCandidateDate();
    public void UpdateStatus(int id,String text);
    boolean storeData(JSONObject jsonObject);

}
