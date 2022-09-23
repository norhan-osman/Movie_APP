package com.example.dell.Movie_App_2.ViewModelPackage;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

 import com.example.dell.Movie_App_2.RoomDB.MyAppDataBase;


public class AddFavoriteViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final MyAppDataBase mDb;
    private final int mFavoriteId;

    public AddFavoriteViewModelFactory(MyAppDataBase database, int favoriteId) {
        mDb = database;
        mFavoriteId = favoriteId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new AddFavoriteViewModel(mDb, mFavoriteId);
    }
}
