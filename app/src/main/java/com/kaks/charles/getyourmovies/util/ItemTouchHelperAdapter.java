package com.kaks.charles.getyourmovies.util;

/**
 * Created by charles on 9/29/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPostion, int toPosition);
    void onItemDismiss(int position);
}
