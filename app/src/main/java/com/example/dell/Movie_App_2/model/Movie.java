package com.example.dell.Movie_App_2.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


import java.util.List;


public class Movie implements Parcelable {
    @SerializedName("title")
    private String title;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("poster_path")
    private String moviePoster;
    @SerializedName("vote_average")
    private String voteAverage;
    @SerializedName("overview")
    private String plotSynopsis;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("id")
    private String id;
    @SerializedName("content")
    private String content;
    @SerializedName("key")
    private List<String> key;




    public Movie(String title, String releaseDate, String moviePoster, String voteAverage, String plotSynopsis, String backdropPath, String id, String content, List<String> key) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.moviePoster = moviePoster;
        this.voteAverage = voteAverage;
        this.plotSynopsis = plotSynopsis;
        this.backdropPath = backdropPath;
        this.id = id;
        this.content = content;
        this.key = key;
    }


    protected Movie(Parcel in) {
        title = in.readString();
        releaseDate = in.readString();
        moviePoster = in.readString();
        voteAverage = in.readString();
        plotSynopsis = in.readString();
        backdropPath = in.readString();
        id = in.readString();
        content = in.readString();
        key = in.createStringArrayList();
    }
    public Movie(){

    }
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public List<String> getKey() {
        return key;
    }

    public void setKey(List<String> key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public void setPlotSynopsis(String plotSynopsis) {
        this.plotSynopsis = plotSynopsis;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(releaseDate);
        parcel.writeString(moviePoster);
        parcel.writeString(voteAverage);
        parcel.writeString(plotSynopsis);
        parcel.writeString(backdropPath);
        parcel.writeString(id);
        parcel.writeString(content);
        parcel.writeStringList(key);
    }
}
