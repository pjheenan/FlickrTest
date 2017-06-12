package com.philheenan.domain.action.loadfeed;

public interface LoadFeedOutput {

  void onFeedLoaded(Object imageFeed);

  void onFeedError(Error error);
}
