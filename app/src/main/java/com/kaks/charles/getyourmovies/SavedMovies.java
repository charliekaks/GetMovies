package com.kaks.charles.getyourmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kaks.charles.getyourmovies.adapter.FirebasePopularViewHolder;
import com.kaks.charles.getyourmovies.models.MovieModel;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedMovies extends AppCompatActivity {

    private DatabaseReference mMovieReference;
    private FirebaseRecyclerAdapter mMovieAdapter;

    @Bind(R.id.popular_list) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_popular);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mMovieReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_POPULAR_MOVIES)
                .child(uid);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mMovieAdapter = new FirebaseRecyclerAdapter<MovieModel, FirebasePopularViewHolder>
                (MovieModel.class, R.layout.movie_item, FirebasePopularViewHolder.class,
                        mMovieReference) {

            @Override
            protected void populateViewHolder(FirebasePopularViewHolder viewHolder,
                                              MovieModel model, int position) {
                viewHolder.bindMovies(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMovieAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMovieAdapter.cleanup();
    }
}
