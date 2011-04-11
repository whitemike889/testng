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
  private int m_testCount;
  private XmlTest m_xmlTest;

  public ProgressTestListener(ISuite suite, XmlTest test, MessageHub msh, int suiteCount,
      int testCount) {
    m_sender = msh;
    m_xmlTest= test;
    m_testCount = testCount;
  }

  @Override
  public void onStart(ITestContext testCtx) {
  }

  @Override
  public void onFinish(ITestContext testCtx) {
    sendProgress(testCtx);
  }

  @Override
  public void onTestStart(ITestResult testResult) {
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult testResult) {
    m_methodCount++;
  }

  @Override
  public void onTestFailure(ITestResult testResult) {
    m_methodCount++;
  }

  @Override
  public void onTestSkipped(ITestResult testResult) {
    m_methodCount++;
  }

  @Override
  public void onTestSuccess(ITestResult testResult) {
    m_methodCount++;
  }

  private int m_methodCount = 0;

  private void sendProgress(ITestContext context) {
    m_sender.sendMessage(new ProgressMessage(m_xmlTest.getName(), m_testCount, m_methodCount));
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