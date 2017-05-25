package com.carrey.recycledragview;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * class:  SimpleItemTouchHelperCallback
 * auth:  carrey
 * date: 16-11-30.
 * desc:
 */

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {


    private boolean startMove = true;
    private final ItemTouchHelperAdapter mAdapter;

    public void setStartMove(boolean startMove) {
        this.startMove = startMove;
    }

    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        mAdapter = adapter;
        if (mAdapter == null) {
            throw new RuntimeException("ItemTouchHelperAdapter is null...");
        }
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0;
        if (startMove) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }
        int swipeFlags = 0;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());

    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            mAdapter.onItemSelected(viewHolder, viewHolder.getAdapterPosition());
        }
        super.onSelectedChanged(viewHolder, actionState);

    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        mAdapter.onItemViewClear(viewHolder, viewHolder.getAdapterPosition());
    }
}
