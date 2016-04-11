package com.axolotl.popmovies.dagger.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.axolotl.popmovies.interactor.MainInteractImpl;
import com.axolotl.popmovies.interactor.MainInteractor;
import com.axolotl.popmovies.retrofit.TdbMovieApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by axolotl on 16/4/6.
 */
@Module
public class NetModule {

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }


    @Provides
    @Singleton
    TdbMovieApi provideMovieApi(){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(TdbMovieApi.BASE_URL)
                .build().create(TdbMovieApi.class);
    }

}

