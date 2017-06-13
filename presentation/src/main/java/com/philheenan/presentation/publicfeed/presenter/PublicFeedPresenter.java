package com.philheenan.presentation.publicfeed.presenter;

import com.philheenan.domain.action.invoker.InteractorInvoker;
import com.philheenan.domain.action.loadfeed.LoadFeedInteractor;
import com.philheenan.domain.action.loadfeed.LoadFeedOutput;
import com.philheenan.domain.model.FeedPage;
import com.philheenan.domain.model.ImageItem;
import com.philheenan.presentation.ViewStates;
import com.philheenan.presentation.publicfeed.Presenter;
import com.philheenan.presentation.publicfeed.viewmodel.PublicFeedViewModel;
import java.util.List;

public class PublicFeedPresenter implements Presenter<PublicFeedViewModel>, LoadFeedOutput {

  LoadFeedInteractor interactor;
  InteractorInvoker invoker;

  PublicFeedViewModel viewModel;
  FeedPage data;

  public PublicFeedPresenter() {
  }

  public PublicFeedPresenter(LoadFeedInteractor interactor, InteractorInvoker invoker) {
    this.interactor = interactor;
    this.invoker = invoker;
  }

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

    System.out.println("#### PRESENTER DATA: " + imageFeed.toString());
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
