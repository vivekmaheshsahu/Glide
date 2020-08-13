package com.example.userprofile.Database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;

public class DatabaseManager {


    private static DatabaseManager databaseManagerInstance;
    private static SQLiteOpenHelper mDatabaseHelper;
    private AtomicInteger mOpenCounter = new AtomicInteger();        //We have a counter which indicate how many times database is opened.
    private SQLiteDatabase mDatabase;

    private DatabaseManager() {
    }

    public static synchronized void initializeInstance(SQLiteOpenHelper openHelper) {
        if (databaseManagerInstance == null) {
            databaseManagerInstance = new DatabaseManager();
            mDatabaseHelper = openHelper;
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (databaseManagerInstance == null) {
            throw new IllegalStateException(DatabaseManager.class.getSimpleName() +
                    " is not initialized, call initialize(..) method first.");
        }
        return databaseManagerInstance;
    }

    public synchronized SQLiteDatabase openDatabase() {
        if (mOpenCounter.incrementAndGet() == 1) {
            // Opening new database
            mDatabase = mDatabaseHelper.getWritableDatabase();
        }
        return mDatabase;
    }

    public synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            // Closing database
            mDatabase.close();
        }
    }
}
