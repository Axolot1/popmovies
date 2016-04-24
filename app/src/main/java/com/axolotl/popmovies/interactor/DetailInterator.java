package com.axolotl.popmovies.interactor;

import com.axolotl.popmovies.retrofit.Review;
import com.axolotl.popmovies.retrofit.pojo.Video;

import java.util.List;

/**
 * Created by axolotl on 16/4/24.
 */
public interface DetailInterator {
    interface LoadReviewsListener{
        void onSuccess(List<Review> reviews);
        void onFail();
    }

    interface LoadVideosListener{
        void onSuccess(List<Video> videos);
        void onFail();
    }

    void loadReviews(int movieId, LoadReviewsListener listener);
    void loadVideos(int movieId, LoadVideosListener listener);

}
