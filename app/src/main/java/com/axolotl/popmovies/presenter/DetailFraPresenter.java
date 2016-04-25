package com.axolotl.popmovies.presenter;

import com.axolotl.popmovies.retrofit.Review;
import com.axolotl.popmovies.retrofit.pojo.Movie;
import com.axolotl.popmovies.retrofit.pojo.Video;

import java.util.List;

/**
 * Created by axolotl on 16/4/24.
 */
public interface DetailFraPresenter {
    void initialize(int movieId);
    List<Review> getReviews();
    void restoreReviews(List<Review> reviews);
    List<Video> getVideos();
    void restoreVideos(List<Video> videos);
    void saveMovieDetail(Movie movie);
    void delMovie(int movie);
}
