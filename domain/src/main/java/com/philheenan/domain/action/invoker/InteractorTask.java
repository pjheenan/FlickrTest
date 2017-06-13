package com.philheenan.domain.action.invoker;

import java.util.concurrent.FutureTask;

public class InteractorTask extends FutureTask<Void> {

  private final Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
  private final String description;

  public InteractorTask(Runnable runnable,
      Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
    super(runnable, null);
    this.uncaughtExceptionHandler = uncaughtExceptionHandler;
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
    uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), e);
  }
}
