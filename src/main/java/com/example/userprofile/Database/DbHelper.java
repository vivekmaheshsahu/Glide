package com.example.userprofile.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.userprofile.Database.DatabaseContractor.*;

import java.io.File;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, DB_LOCATION + File.separator + DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
