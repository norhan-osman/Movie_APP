package com.example.dell.Movie_App_2.RetrofirConnection;


import com.example.dell.Movie_App_2.model.MoviesResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiInterface {

    @GET("movie/popular?api_key=3db2c1d15b330e8595099a2bab78dce2")
    Call<MoviesResponse> getpopularMovies();

    @GET("movie/top_rated?api_key=3db2c1d15b330e8595099a2bab78dce2")
    Call<MoviesResponse> getTopRatedMovies();

    @GET("movie/{id}/reviews?api_key=3db2c1d15b330e8595099a2bab78dce2")
    Call<MoviesResponse> getMovieReviews(@Path("id") String id);

    @GET("movie/{id}/videos?api_key=3db2c1d15b330e8595099a2bab78dce2")
    Call<ResponseBody> getMovieTrailers(@Path("id") String id);

}
