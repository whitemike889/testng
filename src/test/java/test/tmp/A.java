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

//  @BeforeClass
  public void bc() {
    System.out.println("before");
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

  @Test
  public void a1() {
    System.out.println("a1 throwing");
//    throw new RuntimeException();
//    System.out.println("a1 " + Thread.currentThread().getId());
  }

//  @Test
  public void a2() {
    System.out.println("a2 " + Thread.currentThread().getId());
  }
}
