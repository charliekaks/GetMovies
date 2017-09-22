package com.kaks.charles.getyourmovies;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.widget.GridView;
import android.widget.TextView;

import com.kaks.charles.getyourmovies.adapter.MovieSearchAdapter;
import com.kaks.charles.getyourmovies.adapter.PopularMovieAdapter;
import com.kaks.charles.getyourmovies.models.MovieModel;
import com.kaks.charles.getyourmovies.models.MovieSearch;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Movies extends AppCompatActivity {

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String categories = intent.getStringExtra("categories");
//        mTextView.setText(categories);
        getMovies(categories);

    }
    private  void getMovies(String categories){
        final MovieService movieService = new MovieService();
            movieService.findMovie(categories, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();

                }

                @Override
                public void onResponse(Call call, Response response)  {
                    final ArrayList<MovieSearch> mMovies = movieService.processMoviesSearched(response);
                    Movies.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final RecyclerView MovieSearchAdapter = (RecyclerView) findViewById(R.id.search_list);
                            final LinearLayoutManager searchLayout = new LinearLayoutManager(getApplicationContext());
                            MovieSearchAdapter.setLayoutManager(searchLayout);
                            MovieSearchAdapter movieSearch = new MovieSearchAdapter(getApplicationContext(),mMovies);
                            MovieSearchAdapter.setAdapter(movieSearch);
                            MovieSearchAdapter.setHasFixedSize(true);
                            for (MovieSearch movie :mMovies){
                                Log.d("movie overview",movie.getOverview());
                                Log.d("The original Name", movie.getOriginal_name());
                                Log.d("The movie Title", movie.getTitle());
                                Log.d("The Movie Poster", movie.getPoster_path());
                                Log.d("Release Date",movie.getRelease_date());
                                Log.d("First Release",movie.getFirst_release());
                                Log.d("Popularity",Double.toString(movie.getPopularity()));
                                Log.d("Voter Average",Double.toString(movie.getVote_average()));
                            }
                        }
                    });
                }
            });

    }
}
