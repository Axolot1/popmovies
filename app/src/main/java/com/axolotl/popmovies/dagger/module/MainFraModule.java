package com.axolotl.popmovies.dagger.module;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

import com.axolotl.popmovies.MyApp;
import com.axolotl.popmovies.adapter.MovieAdapter;
import com.axolotl.popmovies.interactor.MainInteractImpl;
import com.axolotl.popmovies.interactor.MainInteractor;
import com.axolotl.popmovies.presenter.MainFragmentPresenter;
import com.axolotl.popmovies.presenter.MainPresenterImpl;
import com.axolotl.popmovies.retrofit.TdbMovieApi;
import com.axolotl.popmovies.ui.MainFragmentView;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by axolotl on 16/4/8.
 */
@Module
public class MainFraModule {
    MainFragmentView view;

    public MainFraModule(MainFragmentView view) {
        this.view = view;
    }

    @Provides
    public MainFragmentView provideMainView(){
        return view;
    }

    @Provides
    public MainFragmentPresenter providePresenter(MainFragmentView view, MainInteractor interactor){
        return new MainPresenterImpl(view, interactor);
    }

    @Provides
    MainInteractor provideInteractor(TdbMovieApi api){
        return new MainInteractImpl(api);
    }

    @Provides
    MovieAdapter provideMovieAdapter(Picasso picasso, MainFragmentView view){
        return new MovieAdapter(picasso, view);
    }


}
