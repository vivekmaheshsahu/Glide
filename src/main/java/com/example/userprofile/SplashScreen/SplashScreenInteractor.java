package com.example.userprofile.SplashScreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.userprofile.Database.DatabaseContractor;
import com.example.userprofile.utility.Utility;

public class SplashScreenInteractor implements ISplashScreenInteractor {

    String count;
    private Context mContext;
    private SplashScreenPresenter mSplashScreenPresenter;

    public SplashScreenInteractor(Context mContext, SplashScreenPresenter mSplashScreenPresenter) {
        this.mContext = mContext;
        this.mSplashScreenPresenter = mSplashScreenPresenter;
    }

    @Override
    public boolean storeData(JSONObject jsonObject) {
        ContentValues values = new ContentValues();
        try {

            JSONArray CandidateJsonArray = jsonObject.getJSONArray("results");
            int callSize = CandidateJsonArray.length();
            for (int index = 0; index < callSize; index++) {
                values.clear();
                JSONObject object = CandidateJsonArray.getJSONObject(index);

                JSONObject name_object = object.optJSONObject("name");
                values.put(DatabaseContractor.COLUMN_title, name_object.optString("title"));
                values.put(DatabaseContractor.COLUMN_first, name_object.optString("first"));
                values.put(DatabaseContractor.COLUMN_last, name_object.optString("last"));
                values.put(DatabaseContractor.COLUMN_gender,object.optString("gender"));

                JSONObject location_object = object.optJSONObject("location");
                values.put(DatabaseContractor.COLUMN_city,location_object.optString("city"));
                values.put(DatabaseContractor.COLUMN_state,location_object.optString("state"));
                values.put(DatabaseContractor.COLUMN_country,location_object.optString("country"));

                JSONObject dob_object = object.optJSONObject("dob");
                values.put(DatabaseContractor.COLUMN_dob,dob_object.optString("date"));
                values.put(DatabaseContractor.COLUMN_age,dob_object.optString("age"));

                JSONObject contact_object = object.optJSONObject("registered");
                values.put(DatabaseContractor.COLUMN_registration_date, contact_object.optString("date"));

                values.put(DatabaseContractor.COLUMN_cell, object.optString("cell"));
                values.put(DatabaseContractor.COLUMN_phone, object.optString("phone"));
                values.put(DatabaseContractor.COLUMN_email, object.optString("email"));

                JSONObject picture_object = object.optJSONObject("picture");
                values.put(DatabaseContractor.COLUMN_pic, picture_object.optString("large"));

                Utility.getDatabase().insert(DatabaseContractor.TABLE_NAME, null, values);
            }
            return true;
        }
        catch(JSONException e)
        {
            Log.d("ERROR",e.toString());
            return false;
        }
    }
/*
    public boolean fetchCount() {
        boolean status;

        String query = "SELECT COUNT(*) AS remaining FROM Candidate_details";

        Cursor cursor = Utility.getDatabase().rawQuery(query, null);
        String count;
        if (cursor != null && cursor.moveToFirst()) {
            count = cursor.getString(cursor.getColumnIndex("remaining"));
            status = true;
            cursor.close();
        } else {
            status=false;
        }
        return status;
    }*/

    @Override
    public boolean CheckDataAvailablity() {
        boolean status;

        String query = "SELECT COUNT(*) AS total FROM Candidate_details";
        Cursor cursor = Utility.getDatabase().rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            count = cursor.getString(cursor.getColumnIndex("total"));
            cursor.close();
        }
        if(count.equals("0"))
        {return status = true;}
        else
        {return status = false;}

    }
}

