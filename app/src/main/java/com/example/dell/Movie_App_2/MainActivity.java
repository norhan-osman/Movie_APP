package com.example.dell.Movie_App_2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.dell.Movie_App_2.RecyclerViewUtilis.MoviesAdapter;
import com.example.dell.Movie_App_2.RetrofirConnection.ApiClient;
import com.example.dell.Movie_App_2.RetrofirConnection.ApiInterface;
import com.example.dell.Movie_App_2.model.Movie;
import com.example.dell.Movie_App_2.model.MoviesResponse;
import com.example.dell.Movie_App_2.model.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    ProgressDialog pd;
    private AppCompatActivity activity = MainActivity.this;
    public static final String LOG_TAG = MoviesAdapter.class.getName();

     private Parcelable savedRecyclerLayoutState;
    private ArrayList<Movie> moviesInstance = new ArrayList<>();

     TextView textView;
      String Item;

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
textView = findViewById(R.id.textView_choose);

        adapter = new MoviesAdapter(this, moviesInstance);

        if (savedInstanceState != null){
          moviesInstance = savedInstanceState.getParcelableArrayList("list_state");
            savedRecyclerLayoutState = savedInstanceState.getParcelable("_layout");

            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            adapter = new MoviesAdapter(this, moviesInstance);

            if (getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                recyclerView.setLayoutManager(new GridLayoutManager(this, calculateNoOfColumns(this)));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(this, calculateNoOfColumns(this)));
            }

            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);


            if (savedRecyclerLayoutState != null) {
                recyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
            }            adapter.notifyDataSetChanged();




        }


        else {
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            if (getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                recyclerView.setLayoutManager(new GridLayoutManager(this, calculateNoOfColumns(this)));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(this, calculateNoOfColumns(this)));
            }

            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
        }



          recyclerView.addOnItemTouchListener(
                  new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                      @Override public void onItemClick(View view, int position) {
                          // do whatever

                          Intent intent = new Intent(getBaseContext(), MovieDetails.class);
                          intent.putExtra("Position", position);
                           intent.putExtra("Item", Item);

                          startActivity(intent);



                      }

                      @Override public void onLongItemClick(View view, int position) {
                          // do whatever
                      }
                  })
          );

    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList("list_state", moviesInstance);
        savedInstanceState.putParcelable("_layout", recyclerView.getLayoutManager().onSaveInstanceState());
     }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        moviesInstance = savedInstanceState.getParcelableArrayList("list_state");
        savedRecyclerLayoutState = savedInstanceState.getParcelable("_layout");
         super.onRestoreInstanceState(savedInstanceState);
    }




    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 200;
        int noOfColumns = (int) (dpWidth / scalingFactor);
        if(noOfColumns < 2)
            noOfColumns = 2;
        return noOfColumns;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.popular_movie:
                textView.setVisibility(View.GONE);
                Item = "popular movie";
                ApiClient Client = new ApiClient();
                ApiInterface apiService =
                        Client.getClient().create(ApiInterface.class);
                Call<MoviesResponse> call = apiService.getpopularMovies();
                call.enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                        if (response.isSuccessful()) {

                            if (response.body() != null) {
                                List<Movie> movies = response.body().getResults();
                                moviesInstance.addAll(movies);
                                recyclerView.setAdapter(new MoviesAdapter(getApplicationContext(), movies));
                                recyclerView.smoothScrollToPosition(0);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        Log.d("Error", t.getMessage());

                    }
                });
                return true;
            case R.id.top_rated_movie:
                textView.setVisibility(View.GONE);
                Item = "top rated movie";
                Client = new ApiClient();
                  apiService =
                        Client.getClient().create(ApiInterface.class);
                  call = apiService.getTopRatedMovies();
                call.enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                        if (response.isSuccessful()) {

                            if (response.body() != null) {
                                List<Movie> movies = response.body().getResults();
                                moviesInstance.addAll(movies);
                                recyclerView.setAdapter(new MoviesAdapter(getApplicationContext(), movies));
                                recyclerView.smoothScrollToPosition(0);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        Log.d("Error", t.getMessage());

                    }
                });                return true;
            case R.id.favorite_movie:
                startActivity(new Intent(getApplicationContext(),Favorite.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


 }
