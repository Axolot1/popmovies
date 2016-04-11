package com.axolotl.popmovies.presenter;

/**
 * Created by axolotl on 16/4/11.
 */
public interface Presenter {

    void initialize();

    void onResume();

    void onPause();

    void onDestroy();


}
