package com.axolotl.popmovies;

import android.app.Application;
import android.content.Context;

import com.axolotl.popmovies.dagger.component.DaggerNetComponent;
import com.axolotl.popmovies.dagger.component.NetComponent;
import com.axolotl.popmovies.dagger.module.AppModule;

/**
 * project base on
 * https://www.future-processing.pl/blog/dependency-injection-with-dagger-2/
 * https://github.com/antoniolg/androidmvp
 */
public class MyApp extends Application {
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();

    }

    private void initializeInjector() {
        mNetComponent = DaggerNetComponent.builder().appModule(new AppModule(this)).build();
    }

    public static NetComponent getNetComponent(Context context) {
        return ((MyApp)context.getApplicationContext()).mNetComponent;
    }
}
