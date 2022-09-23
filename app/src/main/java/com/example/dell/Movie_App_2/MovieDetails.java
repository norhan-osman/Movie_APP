package com.example.dell.Movie_App_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.Movie_App_2.RecyclerViewUtilis.TrailersAdapter;
import com.example.dell.Movie_App_2.RetrofirConnection.ApiClient;
import com.example.dell.Movie_App_2.RetrofirConnection.ApiInterface;
import com.example.dell.Movie_App_2.RoomDB.MoviesTable;
import com.example.dell.Movie_App_2.RoomDB.MyAppDataBase;
import com.example.dell.Movie_App_2.ViewModelPackage.AppExecutors;
import com.example.dell.Movie_App_2.model.Movie;
import com.example.dell.Movie_App_2.model.MoviesResponse;
import com.example.dell.Movie_App_2.model.RecyclerItemClickListener;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetails extends AppCompatActivity {

    int position;
    String Item;
    private static final String TAG = MovieDetails.class.getSimpleName();
    List<Movie> movies , movieStage2;

    TextView textView_det , textView_view ,textView_review;
    ImageView imageView_pos;

    RecyclerView recyclerView;


    List<String> keys = new ArrayList<>();
    CheckBox star;

    private MyAppDataBase mDb;

    private  ArrayList<String> IDS ;
    MoviesTable moviesTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        IDS = new ArrayList<>();

        mDb = MyAppDataBase.getInstance(getApplicationContext());



        moviesTable = new MoviesTable();


        textView_det = findViewById(R.id.textView_details);
        textView_view = findViewById(R.id.textView_overview);
        textView_review = findViewById(R.id.textView_reviews);


        imageView_pos = findViewById(R.id.imageView_poster);

        position = getIntent().getIntExtra("Position",-1);
        Item = getIntent().getStringExtra("Item");

        star = findViewById(R.id.checkBox);

        recyclerView = findViewById(R.id._Trailers);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        switch (position)
         {
             case 0 :
                 SetMovieDetails(0);
                 getMovieID(0);
                 break;
             case 1 :
                 SetMovieDetails(1);
                 getMovieID(1);

                 break;
             case 2 :
                 SetMovieDetails(2);
                 getMovieID(2);

                 break;
             case 3 :
                 SetMovieDetails(3);
                 getMovieID(3);


                 break;
             case 4 :
                 SetMovieDetails(4);
                 getMovieID(4);

                 break;
             case 5 :
                 SetMovieDetails(5);
                 getMovieID(5);

                 break;
             case 6 :
                 SetMovieDetails(6);
                 getMovieID(6);

                 break;
             case 7 :
                 SetMovieDetails(7);
                 getMovieID(7);

                 break;
             case 8 :
                 SetMovieDetails(8);
                 getMovieID(8);

                 break;
             case 9 :
                 SetMovieDetails(9);
                 getMovieID(9);

                 break;
             case 10 :
                 SetMovieDetails(10);
                 getMovieID(10);

                 break;
             case 11 :
                 SetMovieDetails(11);
                 getMovieID(11);

                 break;
             case 12 :
                 SetMovieDetails(12);
                 getMovieID(12);

                 break;
             case 13 :
                 SetMovieDetails(13);
                 getMovieID(13);

                 break;
             case 14 :
                 SetMovieDetails(14);
                 getMovieID(14);

                 break;
             case 15 :
                 SetMovieDetails(15);
                 getMovieID(15);

                 break;
             case 16 :
                 SetMovieDetails(16);
                 getMovieID(16);

                 break;
             case 17 :
                 SetMovieDetails(17);
                 getMovieID(17);

                 break;
             case 18 :
                 SetMovieDetails(18);
                 getMovieID(18);
                 break;
             case 19 :
                 SetMovieDetails(19);
                 getMovieID(19);

                 break;


         }


         recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
             @Override
             public void onItemClick(View view, int position) {

                 Intent intent = new Intent(Intent.ACTION_VIEW,
                         Uri.parse("http://www.youtube.com/watch?v=" + keys.get(position)));
                 startActivity(intent);


             }

             @Override
             public void onLongItemClick(View view, int position) {

             }
         }));






    }


    private void getMovieID(final int position)
    {

        if (Item.equals("popular movie"))
        {
            ApiClient Client = new ApiClient();
            ApiInterface apiService =
                    Client.getClient().create(ApiInterface.class);

            Call<MoviesResponse> call = apiService.getpopularMovies();
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse>call, Response<MoviesResponse> response) {
                    movies = response.body().getResults();
                    Log.d(TAG, "Number of movies received: " + movies.size());

                    textView_det.append(movies.get(position).getTitle()+"\n\n"+movies.get(position).getReleaseDate()
                            +"\n\n"+movies.get(position).getVoteAverage());
                    textView_view.append(movies.get(position).getPlotSynopsis());
                    Picasso.get()
                            .load("http://image.tmdb.org/t/p/w185/"+movies.get(position).getBackdropPath())
                            .into(imageView_pos);
                    getMovieReviews(movies.get(position).getId());
                    getMovieTrailers(movies.get(position).getId());


                    for (int i = 0 ; i<loadData().size() ; i++) {

                        if (loadData().get(i).equals(movies.get(position).getId()))
                        {
                            star.post(new Runnable() {
                        @Override
                        public void run() {
                            star.setChecked(true);
                        }
                    });

                        }



                    }

                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                }
            });
        }
        else
        {
            ApiClient Client = new ApiClient();
            ApiInterface apiService =
                    Client.getClient().create(ApiInterface.class);

            Call<MoviesResponse> call = apiService.getTopRatedMovies();
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse>call, Response<MoviesResponse> response) {
                    movies = response.body().getResults();
                    Log.d(TAG, "Number of movies received: " + movies.size());

                    textView_det.append(movies.get(position).getTitle()+"\n\n"+movies.get(position).getReleaseDate()
                            +"\n\n"+movies.get(position).getVoteAverage());
                    textView_view.append(movies.get(position).getPlotSynopsis());
                    Picasso.get()
                            .load("http://image.tmdb.org/t/p/w185/"+movies.get(position).getBackdropPath())
                            .into(imageView_pos);
                    getMovieReviews(movies.get(position).getId());
                    getMovieTrailers(movies.get(position).getId());

                    for (int i = 0 ; i<loadData().size() ; i++) {

                        if (loadData().get(i).equals(movies.get(position).getId()))
                        {
                            star.post(new Runnable() {
                                @Override
                                public void run() {
                                    star.setChecked(true);
                                }
                            });

                        }



                    }



                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                }
            });
        }


    }

    private void SetMovieDetails(final int position)
    {

        if (Item.equals("popular movie"))
        {
            ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

            Call<MoviesResponse> call = apiService.getpopularMovies();
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse>call, Response<MoviesResponse> response) {
                    movies = response.body().getResults();
                    Log.d(TAG, "Number of movies received: " + movies.size());

                    textView_det.append(movies.get(position).getTitle()+"\n\n"+movies.get(position).getReleaseDate()
                            +"\n\n"+movies.get(position).getVoteAverage());
                    textView_view.append(movies.get(position).getPlotSynopsis());
                    Picasso.get()
                            .load("http://image.tmdb.org/t/p/w185/"+movies.get(position).getBackdropPath())
                            .into(imageView_pos);
                    getMovieReviews(movies.get(position).getId());
                    getMovieTrailers(movies.get(position).getId());

                    star.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            if (star.isChecked())
                            {
                                //Perform action when you touch on checkbox and it change to selected state



                                    IDS.add(movies.get(position).getId());
                                    saveData();
                                    IDS.remove(0);
                                final MoviesTable favoriteEntry = new MoviesTable(String.valueOf(movies.get(position).getId()),
                                        movies.get(position).getTitle());
                                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        mDb.myDao().addMovie(favoriteEntry);
                                    }
                                });


                            }
                            else
                            {
                                //Perform action when you touch on checkbox and it change to unselected state

                                for (int i = 0 ; i < IDS.size() ; i++)
                                {
                                   if (IDS.get(i).equals(movies.get(position).getId())) {
                                       IDS.remove(i);
                                       break;
                                   }
                                }

                                saveData();

                            }
                        }
                    });

                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                }
            });
        }
        else
        {
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);

            Call<MoviesResponse> call = apiService.getTopRatedMovies();
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse>call, Response<MoviesResponse> response) {
                    movies = response.body().getResults();
                    Log.d(TAG, "Number of movies received: " + movies.size());

                    textView_det.append(movies.get(position).getTitle()+"\n\n"+movies.get(position).getReleaseDate()
                            +"\n\n"+movies.get(position).getVoteAverage());
                    textView_view.append(movies.get(position).getPlotSynopsis());
                    Picasso.get()
                            .load("http://image.tmdb.org/t/p/w185/"+movies.get(position).getBackdropPath())
                            .into(imageView_pos);
                    getMovieReviews(movies.get(position).getId());
                     getMovieTrailers(movies.get(position).getId());


                    star.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            if (star.isChecked())
                            {
                                //Perform action when you touch on checkbox and it change to selected state



                                IDS.add(movies.get(position).getId());
                                saveData();
                                 final MoviesTable favoriteEntry = new MoviesTable(String.valueOf(movies.get(position).getId()),
                                        movies.get(position).getTitle());
                                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        mDb.myDao().addMovie(favoriteEntry);
                                    }
                                });


                            }
                            else
                            {
                                //Perform action when you touch on checkbox and it change to unselected state

                                for ( int i = 0 ; i < IDS.size() ; i++)
                                {
                                    if (IDS.get(i).equals(movies.get(position).getId())) {
                                        final int finalI = i;
                                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                 mDb.myDao().deleteFavoriteWithId(movies.get(position).getId());
                                            }
                                        });
                                        IDS.remove(i);
                                        break;
                                    }
                                }

                                saveData();
                            }
                        }
                    });


                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                }
            });
        }


    }

    private void getMovieReviews (String id)
    {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getMovieReviews(id);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse>call, Response<MoviesResponse> response) {
                movieStage2 = response.body().getResults();
                for (int i = 0 ; i < movieStage2.size() ; i++)
                textView_review.append(movieStage2.get(i).getContent() + "\n\n");


            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }

    private void getMovieTrailers(String id)
    {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.getMovieTrailers(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody>call, Response<ResponseBody> response) {
                //int statusCode = response.code();
                // recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.item_recyclerview, getApplicationContext()));

                //Toast.makeText(MoviesTrailers.this, " "+response.body().string(), Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jObject = new JSONObject(response.body().string());
                    JSONArray jArrary_ingredients=  jObject.getJSONArray("results");

                    for (int i = 0 ; i<jArrary_ingredients.length() ; i++)
                    {


                        JSONObject object3 = jArrary_ingredients.getJSONObject(i);
                        String comp_id = object3.getString("key");

                        keys.add(comp_id);




                    }
                    recyclerView.setAdapter(new TrailersAdapter(keys, getApplicationContext()));



                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());




            }
        });
    }


    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(IDS);
        editor.putString("task list", json);
        editor.apply();
    }

    private ArrayList<String> loadData() {

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        IDS = gson.fromJson(json, type);
        if (IDS == null) {
            IDS = new ArrayList<>();
        }
       if (IDS.isEmpty())
        IDS.add(" ");

        return IDS;
    }


}
