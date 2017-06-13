package com.philheenan.remote.gateway;

import com.philheenan.domain.action.DomainRequest;
import com.philheenan.domain.action.loadfeed.FeedRemoteGateway;
import com.philheenan.domain.model.FeedPage;
import com.philheenan.remote.RemoteBaseTest;
import com.philheenan.remote.entity.FeedEntity;
import org.junit.Before;
import org.junit.Test;
import rx.Observer;
import rx.Subscriber;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class FeedRemoteGatewayImplTest extends RemoteBaseTest {

  private FeedRemoteGateway gateway = new FeedRemoteGatewayImpl();
  private FeedPage feedPageResult = null;
  private boolean onCompletedCalled = false;
  private boolean onErrorCalled = false;

  @Before public void setUp() {
    super.setUp();
  }

  @Test public void test_WhenLoadFeedCalled_thenObservableReturned() {
    assertNotNull(gateway.loadFeed(new DomainRequest()));
  }

  @Test public void test_whenResponseReturned_thenSubscriberCalled() {
    Subscriber<FeedPage> subscriber = new TestSubscriber<>(arrangeTestSubscriberDelegate());
    ((FeedRemoteGatewayImpl) gateway).processResponse(subscriber, new FeedEntity());

    assertNotNull(feedPageResult);
    assertTrue(onCompletedCalled);
    assertFalse(onErrorCalled);
  }

  @Test public void test_whenErrorReturned_thenSubscriberErrorCalled() {
    Subscriber<FeedPage> subscriber = new TestSubscriber<>(arrangeTestSubscriberDelegate());
    ((FeedRemoteGatewayImpl) gateway).processError(subscriber, new NullPointerException());

    assertNull(feedPageResult);
    assertFalse(onCompletedCalled);
    assertTrue(onErrorCalled);
  }

  private Observer<FeedPage> arrangeTestSubscriberDelegate() {
    return new Observer<FeedPage>() {
      @Override public void onCompleted() {
        onCompletedCalled = true;
      }

      @Override public void onError(Throwable e) {
        onErrorCalled = true;
      }

      @Override public void onNext(FeedPage feedPage) {
        feedPageResult = feedPage;
      }
    };
  }
}
