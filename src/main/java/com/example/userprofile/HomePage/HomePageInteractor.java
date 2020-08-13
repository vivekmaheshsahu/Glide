package com.example.userprofile.HomePage;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.userprofile.utility.Utility;
import com.example.userprofile.Database.DatabaseContractor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomePageInteractor implements IHomePageInteractor {

    @Override
    public Cursor fetchCandidateDate() {
        return Utility.getDatabase().rawQuery("SELECT * FROM Candidate_details ORDER BY Id DESC",null);
    }

    @Override
    public void UpdateStatus(int id, String text) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContractor.COLUMN_status, text);
        Utility.getDatabase().update(DatabaseContractor.TABLE_NAME
                , values
                , DatabaseContractor.COLUMN_ID + " = ? "
                , new String[]{String.valueOf(id)});
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

}
