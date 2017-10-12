package com.parasoft.ft.utilities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

  @Override
  public void onTestFailure(ITestResult result) {
    System.out.println("***** Error " + result.getName() + " test has failed *****");
    Object currentClass = result.getInstance();
    WebDriver webDriver = ((BaseClass) currentClass).getDriver();

    if (webDriver != null) {
      new Utilities(webDriver)
          .takeScreenshot("target/" + currentClass.getClass().getName() + "." + result.getName()+".png");
    }


  }

  public void onFinish(ITestContext context) {
  }

  public void onTestStart(ITestResult result) {
  }

  public void onTestSuccess(ITestResult result) {
  }

  public void onTestSkipped(ITestResult result) {
  }

  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
  }

  public void onStart(ITestContext context) {
  }
}