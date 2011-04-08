package org.testng.remote.strprotocol;

public class ProgressMessage implements IMessage {

  private int m_testCount;
  private int m_totalTestCount;

  /**
   * @param testCount The number of tests that were just run (usually 1).
   * @param totalTestCount The total count.
   */
  public ProgressMessage(int testCount, int totalTestCount) {
    m_testCount = testCount;
    m_totalTestCount = totalTestCount;
  }

  public int getTotalTestCount() {
    return m_totalTestCount;
  }

  public int getTestCount() {
    return m_testCount;
  }
}
