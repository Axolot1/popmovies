package com.axolotl.popmovies.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.axolotl.popmovies.MyApp;
import com.axolotl.popmovies.R;
import com.axolotl.popmovies.adapter.ReviewAdapter;
import com.axolotl.popmovies.adapter.TrailerAdapter;
import com.axolotl.popmovies.dagger.component.DaggerDetailComponent;
import com.axolotl.popmovies.dagger.module.DetailFraModule;
import com.axolotl.popmovies.presenter.DetailFraPresenter;
import com.axolotl.popmovies.retrofit.Review;
import com.axolotl.popmovies.retrofit.TdbMovieApi;
import com.axolotl.popmovies.retrofit.pojo.Movie;
import com.axolotl.popmovies.retrofit.pojo.Video;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by axolotl on 16/4/19.
 */
public class DetailFragment extends Fragment implements DfView{

    public static final String ARG_MOVIE = "arg_movie";
    private static final String EXTRA_VIDEO = "extra_video";
    private static final String EXTRA_REVIWS = "extra_review";

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

    @Inject
    DetailFraPresenter detailFraPresenter;
    @Inject
    ReviewAdapter reviewAdapter;
    @Inject
    TrailerAdapter trailerAdapter;

    private Movie mMovie;

    public DetailFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();
        mMovie = Parcels.unwrap(getArguments().getParcelable(ARG_MOVIE));
    }

    private void initializeInjector() {
        DaggerDetailComponent.builder()
                .netComponent(MyApp.getNetComponent(getActivity()))
                .detailFraModule(new DetailFraModule(this))
                .build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_layout, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView();
        if(savedInstanceState == null){
            detailFraPresenter.initialize(mMovie.movieId);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null){
            List<Video> videos = Parcels.unwrap(savedInstanceState.getParcelable(EXTRA_VIDEO));
            List<Review> reviews = Parcels.unwrap(savedInstanceState.getParcelable(EXTRA_REVIWS));
            detailFraPresenter.restoreReviews(reviews);
            detailFraPresenter.restoreVideos(videos);
        }
    }

    private void setupView() {
        String porsterUrl = TdbMovieApi.IMAGE_URL + mMovie.getPosterPath();
        ImageLoader.getInstance().displayImage(porsterUrl, ivPortal);
//        Picasso.with(getContext()).load(TdbMovieApi.IMAGE_URL + mMovie.getPosterPath()).into(ivPortal);
        tvTitle.setText(mMovie.getOriginalTitle());
        tvReleaseDate.setText(mMovie.getReleaseDate());
        tvVote.setText(String.format("%s", mMovie.getVoteAverage()));
        tvOverView.setText(mMovie.getOverview());

        rcvReviews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcvReviews.setAdapter(reviewAdapter);

        rcvTrailers.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcvTrailers.setAdapter(trailerAdapter);

        btnFavor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMovie.save();

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        List<Video> videos = detailFraPresenter.getVideos();
        List<Review> reviews = detailFraPresenter.getReviews();
        outState.putParcelable(EXTRA_VIDEO, Parcels.wrap(videos));
        outState.putParcelable(EXTRA_REVIWS, Parcels.wrap(reviews));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setReviews(List<Review> reviews) {
        if(reviews != null){
            reviewAdapter.setData(reviews);
            Log.i("detail", "setup reviews");
        }
    }

    @Override
    public void setVideo(List<Video> videos) {
        if(videos != null){
            trailerAdapter.setData(videos);
        }
    }
}
