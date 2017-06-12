package com.philheenan.remote.gateway;

import com.philheenan.domain.action.DomainRequest;
import com.philheenan.domain.action.loadfeed.FeedRemoteGateway;
import com.philheenan.remote.RemoteBaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FeedRemoteGatewayImplTest extends RemoteBaseTest {

  private FeedRemoteGateway gateway = new FeedRemoteGatewayImpl();

  @Before public void setUp() {
    super.setUp();
  }

  @Test public void test_WhenLoadFeedCalled_thenObservableReturned() {
    Assert.assertNotNull(gateway.loadFeed(new DomainRequest()));
  }
}
