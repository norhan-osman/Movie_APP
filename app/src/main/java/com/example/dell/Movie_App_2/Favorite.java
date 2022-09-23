package com.example.dell.Movie_App_2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dell.Movie_App_2.RoomDB.MoviesTable;
import com.example.dell.Movie_App_2.ViewModelPackage.MainViewModel;

import java.util.List;

public class Favorite extends AppCompatActivity {

    MainViewModel viewModel;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        textView = findViewById(R.id._saved);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        viewModel.getFavorite().observe(this, new Observer<List<MoviesTable>>() {

            @Override
            public void onChanged(@Nullable List<MoviesTable> imageEntries) {
                assert imageEntries != null;
                for (MoviesTable entry : imageEntries){

                    textView.append("ID : " + entry.getMovieid() + "\n\n"
                    + "Title : " + entry.getTitle() + "\n\n");

                 }

             }
        });

    }
}
