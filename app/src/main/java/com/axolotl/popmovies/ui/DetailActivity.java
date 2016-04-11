package com.axolotl.popmovies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.iv_portal)
    ImageView ivPortal;
    @Bind(R.id.card_view)
    CardView cardView;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_release_date)
    TextView tvReleaseDate;
    @Bind(R.id.tv_vote)
    TextView tvVote;

    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        Movie m = Parcels.unwrap(i.getParcelableExtra(EXTRA_MOVIE));
        if (m != null) {
            Picasso.with(this).load(TdbMovieApi.IMAGE_URL + m.getPosterPath()).into(ivPortal);
            tvTitle.setText(m.getTitle());
            tvReleaseDate.setText(m.getReleaseDate());
            tvVote.setText(m.getVoteAverage() + "");
        }
    }


}
