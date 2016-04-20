
package com.axolotl.popmovies.retrofit.pojo;

import android.provider.BaseColumns;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Table(name = "Movie", id = BaseColumns._ID)
public class Movie extends Model{
    @Column(name = "remote_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    @SerializedName("id")
    @Expose
    public Integer movieId;

    @Column(name = "PosterPath")
    @SerializedName("poster_path")
    @Expose
    public String posterPath;

    @Column(name = "Adult")
    @SerializedName("adult")
    @Expose
    public Boolean adult;

    @Column(name = "OverView")
    @SerializedName("overview")
    @Expose
    public String overview;

    @Column(name = "ReleaseDate")
    @SerializedName("release_date")
    @Expose
    public String releaseDate;

    @Column(name = "GenreIds")
    @SerializedName("genre_ids")
    @Expose
    public List<Integer> genreIds = new ArrayList<Integer>();

    @Column(name = "OriginalTitle")
    @SerializedName("original_title")
    @Expose
    public String originalTitle;

    @Column(name = "OriginalLanguage")
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;

    @Column(name = "Title")
    @SerializedName("title")
    @Expose
    public String title;

    @Column(name = "BackdropPath")
    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;

    @SerializedName("popularity")
    @Expose
    public Double popularity;

    @SerializedName("vote_count")
    @Expose
    public Integer voteCount;

    @SerializedName("video")
    @Expose
    public Boolean video;

    @SerializedName("vote_average")
    @Expose
    public Double voteAverage;

    public Movie() {
        super();
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer id) {
        this.movieId = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }
}
