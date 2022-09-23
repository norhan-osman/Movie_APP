package com.example.dell.Movie_App_2.RoomDB;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "movietable")
public class MoviesTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "movieid")
    private String movieid;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "userrating")
    private String userrating;

    @ColumnInfo(name = "posterpath")
    private String posterpath;

    @ColumnInfo(name = "overview")
    private String overview;

    @Ignore
    public MoviesTable(String movieid, String title, String userrating, String posterpath, String overview) {
        this.movieid = movieid;
        this.title = title;
        this.userrating = userrating;
        this.posterpath = posterpath;
        this.overview = overview;
    }

    public MoviesTable(int id, String movieid, String title, String userrating, String posterpath, String overview) {
        this.id = id;
        this.movieid = movieid;
        this.title = title;
        this.userrating = userrating;
        this.posterpath = posterpath;
        this.overview = overview;
    }

    public MoviesTable(String movieid, String title) {
        this.movieid = movieid;
        this.title = title;
    }

    public MoviesTable() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieid() {
        return movieid;
    }

    public void setMovieid(String movieid) {
        this.movieid = movieid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserrating() {
        return userrating;
    }

    public void setUserrating(String userrating) {
        this.userrating = userrating;
    }

    public void setPosterpath(String posterpath){ this.posterpath = posterpath; }

    public String getPosterpath() { return posterpath; }

    public void setOverview(String image) { this.overview = overview; }

    public String getOverview() { return overview; }

}
