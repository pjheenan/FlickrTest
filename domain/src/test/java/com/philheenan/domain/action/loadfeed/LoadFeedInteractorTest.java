package com.philheenan.domain.action.loadfeed;

import com.philheenan.domain.DomainBaseTest;
import com.philheenan.domain.action.DomainRequest;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoadFeedInteractorTest extends DomainBaseTest {

  @Inject LoadFeedInteractor interactor;

  @Before public void setUp() {
    super.setUp();
    interactor = new LoadFeedInteractor();
  }

  @Test(expected = IllegalStateException.class) public void test_whenExecuteCalledAndOutputIsNull_thenErrorThrown() {
    interactor.setOutput(null);
    interactor.execute();
  }

  @Test public void test_whenInteractorCalledWithOutput_thenRemoteGatewayCalled() {
    LoadFeedOutput mockOutput = mock(LoadFeedOutput.class);
    interactor.remoteGateway = arrangeMockGateway(Observable.empty());
    interactor.setOutput(mockOutput);

    interactor.execute();

    verify(interactor.remoteGateway).loadFeed(any(DomainRequest.class));
  }

  private FeedRemoteGateway arrangeMockGateway(Observable<Object> observable) {
    FeedRemoteGateway mockGateway = mock(FeedRemoteGateway.class);
    when(mockGateway.loadFeed(any(DomainRequest.class))).thenReturn(observable);
    return mockGateway;
  }

}
