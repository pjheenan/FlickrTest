package com.philheenan.presentation.publicfeed.presenter;

import com.philheenan.domain.action.invoker.InteractorInvoker;
import com.philheenan.domain.action.loadfeed.LoadFeedInteractor;
import com.philheenan.domain.action.loadfeed.LoadFeedOutput;
import com.philheenan.domain.model.FeedPage;
import com.philheenan.domain.model.ImageItem;
import com.philheenan.presentation.publicfeed.viewmodel.PublicFeedViewModel;
import com.philheenan.presentation.publicfeed.viewmodel.ViewStates;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class PublicFeedPresenterTest {

  PublicFeedPresenter presenter;

  @Before public void setUp() {
    presenter = new PublicFeedPresenter();
    arrangePresenter();
  }

  @Test public void test_whenViewModelSet_thenViewModelAvailable() {
    Assert.assertNotNull(presenter.viewModel);
  }

  @Test public void test_whenStartCalled_thenInteractorCalled() {
    presenter.start();

    verify(presenter.interactor).setOutput(any(LoadFeedOutput.class));
    verify(presenter.invoker).execute(eq(presenter.interactor));
  }

  @Test public void test_whenSelectedItemCalled_thenViewModelCalled() {
    ImageItem item = new ImageItem();
    presenter.onItemSelected(item);

    verify(presenter.viewModel).goToImageView(eq(item));
  }

  @Test public void test_whenStartCalled_thenViewStateLoading() {
    presenter.start();

    verify(presenter.viewModel).setViewState(eq(ViewStates.LOADING));
  }

  @Test public void test_whenErrorReturned_thenViewStateError() {
    presenter.onFeedError(new Error());

    verify(presenter.viewModel).setViewState(eq(ViewStates.ERROR));
  }

  @Test public void test_whenEmptyResultCalled_thenViewStateEmpty() {
    presenter.onFeedLoaded(new FeedPage());

    verify(presenter.viewModel).setViewState(eq(ViewStates.EMPTY));
  }

  @Test public void test_whenPopulatedResultCalled_thenViewStateLoaded() {
    FeedPage page = arrangeFeedPage();
    presenter.onFeedLoaded(page);

    verify(presenter.viewModel).setViewState(eq(ViewStates.LOADED));
  }

  @Test public void test_whenDataReturnedAndLoadImagesCalled_thenResponseDataIsReturned() {
    FeedPage page = arrangeFeedPage();
    presenter.onFeedLoaded(page);
    List<ImageItem> output = presenter.loadImages();
    assertEquals(page.imageItems, output);
  }

  private FeedPage arrangeFeedPage() {
    FeedPage page = new FeedPage();
    page.imageItems = new ArrayList<>();
    page.imageItems.add(new ImageItem());
    return page;
  }

  private void arrangePresenter() {
    presenter.invoker = mock(InteractorInvoker.class);
    presenter.interactor = mock(LoadFeedInteractor.class);
    presenter.setViewModel(mock(PublicFeedViewModel.class));
  }
}
