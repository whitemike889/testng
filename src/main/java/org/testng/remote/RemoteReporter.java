package org.testng.remote;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.remote.strprotocol.MessageHub;
import org.testng.remote.strprotocol.ReportMessage;
import org.testng.xml.XmlSuite;

import java.util.List;

public class RemoteReporter implements IReporter {

  private MessageHub m_messageHub;

  public RemoteReporter(MessageHub msh) {
    m_messageHub = msh;
  }

  @Override
  public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
      String outputDirectory)
  {
    System.out.println("[RemoteReporter] Sending report message:" + suites.size());
    m_messageHub.sendMessage(new ReportMessage(xmlSuites, suites));
  }

}
