package com.kaks.charles.getyourmovies;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kaks.charles.getyourmovies.adapter.MovieSearchPagerAdapter;
import com.kaks.charles.getyourmovies.models.MovieSearch;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieSearchDetail extends AppCompatActivity {
    @Bind(R.id.viewPager2)
    ViewPager mViewPager2;
    private MovieSearchPagerAdapter adapterViewPager2;
    ArrayList<MovieSearch> mMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search_detail);

        ButterKnife.bind(this);

        mMovies = Parcels.unwrap(getIntent().getParcelableExtra("movies"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager2 = new MovieSearchPagerAdapter(getSupportFragmentManager(), mMovies);
        mViewPager2.setAdapter(adapterViewPager2);
        mViewPager2.setCurrentItem(startingPosition);
    }
}
