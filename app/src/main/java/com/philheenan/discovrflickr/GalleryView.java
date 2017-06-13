package com.philheenan.discovrflickr;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.philheenan.domain.action.invoker.InteractorInvokerImpl;
import com.philheenan.domain.action.loadfeed.LoadFeedInteractor;
import com.philheenan.domain.model.ImageItem;
import com.philheenan.presentation.ViewStates;
import com.philheenan.presentation.publicfeed.Presenter;
import com.philheenan.presentation.publicfeed.presenter.PublicFeedPresenter;
import com.philheenan.presentation.publicfeed.viewmodel.PublicFeedViewModel;
import com.philheenan.remote.gateway.FeedRemoteGatewayImpl;

public class GalleryView extends FrameLayout implements PublicFeedViewModel {

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.galleryGrid) GridLayout gridLayout;
  @BindView(R.id.loading) ProgressBar loadingSpinner;

  PublicFeedPresenter presenter = new PublicFeedPresenter();

  public GalleryView(@NonNull Context context) {
    super(context);
  }

  public GalleryView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public GalleryView(@NonNull Context context, @Nullable AttributeSet attrs,
      @AttrRes int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    init();
  }

  @Override public Presenter<PublicFeedViewModel> getPresenter() {
    return presenter;
  }

  @Override public void setViewState(int viewState) {
    if (viewState == ViewStates.LOADING) {
      updateLoadingView(true);
    } else if (viewState == ViewStates.LOADED) {
      updateLoadingView(false);
    } else if (viewState == ViewStates.EMPTY) {
      updateLoadingView(false);
      showEmptyResult();
    } else if (viewState == ViewStates.ERROR) {
      updateLoadingView(false);
    }
  }

  private void showEmptyResult() {
    gridLayout.setVisibility(View.INVISIBLE);
  }

  private void updateLoadingView(boolean isVisible) {
    //loadingSpinner.setVisibility(isVisible ? View.VISIBLE : View.GONE);
  }

  @Override public void goToImageView(ImageItem item) {

  }

  private void init() {
    initView();
    initInjects();
    initPresenter();
  }

  private void initView() {
  }

  private void initInjects() {
    ButterKnife.bind(this);
  }

  private void initPresenter() {
    // TODO: replace with dependency injection
    LoadFeedInteractor interactor = new LoadFeedInteractor(new FeedRemoteGatewayImpl());
    this.presenter = new PublicFeedPresenter(interactor, new InteractorInvokerImpl());
    getPresenter().setViewModel(this);
    getPresenter().start();
  }
}
