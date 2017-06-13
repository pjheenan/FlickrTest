package com.philheenan.domain.action.invoker;

import com.philheenan.domain.action.Interactor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InteractorInvokerImpl implements InteractorInvoker {

  ExecutorService executor;

  public InteractorInvokerImpl() {
    executor = Executors.newFixedThreadPool(3);
  }

  @Override public void execute(Interactor interactor) {
    executor.submit(new InteractorTask(interactor));
  }
}
