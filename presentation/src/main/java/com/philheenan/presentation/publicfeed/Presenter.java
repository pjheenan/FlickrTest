package com.philheenan.presentation.publicfeed;

import com.philheenan.presentation.ViewModel;

public interface Presenter<T extends ViewModel> {

  void setViewModel(T viewModel);

  void start();

  void finish();

}
