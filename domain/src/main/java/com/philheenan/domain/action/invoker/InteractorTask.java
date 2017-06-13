package com.philheenan.domain.action.invoker;

import java.util.concurrent.FutureTask;

public class InteractorTask extends FutureTask<Void> {

  private final String description;

  public InteractorTask(Runnable runnable) {
    super(runnable, null);
    this.description = runnable.getClass().toString();
  }

  @Override protected void done() {
    super.done();
    try {
      get();
    } catch (Exception e) {
      unhandledException(e);
    }
  }

  private void unhandledException(Throwable cause) {
    UnhandledInteractorException e =
        new UnhandledInteractorException(description, cause.getClass().getName());
    e.initCause(cause);
  }
}
