package com.axolotl.popmovies.dagger.module;

import com.axolotl.popmovies.adapter.ReviewAdapter;
import com.axolotl.popmovies.adapter.TrailerAdapter;
import com.axolotl.popmovies.interactor.DetailInterator;
import com.axolotl.popmovies.interactor.DetailInteratorImpl;
import com.axolotl.popmovies.presenter.DetailFraPresenter;
import com.axolotl.popmovies.presenter.DetailFragPresenterImpl;
import com.axolotl.popmovies.retrofit.TdbMovieApi;
import com.axolotl.popmovies.ui.DfView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by axolotl on 16/4/24.
 */
@Module
public class DetailFraModule {
    DfView mView;

    public DetailFraModule(DfView mView) {
        this.mView = mView;
    }

    @Provides
    public DetailFraPresenter providesPresenter(DetailInterator interator){
        return new DetailFragPresenterImpl(mView, interator);
    }

    @Provides
    public DetailInterator providesInterator(TdbMovieApi api){
        return new DetailInteratorImpl(api);
    }

    @Provides
    public ReviewAdapter providesReviewAdapter(){
        return new ReviewAdapter();
    }

    @Provides
    public TrailerAdapter providesTrailerAdapter(){
        return new TrailerAdapter(mView);
    }
}
