package com.lior.pagination.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.lior.pagination.api.FlickerService;
import com.lior.pagination.model.Photo;

public class PhotosDataFactory extends DataSource.Factory<Integer, Photo> {

    private final FlickerService flickerService;
    private MutableLiveData<PhotosDataSource> mutableLiveData;

    public PhotosDataFactory(FlickerService flickerService) {
        this.mutableLiveData = new MutableLiveData<>();
        this.flickerService = flickerService;
    }

    @Override
    public DataSource<Integer, Photo> create() {
        PhotosDataSource photosDataSource = new PhotosDataSource(flickerService);
        mutableLiveData.postValue(photosDataSource);
        return photosDataSource;
    }

    public LiveData<PhotosDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}