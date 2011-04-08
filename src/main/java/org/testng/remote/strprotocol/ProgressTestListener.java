package org.testng.remote.strprotocol;

import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.collections.Sets;
import org.testng.internal.IResultListener;
import org.testng.xml.XmlTest;

import java.util.Set;

/**
 * A special listener that remote the event with string protocol.
 *
 * @author <a href='mailto:the_mindstorm[at]evolva[dot]ro'>Alexandru Popescu</a>
 */
public class ProgressTestListener implements IResultListener {
  private final MessageHub m_sender;
  private ISuite m_suite;
  private XmlTest m_xmlTest;
  private ITestContext m_currentTestContext;
  private int m_percent;
  private int m_suiteCount;
  private int m_testCount;
  private Set<String> m_xmlTestsRun = Sets.newHashSet();

  public ProgressTestListener(ISuite suite, XmlTest test, MessageHub msh, int suiteCount,
      int testCount) {
    m_sender = msh;
    m_suite= suite;
    m_xmlTest= test;
    m_suiteCount = suiteCount;
    m_testCount = testCount;
  }

  @Override
  public void onStart(ITestContext testCtx) {
//    m_currentTestContext = testCtx;
//    m_sender.sendMessage(new TestMessage(testCtx, true /*start*/));
  }

  @Override
  public void onFinish(ITestContext testCtx) {
//    m_sender.sendMessage(new TestMessage(testCtx, false /*end*/));
//    m_currentTestContext = null;
  }

  @Override
  public void onTestStart(ITestResult testResult) {
//    TestResultMessage trm= null;
//
//    if (null == m_currentTestContext) {
//      trm= new TestResultMessage(m_suite.getName(), m_xmlTest.getName(), testResult);
//    }
//    else {
//      trm= new TestResultMessage(m_currentTestContext, testResult);
//    }
//
//    m_sender.sendMessage(trm);
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult testResult) {
//    if (null == m_currentTestContext) {
//      m_sender.sendMessage(new TestResultMessage(m_suite.getName(), m_xmlTest.getName(), testResult));
//    }
//    else {
//      m_sender.sendMessage(new TestResultMessage(m_currentTestContext, testResult));
//    }
  }

  @Override
  public void onTestFailure(ITestResult testResult) {
//    if (null == m_currentTestContext) {
//      m_sender.sendMessage(new TestResultMessage(m_suite.getName(), m_xmlTest.getName(), testResult));
//    }
//    else {
//      m_sender.sendMessage(new TestResultMessage(m_currentTestContext, testResult));
//    }
  }

  @Override
  public void onTestSkipped(ITestResult testResult) {
//    if (null == m_currentTestContext) {
//      m_sender.sendMessage(new TestResultMessage(m_suite.getName(), m_xmlTest.getName(), testResult));
//    }
//    else {
//      m_sender.sendMessage(new TestResultMessage(m_currentTestContext, testResult));
//    }
  }

  @Override
  public void onTestSuccess(ITestResult testResult) {
    String testName = testResult.getMethod().getXmlTest().getName();
    if (! m_xmlTestsRun.contains(testName)) {
      m_xmlTestsRun.add(testName);
      m_sender.sendMessage(new ProgressMessage(1, m_testCount));
      System.out.println("Sending progress: " + testName);
    }
  }

  /**
   * @see org.testng.internal.IConfigurationListener#onConfigurationFailure(org.testng.ITestResult)
   */
  @Override
  public void onConfigurationFailure(ITestResult itr) {
    onTestFailure(itr);
  }

  /**
   * @see org.testng.internal.IConfigurationListener#onConfigurationSkip(org.testng.ITestResult)
   */
  @Override
  public void onConfigurationSkip(ITestResult itr) {
    onTestSkipped(itr);
  }

  /**
   * @see org.testng.internal.IConfigurationListener#onConfigurationSuccess(org.testng.ITestResult)
   */
  @Override
  public void onConfigurationSuccess(ITestResult itr) {
  }
}