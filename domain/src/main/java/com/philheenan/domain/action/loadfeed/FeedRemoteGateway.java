package com.philheenan.domain.action.loadfeed;

import com.philheenan.domain.action.DomainRequest;
import com.philheenan.domain.model.FeedPage;
import rx.Observable;

public interface FeedRemoteGateway {

  Observable<FeedPage> loadFeed(DomainRequest request);
}
