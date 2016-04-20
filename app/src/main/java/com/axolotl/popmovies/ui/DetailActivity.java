package com.axolotl.popmovies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.axolotl.popmovies.R;
import com.axolotl.popmovies.retrofit.TdbMovieApi;
import com.axolotl.popmovies.retrofit.pojo.Movie;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by axolotl on 16/4/11.
 */
public class DetailActivity extends AppCompatActivity {


    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        if(savedInstanceState == null){
            Intent i = getIntent();
            DetailFragment detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(DetailFragment.ARG_MOVIE, i.getParcelableExtra(EXTRA_MOVIE));
            detailFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, detailFragment).commit();
        }
    }

}
