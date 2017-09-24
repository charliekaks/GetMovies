package com.kaks.charles.getyourmovies;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kaks.charles.getyourmovies.adapter.MoviePagerAdapter;
import com.kaks.charles.getyourmovies.models.MovieModel;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieDetails extends AppCompatActivity {
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    private MoviePagerAdapter adapterViewPager;
    ArrayList<MovieModel> mMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ButterKnife.bind(this);

        mMovies = Parcels.unwrap(getIntent().getParcelableExtra("movies"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new MoviePagerAdapter(getSupportFragmentManager(), mMovies);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
