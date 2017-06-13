package com.philheenan.remote.gateway;

import com.philheenan.domain.action.DomainRequest;
import com.philheenan.domain.action.loadfeed.FeedRemoteGateway;
import com.philheenan.domain.model.FeedPage;
import com.philheenan.remote.client.FeedClient;
import com.philheenan.remote.client.FeedClientProvider;
import com.philheenan.remote.entity.FeedEntity;
import com.philheenan.remote.request.FeedRequest;
import com.philheenan.remote.request.PublicFeedsRequest;
import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

public class FeedRemoteGatewayImpl implements FeedRemoteGateway {

  FeedClient feedClient;

  public FeedRemoteGatewayImpl() {
    feedClient = FeedClientProvider.provideRetrofit();
  }

  @Override public Observable<FeedPage> load(DomainRequest request) {
    final FeedRequest remoteRequest = buildFeedRequest();
    return Observable.create(new Observable.OnSubscribe<FeedPage>() {
      @Override public void call(Subscriber<? super FeedPage> subscriber) {
        try {
          Call call = feedClient.getPublicFeed(remoteRequest.queryParams);
          processResponse(subscriber, (FeedEntity) call.execute().body());
        } catch (Exception e) {
          processError(subscriber, e);
        }
      }
    });
  }

  void processResponse(Subscriber<? super FeedPage> subscriber, FeedEntity feedEntity) {
    System.out.println("###### FEED ENTITY: " + feedEntity.toString());
    subscriber.onNext(mapResponse(feedEntity));
    subscriber.onCompleted();
  }

  void processError(Subscriber<? super FeedPage> subscriber, Throwable error) {
    subscriber.onError(error);
  }

  private FeedRequest buildFeedRequest() {
    return new PublicFeedsRequest();
  }

  private FeedPage mapResponse(FeedEntity entity) {
    return entity.mapToModel();
  }
}
