package com.axolotl.popmovies.dagger.component;

import com.axolotl.popmovies.dagger.module.NetModule;
import com.axolotl.popmovies.dagger.module.AppModule;
import com.axolotl.popmovies.interactor.MainInteractor;
import com.axolotl.popmovies.retrofit.TdbMovieApi;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by axolotl on 16/4/6.
 */
@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
    TdbMovieApi getMovieApi(); //expose to downstream
}
