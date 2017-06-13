package com.philheenan.domain.action.loadfeed;

import com.philheenan.domain.DomainBaseTest;
import com.philheenan.domain.action.DomainRequest;
import com.philheenan.domain.model.FeedPage;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class LoadFeedInteractorTest extends DomainBaseTest {

  LoadFeedInteractor interactor;

  @Before public void setUp() {
    super.setUp();
    interactor = new LoadFeedInteractor();
  }

  @Test(expected = IllegalStateException.class)
  public void test_whenExecuteCalledAndOutputIsNull_thenErrorThrown() {
    interactor.setOutput(null);
    interactor.execute();
  }

  @Test public void test_whenInteractorCalledWithOutput_thenRemoteGatewayCalled() {
    LoadFeedOutput mockOutput = mock(LoadFeedOutput.class);
    interactor.setOutput(mockOutput);
    interactor.remoteGateway = arrangeMockGateway(Observable.<FeedPage>empty());

    interactor.execute();

    verify(interactor.remoteGateway).load(any(DomainRequest.class));
  }

  @Test public void test_whenGatewayReturnsData_thenOutputIsCalledWithData() {
    LoadFeedOutput mockOutput = mock(LoadFeedOutput.class);
    interactor.setOutput(mockOutput);
    interactor.remoteGateway = arrangeMockGateway(Observable.just(new FeedPage()));

    interactor.execute();

    verify(mockOutput).onFeedLoaded(eq(new FeedPage()));
  }

  @Test public void test_whenGatewayReturnsError_thenOutputIsCalledWithError() {
    LoadFeedOutput mockOutput = mock(LoadFeedOutput.class);
    interactor.setOutput(mockOutput);
    interactor.remoteGateway =
        arrangeMockGateway(Observable.<FeedPage>error(new Throwable("error")));

    interactor.execute();

    verify(mockOutput).onFeedError(any(Error.class));
  }

  private FeedRemoteGateway arrangeMockGateway(Observable<FeedPage> observable) {
    FeedRemoteGateway mockGateway = mock(FeedRemoteGateway.class);
    when(mockGateway.load(any(DomainRequest.class))).thenReturn(observable);
    return mockGateway;
  }
}
