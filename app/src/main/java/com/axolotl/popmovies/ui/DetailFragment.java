package com.axolotl.popmovies.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
import com.axolotl.popmovies.utils.DbUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by axolotl on 16/4/19.
 */
public class DetailFragment extends Fragment implements DfView {

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
    @Bind(R.id.sv_detail)
    ScrollView svDetail;
    @Bind(R.id.rl_layout)
    RelativeLayout rlLayout;

    private Movie mMovie;
    private boolean isCheck;

    public interface CallBack{
        void onDelMovie();
    }

    public DetailFragment() {
        setHasOptionsMenu(true);
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();
        Bundle argument = getArguments();
        if (argument != null) {
            mMovie = Parcels.unwrap(getArguments().getParcelable(ARG_MOVIE));
        }
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
        //initially show empty screen
        if (mMovie == null) {
            svDetail.setVisibility(View.GONE);
            return;
        } else {
            svDetail.setVisibility(View.VISIBLE);
        }

        setupView();
        if (savedInstanceState == null) {
            if (mMovie.favor) {
                mMovie = DbUtils.findMovie(mMovie.movieId);
                List<Video> videos = DbUtils.findVideos(mMovie);
                List<Review> reviews = DbUtils.findReviews(mMovie);
                detailFraPresenter.restoreVideos(videos);
                detailFraPresenter.restoreReviews(reviews);
            } else {
                detailFraPresenter.initialize(mMovie.movieId);
            }
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
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

        setupRecyclerView();

        setupToggleBtn();

        btnFavor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    detailFraPresenter.saveMovieDetail(mMovie);
                } else {
                    detailFraPresenter.delMovie(mMovie.movieId);
                }
            }
        });
    }

    private void setupToggleBtn() {
        Movie nm = DbUtils.findMovie(mMovie.movieId);
        if (nm == null) {
            btnFavor.setChecked(false);
        } else {
            btnFavor.setChecked(true);
            isCheck = true;
        }
    }

    private void setupRecyclerView() {
        rcvTrailers.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcvTrailers.setAdapter(trailerAdapter);
        rcvTrailers.setNestedScrollingEnabled(true);

        rcvReviews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcvReviews.setAdapter(reviewAdapter);
        rcvReviews.setNestedScrollingEnabled(true);


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
    public void onResume() {
        super.onResume();
        rlLayout.setFocusable(true);
        rlLayout.setFocusableInTouchMode(true);
        rlLayout.requestFocus();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void setReviews(List<Review> reviews) {
        if (reviews != null) {
            reviewAdapter.setData(reviews);
        }
    }

    @Override
    public void setVideo(List<Video> videos) {
        if (videos != null) {
            trailerAdapter.setData(videos);
        }
    }

    @Override
    public void clickTrailer(Video video) {
        String url = TdbMovieApi.YOUTUBE_URL + video.getKey();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void delMovieSuccess() {
        Activity activity = getActivity();
        if(activity instanceof MainActivity) {
            ((MainActivity) getActivity()).onDelMovie();
        }
    }
}
