package com.axolotl.popmovies.presenter;

import com.axolotl.popmovies.interactor.MainInteractor;
import com.axolotl.popmovies.retrofit.pojo.Movie;
import com.axolotl.popmovies.ui.MainFragmentView;
import com.axolotl.popmovies.utils.DbUtils;

import java.util.List;

/**
 * Created by axolotl on 16/4/7.
 */
//view <--> presenter <-->interactor
public class MainPresenterImpl implements MainFragmentPresenter{

    private List<Movie> mData;
    private MainFragmentView mMainView;
    private MainInteractor mMainIterator;
    private int type;
    private static final int TOP_RATED = 1;
    private static final int MOST_POPULAR = 2;
    private static final int MY_FAVOR = 3;

    public MainPresenterImpl(MainFragmentView mMainView, MainInteractor mMainIterator) {
        this.mMainView = mMainView;
        this.mMainIterator = mMainIterator;
    }



    @Override
    public void initialize() {
        mMainView.showProgress();
        mMainIterator.loadPopularMovies(new LoadMovieListener(MOST_POPULAR));
    }

    @Override
    public void onResume() {
        if(type == MY_FAVOR){
            refreshLocalMovie();
        }
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
        mMainIterator.loadPopularMovies(new LoadMovieListener(MOST_POPULAR));

    }

    @Override
    public void clickMenuTopRated() {
        if(type == TOP_RATED){
            return ;
        }
        mMainIterator.loadTopRatedMovies(new LoadMovieListener(TOP_RATED));
    }

    @Override
    public void clickMenuFavor() {
        if(type == MY_FAVOR){
            return ;
        }
        refreshLocalMovie();
    }

    private void refreshLocalMovie() {
        List<Movie> movies = DbUtils.getLocalMovies();
        if(movies == null || movies.size() == 0){
            mMainView.showMessage("No Favor Movies yet");
            return ;
        }
        type = MY_FAVOR;
        mMainView.setItems(movies);
        mMainView.hideProgress();
    }

    @Override
    public List<Movie> getParcelableData() {
        return mData;
    }

    @Override
    public void restoreParcelableData(List<Movie> data) {
        this.mData = data;
        mMainView.setItems(mData);
        mMainView.hideProgress();
    }


    public class LoadMovieListener implements MainInteractor.OnLoadMoviesFinishListener{

        public int type;

        public LoadMovieListener(int type) {
            this.type = type;
        }

        @Override
        public void onLoadMovieSuccess(List<Movie> movies) {
            if(movies != null && mMainView != null){
                mData = movies;
                mMainView.setItems(movies);
                mMainView.hideProgress();
                MainPresenterImpl.this.type = this.type;
            }
        }

        @Override
        public void onLoadFail(String error) {
            mMainView.showMessage("network problem");
            mMainView.hideProgress();
        }
    }


}
