
package com.axolotl.popmovies.retrofit.pojo;

import android.provider.BaseColumns;

import javax.annotation.Generated;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Table(name = "Review", id = BaseColumns._ID)
@Generated("org.jsonschema2pojo")
public class Review extends Model{

    @Column(name = "ReviewId", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
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

    /**
     * 
     * @return
     *     The id
     */
    public String getReviewId() {
        return reviewId;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setReviewId(String id) {
        this.reviewId = id;
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
