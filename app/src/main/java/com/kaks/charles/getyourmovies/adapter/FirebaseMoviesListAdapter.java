package com.kaks.charles.getyourmovies.adapter;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.kaks.charles.getyourmovies.models.MovieModel;
import com.kaks.charles.getyourmovies.util.ItemTouchHelperAdapter;
import com.kaks.charles.getyourmovies.util.OnStartDragListener;

/**
 * Created by charles on 9/29/17.
 */

public class FirebaseMoviesListAdapter extends FirebaseRecyclerAdapter<MovieModel, FirebasePopularViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseMoviesListAdapter(Class<MovieModel> modelClass, int modelLayout, Class<FirebasePopularViewHolder> viewHolderClass, Query ref ,OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
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
    public boolean onItemMove(int fromPostion, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }

}
