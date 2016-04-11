package com.axolotl.popmovies.interactor;

import com.axolotl.popmovies.retrofit.TdbMovieApi;
import com.axolotl.popmovies.retrofit.pojo.Movie;
import com.axolotl.popmovies.retrofit.pojo.PopMovies;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by axolotl on 16/4/7.
 */
public class MainInteractImpl implements MainInteractor {

    TdbMovieApi movieApi;

    public MainInteractImpl(TdbMovieApi movieApi) {
        this.movieApi = movieApi;
    }

    @Override
    public void loadPopularMovies(final OnLoadMoviesFinishListener listener) {
        movieApi.getPopMovies().enqueue(new Callback<PopMovies>() {
            @Override
            public void onResponse(Call<PopMovies> call, Response<PopMovies> response) {
                if(response.isSuccessful()){
                    listener.onLoadMovieSuccess(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<PopMovies> call, Throwable t) {
                listener.onLoadFail(t.getMessage());
            }
        });
    }

    @Override
    public void loadTopRatedMovies(final OnLoadMoviesFinishListener listener) {
        movieApi.getTopMoves().enqueue(new Callback<PopMovies>() {
            @Override
            public void onResponse(Call<PopMovies> call, Response<PopMovies> response) {
                if(response.isSuccessful()){
                    listener.onLoadMovieSuccess(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<PopMovies> call, Throwable t) {
                listener.onLoadFail(t.getMessage());
            }
        });
    }



}
