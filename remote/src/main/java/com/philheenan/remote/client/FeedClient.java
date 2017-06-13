package com.philheenan.remote.client;

import com.philheenan.remote.entity.FeedEntity;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface FeedClient {

  @GET("/services/feeds/photos_public.gne") Call<FeedEntity> getPublicFeed(
      @QueryMap Map<String, String> queryParams);
}
