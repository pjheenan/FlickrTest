package com.philheenan.domain.action.loadfeed;

import com.philheenan.domain.action.DomainRequest;
import com.philheenan.domain.action.RemoteGateway;
import com.philheenan.domain.model.FeedPage;
import rx.Observable;

public interface FeedRemoteGateway extends RemoteGateway<FeedPage> {

  Observable<FeedPage> load(DomainRequest request);
}
