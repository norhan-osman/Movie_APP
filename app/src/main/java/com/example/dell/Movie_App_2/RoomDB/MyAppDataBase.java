package com.example.dell.Movie_App_2.RoomDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

@Database(entities = {MoviesTable.class},version = 1)
public abstract class MyAppDataBase extends RoomDatabase {

    private static final String LOG_TAG = MyAppDataBase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "favorite";
    private static MyAppDataBase sInstance;

    public static MyAppDataBase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        MyAppDataBase.class, MyAppDataBase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(LOG_TAG, "database instance");
        return sInstance;
    }

     public abstract MyDao myDao();
}
