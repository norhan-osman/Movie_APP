package com.example.dell.Movie_App_2.RoomDB;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MyDao {


    @Insert
    public void addMovie(MoviesTable moviesTable);

    @Query("select * from  movietable")
    LiveData<List<MoviesTable>> getAllMovies();


     @Delete
    public void DelteMovie(MoviesTable moviesTable);

    @Query("SELECT * FROM movietable WHERE id = :id")
    LiveData<MoviesTable> loadFavoriteById(int id);


    @Query("DELETE FROM movietable WHERE movieid = :movie_id")
    void deleteFavoriteWithId(String movie_id);
}
