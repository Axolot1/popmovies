package com.axolotl.popmovies.retrofit;

import com.axolotl.popmovies.BuildConfig;
import com.axolotl.popmovies.retrofit.pojo.PopMovies;
import com.axolotl.popmovies.retrofit.pojo.Reviews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by axolotl on 16/4/7.
 */
public interface TdbMovieApi {
    String BASE_URL = "http://api.themoviedb.org/3/";
    String IMAGE_URL = "http://image.tmdb.org/t/p/w185/";
    String YOUTUBE_URL = "https://www.youtube.com/watch?v=";

    String API_KEY = BuildConfig.MOVIE_DB_API_KEY;

    @GET("movie/popular?api_key=" + API_KEY)
    Call<PopMovies> getPopMovies();

    @GET("movie/top_rated?api_key=" + API_KEY)
    Call<PopMovies> getTopMoves();

    @GET("/movie/{id}/reviews?api_key=" + API_KEY)
    Call<Reviews> getReviews(@Path("id") int movieId);

    @GET("/movie/{id}/videos?api_key=" + API_KEY)
    Call<Reviews> getVideos(@Path("id") int movieId);



}
