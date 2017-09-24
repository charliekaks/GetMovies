package com.kaks.charles.getyourmovies.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kaks.charles.getyourmovies.PopularFragment;
import com.kaks.charles.getyourmovies.models.MovieModel;

import java.util.ArrayList;

/**
 * Created by charles on 9/15/17.
 */

public class MoviePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<MovieModel> mMovies;

    public MoviePagerAdapter(FragmentManager fm, ArrayList<MovieModel> movies) {
        super(fm);
        mMovies = movies;
    }

    @Override
    public Fragment getItem(int position) {
        return PopularFragment.newInstance(mMovies.get(position));
    }

    @Override
    public int getCount() {
        return mMovies.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mMovies.get(position).getTitle();
    }
}
