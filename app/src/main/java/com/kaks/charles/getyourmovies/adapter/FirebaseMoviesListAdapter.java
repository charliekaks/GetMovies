package com.kaks.charles.getyourmovies.adapter;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.kaks.charles.getyourmovies.models.MovieModel;
import com.kaks.charles.getyourmovies.util.ItemTouchHelperAdapter;
import com.kaks.charles.getyourmovies.util.OnStartDragListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by charles on 9/29/17.
 */

public class FirebaseMoviesListAdapter extends FirebaseRecyclerAdapter<MovieModel, FirebasePopularViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<MovieModel> mMovies = new ArrayList<>();

    public FirebaseMoviesListAdapter(Class<MovieModel> modelClass, int modelLayout, Class<FirebasePopularViewHolder> viewHolderClass, Query ref ,OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mMovies.add(dataSnapshot.getValue(MovieModel.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    protected void populateViewHolder(final FirebasePopularViewHolder viewHolder, MovieModel model, int position) {
        viewHolder.bindMovies(model);
        viewHolder.mMovieImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mMovies,fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mMovies.remove(position);
        getRef(position).removeValue();
    }
    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
    private void setIndexInFirebase(){
        for (MovieModel movies : mMovies) {
            int index = mMovies.indexOf(movies);
            DatabaseReference ref = getRef(index);
            movies.setIndex(Integer.toString(index));
            ref.setValue(movies);
        }
    }


}
