/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test.tmp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.IConfigurable;
import org.testng.IConfigureCallBack;
import org.testng.IPhaseListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestNG;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.internal.AnnotationTypeEnum;
import org.testng.phase.PhaseClassEvent;
import org.testng.phase.PhaseGroupEvent;
import org.testng.phase.PhaseMethodEvent;
import org.testng.phase.PhaseSuiteEvent;
import org.testng.phase.PhaseTestEvent;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

/**
 * ArquillianTestCase
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public class ArquillianTestCase
{
   @Test
   public void shouldVetoConfigurationMethods() throws Exception
   {
      XmlSuite suite = new XmlSuite();
      suite.setName("Arquillian");
      suite.setAnnotations(AnnotationTypeEnum.JDK.getName());

      XmlTest test = new XmlTest(suite);
      test.setName("Arquillian");
      List<XmlClass> testClasses = new ArrayList<XmlClass>();
      testClasses.add(new XmlClass(TestClass1.class));
      testClasses.add(new XmlClass(TestClass2.class));
      
      test.setXmlClasses(testClasses);

      
      TestNG runner = new TestNG(true);
      runner.setVerbose(0);
      runner.setJUnit(false);
      runner.setXmlSuites(
            Arrays.asList(suite));
      //runner.addListener(new DebugListener());
      runner.run();
   }

   public class TestClass 
   {
      @BeforeSuite
      public void beforeSuite() {
         System.out.println(printTab(0) + this.getClass().getSimpleName() + " beforeSuite");
      }
      
      @AfterSuite
      public void afterSuite() {
         System.out.println(printTab(0) + this.getClass().getSimpleName() + " afterSuite");
      }

      @BeforeClass
      public void beforeClass() {
         System.out.println(printTab(1) + this.getClass().getSimpleName() + " beforeClass");
      }
      
      @AfterClass
      public void afterClass() {
         System.out.println(printTab(1) + this.getClass().getSimpleName() + " afterClass");
      }

      @BeforeMethod
      public void beforeMethod() {
         System.out.println(printTab(2) + this.getClass().getSimpleName() + " beforeMethod");
      }
      
      @AfterMethod
      public void afterMethod() {
         System.out.println(printTab(2) + this.getClass().getSimpleName() + " afterMethod");
      }

      @Test
      public void test1() {
         System.out.println(printTab(3) + this.getClass().getSimpleName() + " test1");
      }

      @Test
      public void test2() {
         System.out.println(printTab(3) + this.getClass().getSimpleName() + " test2");
      }
   }
   
   @Listeners(DebugListener.class)
   public class TestClass1 extends TestClass 
   {
      
   }

//   @Listeners(DebugListener.class)
   public class TestClass2 extends TestClass 
   {
      
   }

   
   public static class DebugListener implements IPhaseListener, IConfigurable
   {
      public void run(IConfigureCallBack callBack, ITestResult testResult)
      {
         System.out.println(printTab(testResult) + "IConfigurable: " + testResult.getMethod().getMethod().getName());
         callBack.runConfigurationMethod(testResult);
      }
      
      public void onSuiteEvent(PhaseSuiteEvent event)
      {
         System.out.println(printTab(0) + "onSuiteEvent: " + event.isBefore()
             + "\n" + event.getXmlSuite().toXml());
      }

      public void onTestEvent(PhaseTestEvent event)
      {
         //System.out.println("onTestEvent: " + event.isBefore() + " " + event.getTestClass().getRealClass().getSimpleName());
      }

      public void onGroupEvent(PhaseGroupEvent event)
      {
         //System.out.println("onGroupEvent: " + event.isBefore() + " " + event.getTestClass().getRealClass().getSimpleName());         
      }

      public void onClassEvent(PhaseClassEvent event)
      {
         System.out.println(printTab(1) + "onClassEvent: " + event.isBefore()
             + " " + event.getName());
      }

      public void onMethodEvent(PhaseMethodEvent event)
      {
         System.out.println(printTab(2) + "onMethodEvent: " + event.isBefore() 
             + " " + event.getName() + " " + event.getMethod().getMethodName());
      }
   }

   private static String printTab(int count)
   {
      StringBuilder sb = new StringBuilder();
      for(int i = 0; i < count; i++)
      {
         sb.append('\t');
      }
      return sb.toString();
   }
   
   private static String printTab(ITestResult result)
   {
      ITestNGMethod method = result.getMethod();
      if(method.isBeforeSuiteConfiguration() || method.isAfterSuiteConfiguration())
         return printTab(0);
      if(method.isBeforeClassConfiguration() || method.isAfterClassConfiguration())
         return printTab(1);
      if(method.isBeforeMethodConfiguration() || method.isAfterMethodConfiguration())
         return printTab(2);
      
      return printTab(0);
   }
}