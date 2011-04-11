package org.testng.remote.strprotocol;

import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.collections.Lists;
import org.testng.xml.XmlSuite;

import java.util.List;
import java.util.Map.Entry;

public class ReportMessage implements IMessage {
  private static final long serialVersionUID = 1L;

  private List<TestResultMessage> m_failed = Lists.newArrayList();
  private List<TestResultMessage> m_passed = Lists.newArrayList();
  private List<TestResultMessage> m_skipped = Lists.newArrayList();
  
//  private List<ITestNGMethod> m_methods = Lists.newArrayList();

  public ReportMessage(List<XmlSuite> xmlSuites, List<ISuite> suites) {
    for (ISuite s : suites) {
      for (Entry<String, ISuiteResult> es : s.getResults().entrySet()) {
        ISuiteResult sr = es.getValue();
        ITestContext tc = sr.getTestContext();
        addResults(m_failed, tc.getFailedTests(), tc);
        addResults(m_skipped, tc.getSkippedTests(), tc);
        addResults(m_passed, tc.getPassedTests(), tc);
      }
    }
  }

  private void addResults(List<TestResultMessage> outMessages, IResultMap result, ITestContext tc) {
    for (ITestResult tr : result.getAllResults()) {
      outMessages.add(new TestResultMessage(tc, tr));
    }
  }

  public List<TestResultMessage> getPassed() {
    return m_passed;
  }

  public List<TestResultMessage> getFailed() {
    return m_failed;
  }

  public List<TestResultMessage> getSkipped() {
    return m_skipped;
  }
}
