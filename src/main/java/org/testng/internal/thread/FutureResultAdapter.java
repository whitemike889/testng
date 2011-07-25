package org.testng.internal.thread;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * A very reduced interface of <code>Future</code>.
 *
 * @author <a href="mailto:the_mindstorm@evolva.ro>the_mindstorm</a>
 */
public class FutureResultAdapter implements IFutureResult {
   Future<?> m_future;

   public FutureResultAdapter(Future<?> future) {
      m_future = future;
   }

  @Override
  public Object get() throws InterruptedException, ThreadExecutionException {
    try {
      return m_future.get();
    }
    catch(ExecutionException ee) {
      throw new ThreadExecutionException(ee.getCause());
    }
  }

  @Override
  public Object get(long timeOut, TimeUnit unit)
      throws InterruptedException, ThreadExecutionException, TimeoutException {
    try {
      return m_future.get(timeOut, unit);
    }
    catch (ExecutionException ee) {
      throw new ThreadExecutionException(ee.getCause()); // NOTE there is no
                                                         // need to keep the EE
    }
  }
}