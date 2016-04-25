package com.axolotl.popmovies.utils;

import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.axolotl.popmovies.retrofit.Review;
import com.axolotl.popmovies.retrofit.pojo.Movie;
import com.axolotl.popmovies.retrofit.pojo.Video;

import java.util.List;

/**
 * Created by axolotl on 16/4/24.
 */
public class DbUtils {
    public static void saveMovie(Movie movie, List<Video> videos, List<Review> reviews) {
        movie.setFavor(true);
        movie.save();
        ActiveAndroid.beginTransaction();
        try {
            if(videos != null) {
                for (Video v : videos) {
                    v.setMovie(movie);
                    v.save();
                }
            }
            if(reviews != null) {
                for (Review r : reviews) {
                    r.setMovie(movie);
                    r.save();
                }
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    public static void delMovie(int movieId) {
        ActiveAndroid.beginTransaction();
        Movie m = findMovie(movieId);
        if(m == null){
            return ;
        }
        List<Video> videos = findVideos(m);
        List<Review> reviews = findReviews(m);
        try {
            if (videos != null) {
                for (Video v : videos) {
                    Log.i("db", "del v");
                    v.delete();
                }
            }
            if(reviews != null){
                for(Review review : reviews){
                    Log.i("db", "del r");
                    review.delete();
                }
            }
            m.delete();
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    public static List<Review> findReviews(Movie m) {
        return new Select()
                .from(Review.class)
                .where("Movie = ?", m.getId())
                .execute();
    }

    public static List<Video> findVideos(Movie m) {
        return new Select()
                .from(Video.class)
                .where("Movie = ?", m.getId())
                .execute();
    }

    public static Movie findMovie(int movieId){
        return new Select()
                .from(Movie.class)
                .where("Remote_id = ?", movieId)
                .executeSingle();
    }

    public static List<Movie> getLocalMovies(){
        return new Select()
                .from(Movie.class)
                .execute();
    }
}
