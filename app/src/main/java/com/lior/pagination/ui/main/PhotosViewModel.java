package com.lior.pagination.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.lior.pagination.data.FlickerRepository;
import com.lior.pagination.data.NetworkStatus;
import com.lior.pagination.data.PhotosDataFactory;
import com.lior.pagination.data.PhotosDataSource;
import com.lior.pagination.model.Photo;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * ViewModel for the [MainFragment] screen.
 * The ViewModel works with the [FlickerRepository] to get the data.
 */
public class PhotosViewModel extends ViewModel {

    private FlickerRepository repository;

    public PhotosViewModel(FlickerRepository repository) {
        this.repository = repository;
        init();
    }

    private void init() {
        repository.getFlickerFeed();
    }

    public LiveData<PagedList<Photo>> getPhotoLiveData() {
        return repository.getPhotoLiveData();
    }

    public LiveData<NetworkStatus> getNetworkState() {
        return repository.getNetworkState();
    }
}