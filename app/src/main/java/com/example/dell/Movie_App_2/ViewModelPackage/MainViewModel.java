package com.example.dell.Movie_App_2.ViewModelPackage;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

 import com.example.dell.Movie_App_2.RoomDB.MoviesTable;
import com.example.dell.Movie_App_2.RoomDB.MyAppDataBase;

import java.util.List;


public class MainViewModel extends AndroidViewModel {

    // Constant for logging
    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<MoviesTable>> favorite;

    public MainViewModel(Application application) {
        super(application);
        MyAppDataBase database = MyAppDataBase.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving the tasks from the DataBase");
        favorite = database.myDao().getAllMovies();
    }

    public LiveData<List<MoviesTable>> getFavorite() {
        return favorite;
    }
}
