package com.kaks.charles.getyourmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kaks.charles.getyourmovies.adapter.FirebaseMoviesListAdapter;
import com.kaks.charles.getyourmovies.adapter.FirebasePopularViewHolder;
import com.kaks.charles.getyourmovies.models.MovieModel;
import com.kaks.charles.getyourmovies.util.OnStartDragListener;
import com.kaks.charles.getyourmovies.util.SimpleItemTouchHelperCallback;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedMovies extends AppCompatActivity implements OnStartDragListener {

    private DatabaseReference mMovieReference;
    private FirebaseMoviesListAdapter mMovieAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.popular_list) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_popular);
        ButterKnife.bind(this);
        
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mMovieReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_POPULAR_MOVIES)
                .child(uid);
        mMovieAdapter =  new FirebaseMoviesListAdapter(MovieModel.class,R.layout.movie_item_drag,FirebasePopularViewHolder.class,mMovieReference, this,this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMovieAdapter);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mMovieAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }


    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMovieAdapter.cleanup();
    }
}
