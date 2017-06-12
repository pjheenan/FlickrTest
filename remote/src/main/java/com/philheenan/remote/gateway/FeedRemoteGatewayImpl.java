package com.philheenan.remote.gateway;

import com.philheenan.domain.action.DomainRequest;
import com.philheenan.domain.action.loadfeed.FeedRemoteGateway;
import com.philheenan.domain.model.FeedPage;
import rx.Observable;
import rx.Subscriber;

public class FeedRemoteGatewayImpl implements FeedRemoteGateway {

  @Override public Observable<FeedPage> loadFeed(DomainRequest request) {
    return Observable.create(new Observable.OnSubscribe<FeedPage>() {
      @Override public void call(Subscriber<? super FeedPage> subscriber) {

      }
    });
  }
}
