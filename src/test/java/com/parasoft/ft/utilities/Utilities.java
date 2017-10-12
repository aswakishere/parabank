package com.parasoft.ft.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {

  WebDriver driver;
  WebDriverWait webDriverWait;

  public Utilities(WebDriver driver) {
    this.driver = driver;

  }

  public void fillInTextInfo(By path, String SomeString) {
    webDriverWait = new WebDriverWait(driver, 3, 100);
    webDriverWait.until(ExpectedConditions.presenceOfElementLocated(path)).sendKeys(SomeString);
  }

  public void click(By path) {
    webDriverWait = new WebDriverWait(driver, 3, 100);
    webDriverWait.until(ExpectedConditions.presenceOfElementLocated(path)).click();
  }

  public void LoginInfo(Map<String, String> map) {
    // TODO Auto-generated method stub

  }

  public void takeScreenshot(String screenshotPath) {
    // RemoteWebDriver does not implement the TakesScreenshot class
    // if the driver does have the Capabilities to take a screenshot
    // then Augmenter will add the TakesScreenshot methods to the instance
    WebDriver augmentedDriver = new Augmenter().augment(driver);
    File screenshot = ((TakesScreenshot) augmentedDriver).
        getScreenshotAs(OutputType.FILE);
    try {
      FileUtils.copyFile(screenshot, new File(screenshotPath));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
