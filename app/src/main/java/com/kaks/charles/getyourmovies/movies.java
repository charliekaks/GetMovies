package com.kaks.charles.getyourmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.GridView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class movies extends AppCompatActivity {
    @Bind(R.id.movieGrid) GridView mMovieGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String categories = intent.getStringExtra("categories");



    }
}
