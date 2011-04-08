package org.testng.remote.strprotocol;

import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.collections.Lists;
import org.testng.xml.XmlSuite;

import java.util.List;
import java.util.Map.Entry;

public class ReportMessage implements IMessage {
  private List<XmlSuite> m_xmlSuites;
//  private Map<String, Collection<TestResultMessage>> m_passed = Maps.newHashMap();
  private List<TestResultMessage> m_passed = Lists.newArrayList();
  
//  private List<ITestNGMethod> m_methods = Lists.newArrayList();

  public ReportMessage(List<XmlSuite> xmlSuites, List<ISuite> suites) {
    m_xmlSuites = xmlSuites;
    for (ISuite s : suites) {
      for (Entry<String, ISuiteResult> es : s.getResults().entrySet()) {
        ISuiteResult sr = es.getValue();
        ITestContext tc = sr.getTestContext();
//        List<ITestNGMethod> l = Lists.newArrayList();
//        l.addAll(tc.getPassedTests().getAllMethods());
//        m_passed.put(es.getKey(), l);
        for (ITestResult tr : tc.getPassedTests().getAllResults()) {
          TestResultMessage trm = new TestResultMessage(tc, tr);
          m_passed.add(trm);
        }
      }
    }
  }

  public List<TestResultMessage> getPassed() {
    return m_passed;
  }

}
