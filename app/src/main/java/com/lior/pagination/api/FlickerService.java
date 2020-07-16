package com.lior.pagination.api;

import com.lior.pagination.model.FlickerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickerService {
    @GET("?method=flickr.photos.getRecent&extras=url_s&format=json&nojsoncallback=1")
    Call<FlickerResponse> getRecentPhotos(@Query("api_key") String apiKey,
                                          @Query("page") long page);
}
