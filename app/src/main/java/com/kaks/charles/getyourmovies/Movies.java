package com.kaks.charles.getyourmovies;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.GridView;
import android.widget.TextView;

import com.kaks.charles.getyourmovies.models.MovieModel;

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
        mTextView.setText(categories);
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
                    final ArrayList<MovieModel> mMovies = movieService.processMovies(response);
                    Movies.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (MovieModel movie :mMovies){
                                Log.d("movie overview",movie.getOverview());
                                Log.d("The original Title", movie.getOriginal_title());
                                Log.d("The movie Title", movie.getTitle());
                                Log.d("The Movie Poster", movie.getPoster_path());
                                Log.d("Release Date",movie.getRelease_date());
                                Log.d("Popularity",Double.toString(movie.getPopularity()));
                                Log.d("Voter Average",Double.toString(movie.getVote_average()));
                            }
                        }
                    });
                }
            });

    }
}
