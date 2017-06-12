package com.philheenan.domain.action.loadfeed;

import com.philheenan.domain.action.DomainRequest;
import rx.Observable;

public interface FeedRemoteGateway {

  Observable<Object> loadFeed(DomainRequest request);
}
