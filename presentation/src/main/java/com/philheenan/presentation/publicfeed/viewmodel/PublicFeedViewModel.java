package com.philheenan.presentation.publicfeed.viewmodel;

import com.philheenan.domain.model.ImageItem;
import com.philheenan.presentation.ViewModel;

public interface PublicFeedViewModel extends ViewModel {

  void setViewState(int viewState);

  void goToImageView(ImageItem item);
}
