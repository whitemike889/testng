package org.testng.internal.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Reduced interface to mimic Future.
 *
 * @author <a href='mailto:the_mindstorm@evolva.ro'>Alexandru Popescu</a>
 */
public interface IFutureResult {
  Object get() throws InterruptedException, ThreadExecutionException;

  Object get(long timeOut, TimeUnit unit) throws InterruptedException, ThreadExecutionException,
      TimeoutException;
}
