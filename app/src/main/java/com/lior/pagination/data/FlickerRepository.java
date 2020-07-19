package com.lior.pagination.data;

import androidx.lifecycle.LiveData;

public interface FlickerRepository {
    LiveData<NetworkStatus> getNetworkState();
    LiveData getPhotoLiveData();
    void getFlickerFeed();
}