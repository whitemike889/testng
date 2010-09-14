package test.tmp;

import org.testng.IPhaseListener;
import org.testng.phase.PhaseClassEvent;
<<<<<<< HEAD
=======
import org.testng.phase.PhaseGroupEvent;
>>>>>>> 01bf34b4065ee05d6ed0f6fdf160613134e28ba8
import org.testng.phase.PhaseMethodEvent;
import org.testng.phase.PhaseSuiteEvent;
import org.testng.phase.PhaseTestEvent;

public class MyPhaseListener implements IPhaseListener {
  private static boolean m_verbose;

  private static void p(String s) {
    if (m_verbose) {
      System.out.println("[MyPhaseListener] " + s);
    }
  }
  @Override
  public void onSuiteEvent(PhaseSuiteEvent event) {
    p("Suite event:" + event);
  }

  @Override
  public void onTestEvent(PhaseTestEvent event) {
    p("Test event:" + event);
  }

  @Override
  public void onClassEvent(PhaseClassEvent event) {
    p("Class event:" + event);
  }

  @Override
  public void onMethodEvent(PhaseMethodEvent event) {
    p("Method event:" + event);
  }
<<<<<<< HEAD
=======
  @Override
  public void onGroupEvent(PhaseGroupEvent event) {
    p("Group event:" + event);
  }
>>>>>>> 01bf34b4065ee05d6ed0f6fdf160613134e28ba8

}
