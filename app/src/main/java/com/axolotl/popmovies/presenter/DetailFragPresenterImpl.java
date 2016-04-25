package com.axolotl.popmovies.presenter;

import com.axolotl.popmovies.interactor.DetailInterator;
import com.axolotl.popmovies.retrofit.Review;
import com.axolotl.popmovies.retrofit.pojo.Movie;
import com.axolotl.popmovies.retrofit.pojo.Video;
import com.axolotl.popmovies.ui.DfView;
import com.axolotl.popmovies.utils.DbUtils;

import java.util.List;

/**
 * Created by axolotl on 16/4/24.
 */
public class DetailFragPresenterImpl implements DetailFraPresenter {

    private DfView mDfView;
    private DetailInterator mInterator;
    private List<Review> mReviews;
    private List<Video> mVideos;

    public DetailFragPresenterImpl(DfView mDfView, DetailInterator mInterator) {
        this.mDfView = mDfView;
        this.mInterator = mInterator;
    }

    @Override
    public void initialize(int movieId) {
        mInterator.loadReviews(movieId, new DetailInterator.LoadReviewsListener() {
            @Override
            public void onSuccess(List<Review> reviews) {
                mReviews = reviews;
                mDfView.setReviews(mReviews);
            }

            @Override
            public void onFail() {

            }
        });
        mInterator.loadVideos(movieId, new DetailInterator.LoadVideosListener() {
            @Override
            public void onSuccess(List<Video> videos) {
                mVideos = videos;
                mDfView.setVideo(mVideos);
            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    public List<Review> getReviews() {
        return mReviews;
    }

    @Override
    public void restoreReviews(List<Review> reviews) {
        this.mReviews = reviews;
        mDfView.setReviews(mReviews);
    }

    @Override
    public List<Video> getVideos() {
        return mVideos;
    }

    @Override
    public void restoreVideos(List<Video> videos) {
        this.mVideos = videos;
        mDfView.setVideo(videos);
    }

    @Override
    public void saveMovieDetail(Movie movie) {
        DbUtils.saveMovie(movie, mVideos, mReviews);
    }

    @Override
    public void delMovie(int movieId) {
        DbUtils.delMovie(movieId);
    }


}
