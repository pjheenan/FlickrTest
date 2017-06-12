package com.philheenan.domain.action.loadfeed;

import com.philheenan.domain.model.FeedPage;

public interface LoadFeedOutput {

  void onFeedLoaded(FeedPage imageFeed);

  void onFeedError(Error error);
}
