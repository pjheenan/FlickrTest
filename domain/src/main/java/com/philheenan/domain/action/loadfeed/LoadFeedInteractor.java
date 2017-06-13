package com.philheenan.domain.action.loadfeed;

import com.philheenan.domain.action.DomainRequest;
import com.philheenan.domain.action.Interactor;
import com.philheenan.domain.model.FeedPage;
import rx.Observer;

public class LoadFeedInteractor implements Interactor {

  FeedRemoteGateway remoteGateway;

  private LoadFeedOutput output;

  @Override public void run() {
    checkOutput();
    loadImageFeed();
  }

  public void setOutput(LoadFeedOutput output) {
    this.output = output;
  }

  private void loadImageFeed() {
    /**
     * TODO: control the use of local data caching here
     * If a local cache of data is enabled the call the local gateway here, check for data
     * and whether the data is within the cache limit. The decision can then be made as to
     * whether the remote call is required. If the data is cached and out of date, then update
     * the local gateway when the remote returns before returning the data on the output
     */
    loadImageFeedRemote();
  }

  private void loadImageFeedRemote() {
    remoteGateway.load(buildDomainRequest()).subscribe(new Observer<FeedPage>() {
      @Override public void onNext(FeedPage result) {
        output.onFeedLoaded(result);
      }

      @Override public void onCompleted() {
        //no used - business process does not require this.
        // Completion happens on return of an error or FeedPage
      }

      @Override public void onError(Throwable e) {
        output.onFeedError(new Error());
      }
    });
  }

  private DomainRequest buildDomainRequest() {
    return new DomainRequest();
  }

  private void checkOutput() {
    if (output == null) {
      throw new IllegalStateException("Output cannot be null");
    }
  }
}
