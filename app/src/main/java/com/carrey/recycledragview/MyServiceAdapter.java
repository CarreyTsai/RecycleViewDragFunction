package com.carrey.recycledragview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 作者： carrey
 * 时间 2017/5/23
 * desc:
 */

public class MyServiceAdapter extends RecyclerView.Adapter<MyServiceAdapter.ViewHolder>
        implements ItemTouchHelperAdapter {


    private List<ServiceEntity> mItems;
    private Context mContext;
    private LayoutInflater mInflater;

    private boolean isEdit;
    private ServiceItemOPLinstener mLinstener;


    public List<ServiceEntity> getItems() {
        if (mItems == null) {
            return new ArrayList<>();
        }
        return mItems;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
        notifyDataSetChanged();
    }

    public MyServiceAdapter(Context context, List<ServiceEntity> t) {

        mContext = context;
        if (context instanceof ServiceItemOPLinstener) {
            mLinstener = (ServiceItemOPLinstener) context;
        }
        mInflater = LayoutInflater.from(context);
        mItems = t;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_service, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ServiceEntity entity = mItems.get(position);
        holder.serviceName.setText(entity.name);
        holder.icon.setImageResource(entity.icon);
        holder.addview.setVisibility(isEdit ? View.VISIBLE : View.GONE);
        holder.addview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLinstener != null) {
                    mLinstener.serviceDel(entity);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mItems == null) {
            return 0;
        }

        return mItems.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition > toPosition) {
            for (int x = fromPosition; x > toPosition; x--) {
                Collections.swap(mItems, x, x - 1);
                notifyItemMoved(x, x - 1);
            }

        } else {
            for (int x = fromPosition; x < toPosition; x++) {
                Collections.swap(mItems, x, x + 1);
                notifyItemMoved(x, x + 1);
            }
        }
    }

    @Override
    public void onItemDismiss(int position) {

    }

    @Override
    public void onItemSelected(RecyclerView.ViewHolder viewHolder, int position) {

    }

    @Override
    public void onItemViewClear(RecyclerView.ViewHolder viewHolder, int position) {

    }

    public void add(ServiceEntity entity) {
        mItems.add(entity);
        notifyItemInserted(getItemCount());

    }

    public void remove(ServiceEntity entity) {
        if (mItems.contains(entity)){
            int pos = mItems.indexOf(entity);
            mItems.remove(entity);
            notifyItemRemoved(pos);
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView serviceName;
        ImageView addview, icon;

        public ViewHolder(View itemView) {
            super(itemView);
            serviceName = (TextView) itemView.findViewById(R.id.service_name);
            icon = (ImageView) itemView.findViewById(R.id.service_icon);
            addview = (ImageView) itemView.findViewById(R.id.iv_func_icon);
            addview.setImageResource(R.mipmap.ic_service_del);
        }

    }
}
