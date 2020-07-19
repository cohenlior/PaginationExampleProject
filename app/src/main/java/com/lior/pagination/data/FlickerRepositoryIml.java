package com.lior.pagination.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.lior.pagination.api.FlickerService;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FlickerRepositoryIml implements FlickerRepository {

    private static final int PAGE_SIZE = 100;
    private final FlickerService flickerService;

    private LiveData<NetworkStatus> networkState;
    private LiveData photoLiveData;

    public FlickerRepositoryIml(FlickerService flickerService) {
        this.flickerService = flickerService;
    }

    @Override
    public LiveData<NetworkStatus> getNetworkState() {
        return networkState;
    }

    @Override
    public LiveData getPhotoLiveData() {
        return photoLiveData;
    }

    @Override
    public void getFlickerFeed() {
        Executor executor = Executors.newFixedThreadPool(5);

        PhotosDataFactory photosDataFactory = new PhotosDataFactory(flickerService);
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
}