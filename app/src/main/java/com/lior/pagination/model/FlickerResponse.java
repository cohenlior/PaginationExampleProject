package com.lior.pagination.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlickerResponse {

    @SerializedName("photos")
    @Expose
    public Photos photos;
    @SerializedName("stat")
    @Expose
    public String stat;
}