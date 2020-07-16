package com.lior.pagination.data;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class PhotosDataFactory extends DataSource.Factory {

    private MutableLiveData<PhotosDataSource> mutableLiveData;

    public PhotosDataFactory() {
        this.mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        PhotosDataSource photosDataSource = new PhotosDataSource();
        mutableLiveData.postValue(photosDataSource);
        return photosDataSource;
    }

    public MutableLiveData<PhotosDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}