package com.lior.pagination.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.lior.pagination.api.FlickerApi;
import com.lior.pagination.api.FlickerService;
import com.lior.pagination.model.Photo;
import com.lior.pagination.model.FlickerResponse;

import retrofit2.Call;
import retrofit2.Callback;

import static com.lior.pagination.BuildConfig.API_KEY;

public class PhotosDataSource extends PageKeyedDataSource<Integer, Photo> {

    private static final String TAG = PhotosDataSource.class.getSimpleName();

    private FlickerService flickerService;

    private MutableLiveData<NetworkStatus> networkState;

    public PhotosDataSource(FlickerService flickerService) {
        this.flickerService = flickerService;
        networkState = new MutableLiveData<>();
    }

    public MutableLiveData<NetworkStatus> getNetworkState() {
        return networkState;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull LoadInitialCallback<Integer, Photo> callback) {

        networkState.postValue(NetworkStatus.LOADING);

        flickerService.getRecentPhotos(API_KEY, 1)
                .enqueue(new Callback<FlickerResponse>() {
                    @Override
                    public void onResponse(Call<FlickerResponse> call, retrofit2.Response<FlickerResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            callback.onResult(response.body().photos.photoList, 1, 2);
                            networkState.postValue(NetworkStatus.SUCCESS);

                        } else {
                            networkState.postValue(NetworkStatus.FAILED);
                            Log.e(TAG, "Load initial error");
                        }
                    }

                    @Override
                    public void onFailure(Call<FlickerResponse> call, Throwable t) {
                        String errorMessage = t.getMessage();
                        networkState.postValue(NetworkStatus.FAILED);
                        Log.i(TAG, errorMessage != null ? errorMessage : "Fetch photos error");
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params,
                           @NonNull LoadCallback<Integer, Photo> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params,
                          @NonNull LoadCallback<Integer, Photo> callback) {

        Log.i(TAG, "Loading page " + params.key + " Count " + params.requestedLoadSize);

        networkState.postValue(NetworkStatus.LOADING);

        flickerService.getRecentPhotos(API_KEY, params.key).enqueue(new Callback<FlickerResponse>() {
            @Override
            public void onResponse(Call<FlickerResponse> call, retrofit2.Response<FlickerResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    callback.onResult(response.body().photos.photoList, params.key + 1);
                    networkState.postValue(NetworkStatus.SUCCESS);

                } else {
                    networkState.postValue(NetworkStatus.PAGINATION_ERROR);
                    Log.e(TAG, "Pagination load after error");
                }
            }

            @Override
            public void onFailure(Call<FlickerResponse> call, Throwable t) {
                String errorMessage = t.getMessage();
                networkState.postValue(NetworkStatus.PAGINATION_ERROR);
                Log.e(TAG, errorMessage != null ? errorMessage : "Fetch photos error");
            }
        });
    }
}
