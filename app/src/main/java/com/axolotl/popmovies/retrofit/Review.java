
package com.axolotl.popmovies.retrofit;

import javax.annotation.Generated;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.axolotl.popmovies.retrofit.pojo.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel(value = Parcel.Serialization.BEAN)
@Generated("org.jsonschema2pojo")
public class Review extends Model{

    @Column(name = "remote_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    @SerializedName("id")
    @Expose
    public String reviewId;

    @Column(name = "Author")
    @SerializedName("author")
    @Expose
    public String author;

    @Column(name = "Content")
    @SerializedName("content")
    @Expose
    public String content;

    @Column(name = "Movie", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public Movie movie;

    @SerializedName("url")
    @Expose
    public String url;

    public Review() {
        super();
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    /**
     * 
     * @return
     *     The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 
     * @param author
     *     The author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 
     * @return
     *     The content
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
