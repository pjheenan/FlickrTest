package com.philheenan.domain.action.loadfeed;

import com.philheenan.domain.action.DomainRequest;
import rx.Observer;

public class LoadFeedInteractor {

  FeedRemoteGateway remoteGateway;

  private LoadFeedOutput output;

  public void execute() {
    checkOutput();
    loadImageFeed();
  }

  public void setOutput(LoadFeedOutput output) {
    this.output = output;
  }

  private void loadImageFeed() {
    remoteGateway.loadFeed(buildDomainRequest()).subscribe(new Observer<Object>() {
      @Override public void onNext(Object result) {
        output.onFeedLoaded(result);
      }

      @Override public void onCompleted() {

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