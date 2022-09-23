package com.example.dell.Movie_App_2.RecyclerViewUtilis;

import android.content.Context;
 import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



import com.bumptech.glide.Glide;
import com.example.dell.Movie_App_2.R;
import com.example.dell.Movie_App_2.model.Movie;

import java.util.List;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private Context mContext;
    private List<Movie> movieList;


    public MoviesAdapter(Context mContext, List<Movie> movieList){
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_card, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MoviesAdapter.MyViewHolder viewHolder, int i){

        String poster = "https://image.tmdb.org/t/p/w185" + movieList.get(i).getMoviePoster();

        Glide.with(mContext)
                .load(poster)
                 .into(viewHolder.imageView);

    }

    public void setMovies(List<Movie> movie) {
        movieList = movie;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
         public ImageView imageView;

        public MyViewHolder(View view){
            super(view);

            imageView = (ImageView) view.findViewById(R.id.poster_img);


        }
    }
}
