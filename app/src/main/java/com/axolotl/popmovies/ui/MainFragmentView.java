package com.axolotl.popmovies.ui;

import com.axolotl.popmovies.retrofit.pojo.Movie;

import java.util.List;

/**
 * Created by axolotl on 16/4/7.
 */
public interface MainFragmentView {
    void showProgress();

    void hideProgress();

    void setItems(List<Movie> items);

    void showMessage(String message);
}
