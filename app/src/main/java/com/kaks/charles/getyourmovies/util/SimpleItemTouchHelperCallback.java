package com.kaks.charles.getyourmovies.util;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

/**
 * Created by charles on 9/29/17.
 */

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
    //an instance of the ItemTouchHelperAdapter
    private final ItemTouchHelperAdapter mAdapter;

    //constructor for the class which takes in the ItemTouchHelperAdapter as a parameter which defines what happens when the item is touched or dismissed
    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter){
        mAdapter = adapter;
    }



    //  The method below informs the ItemTouchHelperAdapter that drag gestures are enabled.
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }
    //  The method below informs the ItemTouchHelperAdapter that swipe gestures are enabled.
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }
   // informs the ItemTouchHelper which movement directions are supported.
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }
    //  The method below notifies the adapter that an item has moved.This triggers the onItemMove override in our Firebase adapter,
    //  which will eventually handle updating the restaurants ArrayList to reflect the item's new position.

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
        if (source.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        mAdapter.onItemMove(source.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
