package com.lior.pagination.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("owner")
    @Expose
    public String owner;
    @SerializedName("secret")
    @Expose
    public String secret;
    @SerializedName("server")
    @Expose
    public String server;
    @SerializedName("farm")
    @Expose
    public Integer farm;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("ispublic")
    @Expose
    public Integer ispublic;
    @SerializedName("isfriend")
    @Expose
    public Integer isfriend;
    @SerializedName("isfamily")
    @Expose
    public Integer isfamily;
    @SerializedName("url_s")
    @Expose
    public String urlS;
    @SerializedName("height_s")
    @Expose
    public String heightS;
    @SerializedName("width_s")
    @Expose
    public String widthS;

    public String getId() {
        return id;
    }

    public String getUrlS() {
        return urlS;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Photo photo = (Photo) obj;
        return photo.id.equals(this.id);
    }
}
