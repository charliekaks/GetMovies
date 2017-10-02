package com.kaks.charles.getyourmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kaks.charles.getyourmovies.Constants;
import com.kaks.charles.getyourmovies.MovieDetails;
import com.kaks.charles.getyourmovies.R;
import com.kaks.charles.getyourmovies.models.MovieModel;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by charles on 9/22/17.
 */

public class FirebasePopularViewHolder extends RecyclerView.ViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    public ImageView mMovieImageView;
    public ImageView mMovieImageViewRecoder;
    View mView;
    Context mContext;
    public FirebasePopularViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindMovies(MovieModel movies) {
        mMovieImageView = (ImageView) mView.findViewById(R.id.image_url);
        mMovieImageViewRecoder = (ImageView) mView.findViewById(R.id.recoder_id);
        TextView title = (TextView) mView.findViewById(R.id.title);
        TextView voterAverage = (TextView) mView.findViewById(R.id.v_average);
        ImageView image = (ImageView) mView.findViewById(R.id.image_url);

        title.setText(movies.getTitle());
        // overview.setText(movies.getOverview());
        voterAverage.setText("Vote" + " "+ String.valueOf(movies.getVote_average())+"/10");
        // release.setText("Movie Release Date \n"+movies.getRelease_date());
        // popularity.setText("Movie Popularity"+" "+String.valueOf(movies.getPopularity()));
        Picasso.with(mContext).load(movies.getPoster_path()).into(mMovieImageView);

    }

}
