package com.axolotl.popmovies.dagger.component;

import com.axolotl.popmovies.dagger.ActivityScope;
import com.axolotl.popmovies.dagger.module.MainFraModule;
import com.axolotl.popmovies.ui.MainFragment;

import dagger.Component;

/**
 * Created by axolotl on 16/4/10.
 */
@ActivityScope
@Component(dependencies = NetComponent.class, modules = MainFraModule.class)
public interface MainComponent {
    void inject(MainFragment mainFragment);
}
