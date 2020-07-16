package com.lior.pagination.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photos {

    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("pages")
    @Expose
    public Integer pages;
    @SerializedName("perpage")
    @Expose
    public Integer perpage;
    @SerializedName("total")
    @Expose
    public String total;
    @SerializedName("photo")
    @Expose
    public List<Photo> photoList = null;
}
