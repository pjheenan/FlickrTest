package com.philheenan.domain.action.invoker;

import com.philheenan.domain.action.Interactor;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;

public class InteractorInvokerImpl implements InteractorInvoker {

  @Inject ExecutorService executor;
  @Inject Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

  @Override public void execute(Interactor interactor) {
    executor.submit(new InteractorTask(interactor, uncaughtExceptionHandler));
  }
}
