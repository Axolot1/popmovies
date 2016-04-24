
package com.axolotl.popmovies.retrofit.pojo;

import android.provider.BaseColumns;

import javax.annotation.Generated;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel(value = Parcel.Serialization.BEAN)
@Table(name = "Video", id = BaseColumns._ID)
@Generated("org.jsonschema2pojo")
public class Video extends Model{

    @Column(name = "remote_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    @SerializedName("id")
    @Expose
    public String videoId;

    //add to youtube endpoint
    @Column(name = "Key")
    @SerializedName("key")
    @Expose
    public String key;

    @Column(name = "Movie", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    public Movie movie;

    @SerializedName("iso_639_1")
    @Expose
    public String iso6391;
    @SerializedName("iso_3166_1")
    @Expose
    public String iso31661;


    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("site")
    @Expose
    public String site;
    @SerializedName("size")
    @Expose
    public Integer size;
    @SerializedName("type")
    @Expose
    public String type;

    public Video() {
        super();
    }


    /**
     * 
     * @return
     *     The id
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setVideoId(String id) {
        this.videoId = id;
    }

    /**
     * 
     * @return
     *     The iso6391
     */
    public String getIso6391() {
        return iso6391;
    }

    /**
     * 
     * @param iso6391
     *     The iso_639_1
     */
    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    /**
     * 
     * @return
     *     The iso31661
     */
    public String getIso31661() {
        return iso31661;
    }

    /**
     * 
     * @param iso31661
     *     The iso_3166_1
     */
    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    /**
     * 
     * @return
     *     The key
     */
    public String getKey() {
        return key;
    }

    /**
     * 
     * @param key
     *     The key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The site
     */
    public String getSite() {
        return site;
    }

    /**
     * 
     * @param site
     *     The site
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * 
     * @return
     *     The size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * 
     * @param size
     *     The size
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }



}
