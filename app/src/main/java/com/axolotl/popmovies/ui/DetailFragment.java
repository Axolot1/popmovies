package com.axolotl.popmovies.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.axolotl.popmovies.R;
import com.axolotl.popmovies.retrofit.TdbMovieApi;
import com.axolotl.popmovies.retrofit.pojo.Movie;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by axolotl on 16/4/19.
 */
public class DetailFragment extends Fragment {

    public static final String ARG_MOVIE = "arg_movie";

    @Bind(R.id.iv_portal)
    ImageView ivPortal;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_release_date)
    TextView tvReleaseDate;
    @Bind(R.id.tv_vote)
    TextView tvVote;
    @Bind(R.id.btn_favor)
    ToggleButton btnFavor;
    @Bind(R.id.rcv_trailers)
    RecyclerView rcvTrailers;
    @Bind(R.id.rcv_reviews)
    RecyclerView rcvReviews;
    @Bind(R.id.tv_overView)
    TextView tvOverView;

    private Movie mMovie;

    public DetailFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovie = Parcels.unwrap(getArguments().getParcelable(ARG_MOVIE));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_layout, container, false);
        ButterKnife.bind(this, v);
        String porsterUrl = TdbMovieApi.IMAGE_URL + mMovie.getPosterPath();
        ImageLoader.getInstance().displayImage(porsterUrl, ivPortal);
//        Picasso.with(getContext()).load(TdbMovieApi.IMAGE_URL + mMovie.getPosterPath()).into(ivPortal);
        tvTitle.setText(mMovie.getOriginalTitle());
        tvReleaseDate.setText(mMovie.getReleaseDate());
        tvVote.setText(String.format("%s", mMovie.getVoteAverage()));
        tvOverView.setText(mMovie.getOverview());
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
