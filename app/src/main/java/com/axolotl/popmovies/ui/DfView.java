package com.axolotl.popmovies.ui;

import android.view.View;

import com.axolotl.popmovies.retrofit.Review;
import com.axolotl.popmovies.retrofit.pojo.Video;

import java.util.List;

/**
 * Created by axolotl on 16/4/20.
 */
public interface DfView {
    void setReviews(List<Review> reviews);
    void setVideo(List<Video> videos);
    void clickTrailer(Video video);
}
