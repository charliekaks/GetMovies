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

public class FirebasePopularViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    public ImageView mMovieImageView;

    View mView;
    Context mContext;
    public FirebasePopularViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindMovies(MovieModel movies) {
        mMovieImageView = (ImageView) mView.findViewById(R.id.image_url);
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


    @Override
    public void onClick(View view) {
        final ArrayList<MovieModel> popular = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_POPULAR_MOVIES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    popular.add(snapshot.getValue(MovieModel.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, MovieDetails.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("popular", Parcels.wrap(popular));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
