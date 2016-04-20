package com.axolotl.popmovies.ui;

import android.content.Intent;
import android.os.Bundle;
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


    @Bind(R.id.pb_loading)
    ContentLoadingProgressBar pbLoading;
    @Bind(R.id.recyclerView_movies)
    AutoFitRecyclerView recyclerViewMovies;

    @Inject
    MovieAdapter mAdapter;

    @Inject
    MainFragmentPresenter mPresenter;

    private static final String TAG = "fragment";

    public MainFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.initialize();
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


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView() {
        recyclerViewMovies.addItemDecoration(new AutoFitRecyclerView.MarginDecoration(getContext()
                .getResources().getDimensionPixelSize(R.dimen.item_margin)));
        recyclerViewMovies.setAdapter(mAdapter);
//        recyclerViewMovies.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        int spanCount = 2; // 3 columns
//        int spacing = 50; // 50px
//        boolean includeEdge = false;
//        recyclerViewMovies.addItemDecoration(new MovieAdapter.GridSpacingItemDecoration(spanCount, spacing, includeEdge));
//        recyclerViewMovies.setAdapter(mAdapter);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

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
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Movie m) {
        Intent i = new Intent(getActivity(), DetailActivity.class);
        i.putExtra(DetailActivity.EXTRA_MOVIE, Parcels.wrap(m));
        startActivity(i);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
