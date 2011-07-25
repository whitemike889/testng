package test.timeout;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * This class tests timeouts
 *
 * @author cbeust
 */
public class TimeOutSampleTest {

  @BeforeMethod
  public void bm() {
    System.out.println("before method " + Thread.currentThread().getId());
  }

  @Test(timeOut = 1000 /* 1 second */)
  public void timeoutShouldPass() {
    System.out.println("Should pass " + Thread.currentThread().getId());
  }

  @Test(timeOut = 1 * 1000 /* 1 second */)
  public void timeoutShouldFailByException() {
    throw new RuntimeException("EXCEPTION SHOULD MAKE THIS METHOD FAIL");
  }

  @Test(timeOut = 1000 /* 1 second */)
  public void timeoutShouldFailByTimeOut() throws InterruptedException {
      Thread.sleep(10 * 1000 /* 10 seconds */);
  }

  public static void ppp(String s) {
    System.out.println("[TimeOutTest]@@@@@@@@@@@@@@@ " + s);
  }
}
