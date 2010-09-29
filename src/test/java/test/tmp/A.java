package test.tmp;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Test(sequential = true)
@Listeners(MyPhaseListener.class)
public class A {

//  @BeforeSuite
  public void bs() {
    System.out.println("bs");
  }

  @BeforeMethod
  public void bm() {
    System.out.println("Before method");
  }

  @AfterMethod
  public void am() {
    System.out.println("After method");
  }
  //  @Factory
  public Object[] f() {
    return new Object[] {
        new A(),
        new A()
    };
  }

  @Test(groups="a")
  public void a3() {
    System.out.println("a3");
  }

//  @BeforeClass(timeOut = 1000)
  public void bc() throws InterruptedException {
    System.out.println("bc");
//    Thread.sleep(2000);
  }

//  @Test(timeOut = 1000)
  public void a1() throws InterruptedException {
//    Thread.sleep(2000);
//    throw new SkipException("skipped");
  }

  @Test
  public void a2() {
    throw new RuntimeException("We have a problem");
//    System.out.println("a2 " + Thread.currentThread().getId());
  }
}

