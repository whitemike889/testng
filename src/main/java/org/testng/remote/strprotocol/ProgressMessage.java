package org.testng.remote.strprotocol;

public class ProgressMessage implements IMessage {

  private static final long serialVersionUID = 1L;

  private String m_testName;
  private int m_totalTestCount;
  private int m_methodCount;

  /**
   * @param testCount The number of tests that were just run (usually 1).
   * @param totalTestCount The total count.
   */
  public ProgressMessage(String testName, int totalTestCount, int methodCount) {
    m_testName = testName;
    m_totalTestCount = totalTestCount;
    m_methodCount = methodCount;
  }

  public int getTotalTestCount() {
    return m_totalTestCount;
  }

  public String getTestName() {
    return m_testName;
  }

  public int getMethodCount() {
    return m_methodCount;
  }
}
