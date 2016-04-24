package com.axolotl.popmovies.interactor;

import android.util.Log;

import com.axolotl.popmovies.retrofit.Review;
import com.axolotl.popmovies.retrofit.Reviews;
import com.axolotl.popmovies.retrofit.TdbMovieApi;
import com.axolotl.popmovies.retrofit.pojo.Video;
import com.axolotl.popmovies.retrofit.pojo.Videos;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by axolotl on 16/4/24.
 */
public class DetailInteratorImpl implements DetailInterator {
    private TdbMovieApi mApi;

    public DetailInteratorImpl(TdbMovieApi mApi) {
        this.mApi = mApi;
    }


    @Override
    public void loadReviews(int movieId, final LoadReviewsListener listener) {
//        mApi.getReviews(movieId).enqueue(new Callback<Reviews>() {
//            @Override
//            public void onResponse(Call<Reviews> call, Response<Reviews> response) {
//                if(response.isSuccessful()){
//                    List<Result> reviews = response.body().getResults();
//                    Log.i("detail", "id:" + response.body().getId()+"");
//                    Log.i("detail", "size:" + response.body().getResults().size()+"");
////                    listener.onSuccess(reviews);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Reviews> call, Throwable t) {
//                listener.onFail();
//            }
//        });
        mApi.getReviews(movieId).enqueue(new Callback<Reviews>() {
            @Override
            public void onResponse(Call<Reviews> call, Response<Reviews> response) {
                if (response.isSuccessful()) {
                    List<Review> reviews = response.body().getResults();
                    Log.i("detail", "id:" + response.body().getId() + "");
                    Log.i("detail", "size:" + reviews.size() + "");
                    listener.onSuccess(reviews);
                }
            }

            @Override
            public void onFailure(Call<Reviews> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadVideos(int movieId, final LoadVideosListener listener) {
        mApi.getVideos(movieId).enqueue(new Callback<Videos>() {
            @Override
            public void onResponse(Call<Videos> call, Response<Videos> response) {
                if (response.isSuccessful()) {
                    List<Video> videos = response.body().getResults();
                    listener.onSuccess(videos);
                }
            }

            @Override
            public void onFailure(Call<Videos> call, Throwable t) {
                listener.onFail();
            }
        });
    }
}
