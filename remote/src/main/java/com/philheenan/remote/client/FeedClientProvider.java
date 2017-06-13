package com.philheenan.remote.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeedClientProvider {

  private static FeedClient instance;

  private FeedClientProvider() {
  }

  public static FeedClient provideRetrofit() {
    if (instance == null) {
      instance = new Retrofit.Builder().baseUrl("https://api.flickr.com")
          .addConverterFactory(GsonConverterFactory.create())
          .build().create(FeedClient.class);
    }
    return instance;
  }

}
