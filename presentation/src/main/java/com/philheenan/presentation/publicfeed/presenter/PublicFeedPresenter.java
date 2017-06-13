package com.philheenan.presentation.publicfeed.presenter;

import com.philheenan.domain.action.invoker.InteractorInvoker;
import com.philheenan.domain.action.loadfeed.LoadFeedInteractor;
import com.philheenan.domain.action.loadfeed.LoadFeedOutput;
import com.philheenan.domain.model.FeedPage;
import com.philheenan.domain.model.ImageItem;
import com.philheenan.presentation.publicfeed.Presenter;
import com.philheenan.presentation.publicfeed.viewmodel.PublicFeedViewModel;
import com.philheenan.presentation.ViewStates;
import java.util.List;

public class PublicFeedPresenter implements Presenter<PublicFeedViewModel>, LoadFeedOutput {

  PublicFeedViewModel viewModel;
  LoadFeedInteractor interactor;
  InteractorInvoker invoker;
  FeedPage data;

  @Override public void setViewModel(PublicFeedViewModel viewModel) {
    this.viewModel = viewModel;
  }

  @Override public void start() {
    startInteractor();
    viewModel.setViewState(ViewStates.LOADING);
  }

  @Override public void finish() {

  }

  @Override public void onFeedLoaded(FeedPage imageFeed) {
    data = imageFeed;
    if (imageFeed.imageItems == null || imageFeed.imageItems.isEmpty()) {
      viewModel.setViewState(ViewStates.EMPTY);
    } else {
      viewModel.setViewState(ViewStates.LOADED);
    }
  }

  @Override public void onFeedError(Error error) {
    viewModel.setViewState(ViewStates.ERROR);
  }

  public List<ImageItem> loadImages() {
    return data.imageItems;
  }

  public void onItemSelected(ImageItem item) {
    viewModel.goToImageView(item);
  }

  private void startInteractor() {
    interactor.setOutput(this);
    invoker.execute(interactor);
  }
}
