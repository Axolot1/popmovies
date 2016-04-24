package com.axolotl.popmovies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.axolotl.popmovies.R;
import com.axolotl.popmovies.retrofit.pojo.Video;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by axolotl on 16/4/24.
 */
public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {

    private List<Video> mData;

    public TrailerAdapter() {
        mData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Video video = mData.get(position);
        holder.tvTrailer.setText(String.format("Trailer %d", position + 1));
    }

    public void setData(List<Video> videos){
        if(videos != null){
            mData = videos;
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tv_trailer)
        TextView tvTrailer;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}