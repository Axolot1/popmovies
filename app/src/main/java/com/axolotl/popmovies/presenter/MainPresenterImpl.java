package com.axolotl.popmovies.presenter;

import com.axolotl.popmovies.interactor.MainInteractor;
import com.axolotl.popmovies.retrofit.pojo.Movie;
import com.axolotl.popmovies.ui.MainFragmentView;

import java.util.List;

/**
 * Created by axolotl on 16/4/7.
 */
//view <--> presenter <-->interactor
public class MainPresenterImpl implements MainFragmentPresenter, MainInteractor.OnLoadMoviesFinishListener{

    MainFragmentView mMainView;
    MainInteractor mMainIterator;
    private int type;
    private static final int TOP_RATED = 1;
    private static final int MOST_POPULAR = 2;

    public MainPresenterImpl(MainFragmentView mMainView, MainInteractor mMainIterator) {
        this.mMainView = mMainView;
        this.mMainIterator = mMainIterator;
    }



    @Override
    public void initialize() {
        mMainView.showProgress();
        mMainIterator.loadPopularMovies(this);
        type = MOST_POPULAR;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }



    @Override
    public void clickMenuPopular() {
        if (type == MOST_POPULAR){
            return ;
        }
        type = MOST_POPULAR;
        mMainIterator.loadPopularMovies(this);

    }

    @Override
    public void clickMenuTopRated() {
        if(type == TOP_RATED){
            return ;
        }
        type = TOP_RATED;
        mMainIterator.loadTopRatedMovies(this);
    }


    //call back when interactor successful load data
    @Override
    public void onLoadMovieSuccess(List<Movie> movies) {
        if(movies != null && mMainView != null){
            mMainView.setItems(movies);
            mMainView.hideProgress();
        }
    }

    //call back when interactor fail to load data
    @Override
    public void onLoadFail(String error) {
        if(mMainView != null) {
            mMainView.showMessage(error);
        }
    }
}
