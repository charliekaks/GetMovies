package com.kaks.charles.getyourmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by charles on 9/8/17.
 */

public class seriesAdapter extends RecyclerView.Adapter<seriesAdapter.ViewHolder>  {
    private final Context mContext;
    private final LayoutInflater layout;
    private List<String> mTitles = new ArrayList<>(Arrays.asList("the Jungle Book", "The Happening"));
    private Integer[] mpics = {R.drawable.jungle,R.drawable.cut};


    public seriesAdapter(Context mContext, List<String>mTitles,Integer[] mpics) {
        this.mContext = mContext;
        layout = LayoutInflater.from(mContext);
        this.mTitles = mTitles;
        this.mpics = mpics;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View tvItem = layout.inflate(R.layout.tv_list_item, parent, false);
        return new ViewHolder(tvItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String pics = mTitles.get(position);
        holder.seriesImage.setImageResource(mpics[position]);
        holder.seriesTitle.setText(mTitles.get(position));
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView seriesTitle;
        public final ImageView seriesImage;

        public ViewHolder(View tvItem) {
            super(tvItem);
            seriesTitle = (TextView) tvItem.findViewById(R.id.tvTitle);
            seriesImage = (ImageView) tvItem.findViewById(R.id.tvImage);
        }
    }

}
