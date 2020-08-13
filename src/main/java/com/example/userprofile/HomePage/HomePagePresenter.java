package com.example.userprofile.HomePage;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.userprofile.Data.Candidate_details;
import com.example.userprofile.Database.DatabaseContractor;
import com.example.userprofile.NetworkCall.AppController;

import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class HomePagePresenter implements IHomePagePresenter<HomePage> {

    public IHomePage iHomeActivityView;
    HomePageInteractor homeActivityInteractor;
    Candidate_details  candidate_details;

    @Override
    public void attachView(HomePage view) {
        iHomeActivityView = view;
        homeActivityInteractor = new HomePageInteractor();
    }

    @Override
    public void detachView() {
        iHomeActivityView = null;
    }

    @Override
    public void fetchCandidateData() {
        candidate_details = new Candidate_details();
        List<Candidate_details> DataList = new ArrayList<>();
        Cursor cursor = homeActivityInteractor.fetchCandidateDate();

        while (cursor.moveToNext()) {
            Candidate_details model = new Candidate_details();
            model.setTitle(cursor.getString(cursor.getColumnIndex(DatabaseContractor.COLUMN_title)));
            model.setFirst_name(cursor.getString(cursor.getColumnIndex(DatabaseContractor.COLUMN_first)));
            model.setLast_name(cursor.getString(cursor.getColumnIndex(DatabaseContractor.COLUMN_last)));
            model.setGender(cursor.getString(cursor.getColumnIndex(DatabaseContractor.COLUMN_gender)));
            model.setCity(cursor.getString(cursor.getColumnIndex(DatabaseContractor.COLUMN_city)));
            model.setState(cursor.getString(cursor.getColumnIndex(DatabaseContractor.COLUMN_state)));
            model.setCountry(cursor.getString(cursor.getColumnIndex(DatabaseContractor.COLUMN_country)));
            model.setDob(cursor.getString(cursor.getColumnIndex(DatabaseContractor.COLUMN_dob)));
            model.setAge(cursor.getString(cursor.getColumnIndex(DatabaseContractor.COLUMN_age)));
            model.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseContractor.COLUMN_phone)));
            model.setCell(cursor.getString(cursor.getColumnIndex(DatabaseContractor.COLUMN_cell)));
            model.setRegistration_date(cursor.getString(cursor.getColumnIndex(DatabaseContractor.COLUMN_registration_date)));
            model.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseContractor.COLUMN_email)));
            model.setId(cursor.getString(cursor.getColumnIndex(DatabaseContractor.COLUMN_ID)));
            model.setUrl(cursor.getString(cursor.getColumnIndex(DatabaseContractor.COLUMN_pic)));
            model.setStatus(cursor.getString(cursor.getColumnIndex(DatabaseContractor.COLUMN_status)));
            DataList.add(model);
        }
        iHomeActivityView.setAdapter(DataList);
    }

    @Override
    public void downloadMoreData() {
        String tag_json_obj = "json_obj_req";

            String url = "https://randomuser.me/api/?results=10";

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                    url, null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d(TAG, response.toString());
                            boolean flag = homeActivityInteractor.storeData(response);
                            if(flag)
                            {iHomeActivityView.hideprogressbar();}
                            else
                            {iHomeActivityView.errorDisplay();}



                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    // hide the progress dialog
                }
            });
            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
        }

}
