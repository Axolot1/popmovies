package com.axolotl.popmovies;

import android.app.Application;
import android.content.Context;

import com.axolotl.popmovies.dagger.component.DaggerNetComponent;
import com.axolotl.popmovies.dagger.component.NetComponent;
import com.axolotl.popmovies.dagger.module.AppModule;

/**
 * Created by axolotl on 16/4/6.
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
