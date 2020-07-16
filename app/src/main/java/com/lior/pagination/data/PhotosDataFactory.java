package com.lior.pagination.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.lior.pagination.model.Photo;

public class PhotosDataFactory extends DataSource.Factory<Integer, Photo> {

    private MutableLiveData<PhotosDataSource> mutableLiveData;

    public PhotosDataFactory() {
        this.mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource<Integer, Photo> create() {
        PhotosDataSource photosDataSource = new PhotosDataSource();
        mutableLiveData.postValue(photosDataSource);
        return photosDataSource;
    }

    public LiveData<PhotosDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}