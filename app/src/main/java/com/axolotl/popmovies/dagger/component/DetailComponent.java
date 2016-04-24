package com.axolotl.popmovies.dagger.component;

import com.axolotl.popmovies.dagger.ActivityScope;
import com.axolotl.popmovies.dagger.module.DetailFraModule;
import com.axolotl.popmovies.ui.DetailFragment;

import dagger.Component;

/**
 * Created by axolotl on 16/4/24.
 */
@ActivityScope
@Component(dependencies = NetComponent.class, modules = DetailFraModule.class)
public interface DetailComponent {
    void inject(DetailFragment  detailFragment);
}
