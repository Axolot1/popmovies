package com.axolotl.popmovies;

import android.content.Context;

import com.axolotl.popmovies.dagger.component.DaggerNetComponent;
import com.axolotl.popmovies.dagger.component.NetComponent;
import com.axolotl.popmovies.dagger.module.AppModule;
import com.facebook.stetho.Stetho;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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
        initialUIL();

    }

    private void initialUIL() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);
    }

    private void initializeInjector() {
        mNetComponent = DaggerNetComponent.builder().appModule(new AppModule(this)).build();
    }

    public static NetComponent getNetComponent(Context context) {
        return ((MyApp) context.getApplicationContext()).mNetComponent;
    }
}
