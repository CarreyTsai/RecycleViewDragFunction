package com.carrey.recycledragview;

import android.support.v7.widget.RecyclerView;

/**
 * class:  ItemTouchHelperAdapter
 * auth:  carrey
 * date: 16-11-30.
 * desc:
 */

public interface ItemTouchHelperAdapter {

    /**
     * 移动交换
     */
    void onItemMove(int fromPosition, int toPosition);

    /**
     * 滑动删除
     */
    void onItemDismiss(int position);

    //item被选中
    void onItemSelected(RecyclerView.ViewHolder viewHolder, int position);

    //item被释放
    void onItemViewClear(RecyclerView.ViewHolder viewHolder, int position);
}
