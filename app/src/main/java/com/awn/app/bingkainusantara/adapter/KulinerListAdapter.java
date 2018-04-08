package com.awn.app.bingkainusantara.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.awn.app.bingkainusantara.data.KulinerItems;
import com.awn.app.bingkainusantara.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by adewijanugraha on 31/03/18.
 */

public class KulinerListAdapter extends RecyclerView.Adapter<KulinerListAdapter.ListViewHolder> {
    private ArrayList<KulinerItems> listData;
    private Context context;

    public KulinerListAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<KulinerItems> getListData() {
        return listData;
    }

    public void setListData(ArrayList<KulinerItems> listData) {
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
        KulinerItems data = getListData().get(position);

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
        return getListData().size();
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
