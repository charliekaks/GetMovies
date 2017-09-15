package com.kaks.charles.getyourmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.GridView;
import android.widget.TextView;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class movies extends AppCompatActivity {
    @Bind(R.id.movieGrid) GridView mMovieGrid;
    @Bind(R.id.movieGenre)
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
                public void onResponse(Call call, Response response) throws IOException {
                    try{
                        String jsonData = response.body().string();
                        Log.v("The JSON data", jsonData);

                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            });
    }
}
