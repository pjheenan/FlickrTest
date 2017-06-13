package com.philheenan.presentation.publicfeed.presenter;

import com.philheenan.domain.action.invoker.InteractorInvoker;
import com.philheenan.domain.action.loadfeed.LoadFeedInteractor;
import com.philheenan.domain.action.loadfeed.LoadFeedOutput;
import com.philheenan.domain.model.FeedPage;
import com.philheenan.domain.model.ImageItem;
import com.philheenan.presentation.publicfeed.Presenter;
import com.philheenan.presentation.publicfeed.viewmodel.PublicFeedViewModel;

public class PublicFeedPresenter implements Presenter<PublicFeedViewModel>, LoadFeedOutput {

  PublicFeedViewModel viewModel;
  LoadFeedInteractor interactor;
  InteractorInvoker invoker;

  @Override public void setViewModel(PublicFeedViewModel viewModel) {
    this.viewModel = viewModel;
  }

  @Override public void start() {
    startInteractor();
  }

  @Override public void finish() {

  }

  @Override public void onFeedLoaded(FeedPage imageFeed) {

  }

  @Override public void onFeedError(Error error) {

  }

  public void onItemSelected(ImageItem item) {
    viewModel.goToImageView(item);
  }

  private void startInteractor() {
    interactor.setOutput(this);
    invoker.execute(interactor);
  }
}
