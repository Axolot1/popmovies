package com.axolotl.popmovies.interactor;

import com.axolotl.popmovies.retrofit.pojo.Movie;

import java.util.List;

/**
 * Created by axolotl on 16/4/7.
 */
public interface MainInteractor {
    interface OnLoadMoviesFinishListener{
        void onLoadMovieSuccess(List<Movie> movies);
        void onLoadFail(String error);
    }

    void loadPopularMovies(OnLoadMoviesFinishListener listener);

    void loadTopRatedMovies(OnLoadMoviesFinishListener listener);
}
