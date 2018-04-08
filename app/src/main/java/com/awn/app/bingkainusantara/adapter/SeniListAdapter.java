package com.awn.app.bingkainusantara.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.awn.app.bingkainusantara.R;
import com.awn.app.bingkainusantara.data.SeniItems;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by adewijanugraha on 31/03/18.
 */

public class SeniListAdapter extends RecyclerView.Adapter<SeniListAdapter.ListViewHolder> {
    private ArrayList<SeniItems> listData;
    private Context context;

    public SeniListAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<SeniItems> getListData() {
        return listData;
    }

    public void setListData(ArrayList<SeniItems> listData) {
        this.listData = listData;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ListViewHolder viewHolder = new ListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        SeniItems data = getListData().get(position);

        Glide.with(context)
                .load(data.getPicture())
                .placeholder(new ColorDrawable(Color.GRAY))
                .error(new ColorDrawable(Color.RED))
                .fallback(new ColorDrawable(Color.BLUE))
                .centerCrop()
                .into(holder.ivPict);
        holder.tvTitle.setText(data.getTitle());
        holder.tvDesc.setText(data.getDescription());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPict;
        TextView tvTitle, tvDesc;

        public ListViewHolder(View itemView) {
            super(itemView);
            ivPict = (ImageView) itemView.findViewById(R.id.iv_pict);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_desc);
        }
    }
}
