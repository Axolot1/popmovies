package com.axolotl.popmovies.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.axolotl.popmovies.MyApp;
import com.axolotl.popmovies.R;
import com.axolotl.popmovies.adapter.MovieAdapter;
import com.axolotl.popmovies.dagger.component.DaggerMainComponent;
import com.axolotl.popmovies.dagger.module.MainFraModule;
import com.axolotl.popmovies.presenter.MainFragmentPresenter;
import com.axolotl.popmovies.retrofit.pojo.Movie;
import com.axolotl.popmovies.ui.custom.AutoFitRecyclerView;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment implements MainFragmentView {

    public static final String EXTRA_MOVIES = "extra_movies";

    @Bind(R.id.pb_loading)
    ContentLoadingProgressBar pbLoading;
    @Bind(R.id.recyclerView_movies)
    AutoFitRecyclerView recyclerViewMovies;

    @Inject
    MovieAdapter mAdapter;

    @Inject
    MainFragmentPresenter mPresenter;

    ConnectivityManager mConnectivityManager;

    private boolean twoPaneLayout;


    public interface CallBack {
        void onItemClick(Movie movie);
    }

    public MainFragment() {
    }


    private void initializeInjector() {
        DaggerMainComponent.builder()
                .netComponent(MyApp.getNetComponent(getActivity()))
                .mainFraModule(new MainFraModule(this))
                .build().inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        initializeInjector();
        mConnectivityManager = (ConnectivityManager) getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        if (savedInstanceState == null) {
            mPresenter.initialize();
        }
    }

    private void setupRecyclerView() {
//        recyclerViewMovies.addItemDecoration(new AutoFitRecyclerView.MarginDecoration(getContext()
//                .getResources().getDimensionPixelSize(R.dimen.item_margin)));
        recyclerViewMovies.setAdapter(mAdapter);

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        List<Movie> movies = mPresenter.getParcelableData();
        Parcelable parcelable = Parcels.wrap(movies);
        outState.putParcelable(EXTRA_MOVIES, parcelable);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            Parcelable parcelable = savedInstanceState.getParcelable(EXTRA_MOVIES);
            List<Movie> data = Parcels.unwrap(parcelable);
            mPresenter.restoreParcelableData(data);
        }

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.most_popular:
                mPresenter.clickMenuPopular();
                break;
            case R.id.top_rated:
                mPresenter.clickMenuTopRated();
                break;
            case R.id.favor:
                mPresenter.clickMenuFavor();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
    }



    @Override
    public void onResume() {
        super.onResume();
        //only call in two one pane ui to refresh data
        if(!twoPaneLayout) {
            mPresenter.onResume();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }


    @Override
    public void showProgress() {
        if (pbLoading != null) {
            pbLoading.show();
        }
    }

    @Override
    public void hideProgress() {
        if (pbLoading != null) {
            pbLoading.hide();
        }
    }

    @Override
    public void setItems(List<Movie> items) {
        mAdapter.setData(items);
        mAdapter.notifyDataSetChanged();
        //initially show detailFragment of the first movie
        if (items != null && items.size() > 0 && twoPaneLayout) {
            onItemClick(items.get(0));
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Movie m) {
        ((MainActivity) getActivity()).onItemClick(m);
    }

    @Override
    public void restoreItems(List<Movie> items) {
        mAdapter.setData(items);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void setTwoPaneLayout(boolean twoPaneLayout) {
        this.twoPaneLayout = twoPaneLayout;
    }

    public void refreshData() {
        mPresenter.refreshLocalMovie();
    }

}
