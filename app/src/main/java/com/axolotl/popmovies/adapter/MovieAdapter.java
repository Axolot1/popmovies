package com.axolotl.popmovies.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.axolotl.popmovies.R;
import com.axolotl.popmovies.retrofit.TdbMovieApi;
import com.axolotl.popmovies.retrofit.pojo.Movie;
import com.axolotl.popmovies.ui.MainFragmentView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by axolotl on 16/4/8.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> mMovies;
    Picasso mPicasso;
    MainFragmentView mMainView;

    public MovieAdapter(Picasso picasso, MainFragmentView view) {
        this.mPicasso = picasso;
        this.mMovies = new ArrayList<>();
        this.mMainView = view;
    }

    public void setData(List<Movie> movies){
        this.mMovies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_item, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie m = mMovies.get(position);
        String posUrl = TdbMovieApi.IMAGE_URL + m.getPosterPath();
        ImageLoader.getInstance().displayImage(posUrl, holder.ivPortal);
//        mPicasso.load(posUrl).into(holder.ivPortal);
        holder.ivPortal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainView.onItemClick(m);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mMovies == null){
            return 0;
        }
        return mMovies.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.iv_portal)
        ImageView ivPortal;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    //from http://stackoverflow.com/questions/28531996/android-recyclerview-gridlayoutmanager-column-spacing
    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}
