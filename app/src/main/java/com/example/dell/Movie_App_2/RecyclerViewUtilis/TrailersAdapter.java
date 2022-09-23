package com.example.dell.Movie_App_2.RecyclerViewUtilis;

import android.annotation.SuppressLint;
import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.dell.Movie_App_2.R;

import java.util.List;


public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.MovieViewHolder> {

    private List<String> movies;
    private Context context;


    public TrailersAdapter(List<String> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @Override
    public TrailersAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_vedios, parent, false);
        return new MovieViewHolder(view);
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {

        for (int i = 0 ; i <movies.size() ; i++) {
            holder.textView.setText("Trailer " + (position+1));
        }
        holder.imageView.setImageResource(R.drawable.ic_video_library_black_24dp);


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }



    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public MovieViewHolder(View v) {
            super(v);
            imageView =  v.findViewById(R.id.img_trailers);
            textView =  v.findViewById(R.id.textView_trailers);

        }
    }
}
