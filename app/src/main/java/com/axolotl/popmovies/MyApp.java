package com.axolotl.popmovies;

import android.app.Application;
import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.axolotl.popmovies.dagger.component.DaggerNetComponent;
import com.axolotl.popmovies.dagger.component.NetComponent;
import com.axolotl.popmovies.dagger.module.AppModule;
import com.facebook.stetho.Stetho;

/**
 * project base on
 * https://www.future-processing.pl/blog/dependency-injection-with-dagger-2/
 * https://github.com/antoniolg/androidmvp
 */
public class MyApp extends com.activeandroid.app.Application {
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        Stetho.initializeWithDefaults(this);


    }

    private void initializeInjector() {
        mNetComponent = DaggerNetComponent.builder().appModule(new AppModule(this)).build();
    }

    public static NetComponent getNetComponent(Context context) {
        return ((MyApp)context.getApplicationContext()).mNetComponent;
    }
}
