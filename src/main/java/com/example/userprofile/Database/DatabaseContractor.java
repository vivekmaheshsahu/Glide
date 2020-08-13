package com.example.userprofile.Database;

import android.os.Environment;

public class DatabaseContractor {

    public static final String DATABASE_NAME = "task.sr";
    public static final int DATABASE_VERSION = 1;
    public static final String DB_LOCATION = Environment.getExternalStorageDirectory() + "/TASK";
    public static final String TEXT_TYPE = " TEXT";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String COMMA_SEP = ",";

    public static final String TABLE_NAME = "Candidate_details";
    public static final String COLUMN_ID="Id";
    public static final String COLUMN_title="title";
    public static final String COLUMN_first="first_name";
    public static final String COLUMN_last="last_name";
    public static final String COLUMN_email="email";
    public static final String COLUMN_city="city";
    public static final String COLUMN_state="states";
    public static final String COLUMN_country="country";
    public static final String COLUMN_dob="dob";
    public static final String COLUMN_age="age";
    public static final String COLUMN_phone="phone";
    public static final String COLUMN_cell="cell";
    public static final String COLUMN_registration_date="registration_date";
    public static final String COLUMN_gender="gender";
    public static final String COLUMN_pic="picture_url";
    public static final String COLUMN_status = "status";

        public static final String CREATE_TABLE ="CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "("+
                COLUMN_ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                COLUMN_title + TEXT_TYPE + COMMA_SEP +
                COLUMN_first + TEXT_TYPE + COMMA_SEP +
                COLUMN_last + TEXT_TYPE + COMMA_SEP +
                COLUMN_gender + TEXT_TYPE + COMMA_SEP +
                COLUMN_city + TEXT_TYPE + COMMA_SEP +
                COLUMN_state + TEXT_TYPE + COMMA_SEP +
                COLUMN_country + TEXT_TYPE + COMMA_SEP +
                COLUMN_dob + TEXT_TYPE + COMMA_SEP +
                COLUMN_age + TEXT_TYPE + COMMA_SEP +
                COLUMN_phone + INTEGER_TYPE + COMMA_SEP +
                COLUMN_cell + TEXT_TYPE + COMMA_SEP +
                COLUMN_registration_date + TEXT_TYPE + COMMA_SEP +
                COLUMN_pic + TEXT_TYPE + COMMA_SEP +
                COLUMN_email + TEXT_TYPE + COMMA_SEP +
                COLUMN_status + TEXT_TYPE +
                ")";
    }

