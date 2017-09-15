package com.kaks.charles.getyourmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.kaks.charles.getyourmovies.adapter.PopularMovieAdapter;
import com.kaks.charles.getyourmovies.models.MovieModel;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Popular extends AppCompatActivity {
    public ArrayList<MovieModel> popsMovie= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);
        final RecyclerView PopularMovieAdapter = (RecyclerView) findViewById(R.id.popular_list);
        final LinearLayoutManager popularLayout = new LinearLayoutManager(this);
        PopularMovieAdapter.setLayoutManager(popularLayout);
        PopularMovieAdapter moviePops = new PopularMovieAdapter(this,popsMovie);
        PopularMovieAdapter.setAdapter(moviePops);

        getPopular();

    }

    public void getPopular(){
        final MovieService movieService = new MovieService();
        movieService.findPopularMovies( new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

            }

            @Override
            public void onResponse(Call call, Response response)  {
                final ArrayList<MovieModel> mMovies = movieService.processMovies(response);
                Popular.this.runOnUiThread(new Runnable() {
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

