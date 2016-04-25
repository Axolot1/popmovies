package com.axolotl.popmovies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.axolotl.popmovies.R;
import com.axolotl.popmovies.retrofit.pojo.Movie;

import org.parceler.Parcels;

public class MainActivity extends AppCompatActivity implements
        MainFragment.CallBack, DetailFragment.CallBack{

    private static final String DETAILFRAGMENT_TAG = "DFTAG";
    private boolean mTwoPane;
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.movie_detail_container) != null) {
            Log.i("pane", "two pane");
            mTwoPane = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_detail_container, new DetailFragment(), DETAILFRAGMENT_TAG)
                        .commit();
            }
        } else {
            Log.i("pane", "one pane");
            mTwoPane = false;
        }

        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_main);
        mainFragment.setTwoPaneLayout(mTwoPane);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(Movie movie) {
        if(mTwoPane){
            DetailFragment detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(DetailFragment.ARG_MOVIE, Parcels.wrap(movie));
            detailFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, detailFragment, DETAILFRAGMENT_TAG).commit();
        }else{
            Intent i = new Intent(this, DetailActivity.class);
            i.putExtra(DetailActivity.EXTRA_MOVIE, Parcels.wrap(movie));
            startActivity(i);
        }
    }

    @Override
    public void onDelMovie() {
        Log.i("main", "del");
        mainFragment.refreshData();
    }
}


