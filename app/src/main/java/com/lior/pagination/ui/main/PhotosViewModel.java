package com.lior.pagination.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.lior.pagination.data.NetworkStatus;
import com.lior.pagination.data.PhotosDataFactory;
import com.lior.pagination.data.PhotosDataSource;
import com.lior.pagination.model.Photo;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PhotosViewModel extends ViewModel {

    private static final int PAGE_SIZE = 100;
    private LiveData<NetworkStatus> networkState;
    private LiveData<PagedList<Photo>> photoLiveData;

    public PhotosViewModel() {
        init();
    }

    private void init() {
        Executor executor = Executors.newFixedThreadPool(5);

        PhotosDataFactory photosDataFactory = new PhotosDataFactory();
        networkState = Transformations.switchMap(photosDataFactory.getMutableLiveData(),
                PhotosDataSource::getNetworkState);

        PagedList.Config pagedListConfig = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(PAGE_SIZE * 3)
                .setPageSize(PAGE_SIZE)
                .build();

        photoLiveData = new LivePagedListBuilder(photosDataFactory, pagedListConfig)
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<NetworkStatus> getNetworkState() { return networkState; }

    public LiveData<PagedList<Photo>> getPhotoLiveData() {
        return photoLiveData;
    }
}