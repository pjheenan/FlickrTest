package com.philheenan.discovrflickr;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.philheenan.domain.model.ImageItem;
import com.philheenan.presentation.ViewStates;
import com.philheenan.presentation.publicfeed.Presenter;
import com.philheenan.presentation.publicfeed.presenter.PublicFeedPresenter;
import com.philheenan.presentation.publicfeed.viewmodel.PublicFeedViewModel;
import javax.inject.Inject;

public class GalleryView extends FrameLayout implements PublicFeedViewModel {

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.galleryGrid) GridLayout gridLayout;
  @BindView(R.id.loading) ProgressBar loadingSpinner;

  @Inject PublicFeedPresenter presenter;

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
    loadingSpinner.setVisibility(isVisible ? View.VISIBLE : View.GONE);
  }

  @Override public void goToImageView(ImageItem item) {

  }

  private void init() {
    initInjects();
    initGrid();
    initPresenter();
  }

  private void initGrid() {

  }

  private void initInjects() {
    ButterKnife.bind(this);
    //DaggerPublicFeedComponent.create();
  }

  private void initPresenter() {
    getPresenter().setViewModel(this);
    getPresenter().start();
  }
}
