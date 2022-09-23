package com.example.dell.Movie_App_2.ViewModelPackage;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;


import com.example.dell.Movie_App_2.RoomDB.MoviesTable;
import com.example.dell.Movie_App_2.RoomDB.MyAppDataBase;


public class AddFavoriteViewModel extends ViewModel {

    private LiveData<MoviesTable> favorite;

    public AddFavoriteViewModel(MyAppDataBase mDb, int mFavoriteId) {
        favorite = mDb.myDao().loadFavoriteById(mFavoriteId);

    }


    public LiveData<MoviesTable> getFavorite() {
        return favorite;
    }
}
