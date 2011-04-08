package org.testng.remote;

import com.beust.jcommander.Parameter;

public class RemoteArgs {
  public static final String PORT = "-serport";
  @Parameter(names = PORT, description = "The port for the serialization protocol")
  public Integer serPort;

  public static final String DONT_EXIT= "-dontexit";
  @Parameter(names = DONT_EXIT, description = "Do not exit the JVM once done")
  public boolean dontExit = false;

  public static final String PROTOCOL_VERSION = "-protocolversion";
  @Parameter(names = PROTOCOL_VERSION, description = "Version of the protocol")
  public int protocolVersion = 1;
}
