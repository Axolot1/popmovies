package com.axolotl.popmovies.dagger.module;

import android.app.Application;

import com.axolotl.popmovies.MyApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by axolotl on 16/4/6.
 */
@Module
public class AppModule {

    MyApp mApplication;

    public AppModule(MyApp application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }
}
