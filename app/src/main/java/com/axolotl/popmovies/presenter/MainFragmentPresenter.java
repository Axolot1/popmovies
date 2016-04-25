package com.axolotl.popmovies.presenter;

import com.axolotl.popmovies.retrofit.pojo.Movie;

import java.util.List;

/**
 * Created by axolotl on 16/4/7.
 */
public interface MainFragmentPresenter extends Presenter{
    void clickMenuPopular();
    void clickMenuTopRated();
    List<Movie> getParcelableData();
    void restoreParcelableData(List<Movie> data);
    void clickMenuFavor();
    void refreshLocalMovie();
}
