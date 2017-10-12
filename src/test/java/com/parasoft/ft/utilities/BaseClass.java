package com.parasoft.ft.utilities;

import au.com.bytecode.opencsv.CSVReader;
import com.parasoft.ft.PageObjectClasses.LoginPage;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;

public class BaseClass {

  WebDriver driver;
  String browser;
  int endRow;
  int startRow;
  String csvFile;
  PropertiesManager propertiesManager = PropertiesManager.PROPERTIES;

  public BaseClass(String browser) {
    this.browser = browser;
  }

  public BaseClass(String browser, int endRow, int startRow, String csvFile) {
    this.browser = browser;
    this.endRow = endRow;
    this.startRow = startRow;
    this.csvFile = csvFile;
  }

  public LoginPage navigateToApplication() {
    driver = withDriver(browser);
    // IMPLICT WAIT
    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

    driver.navigate().to(propertiesManager.getProperty("env_url"));
    return new LoginPage(driver);

  }

  public void OpenGoogle(){
    driver = withDriver(browser);
    driver.get("http://www.google.com");
    new Utilities(driver).takeScreenshot("target/screenshot1.png");
  }
  public void CloseBrowser() {

    driver.close();

  }

  @DataProvider(name = "csvData")
  public Object[][] provideCSVData() {
    Object[][] testNgData = null;
    try {
      CSVReader reader = new CSVReader(new FileReader(csvFile));
      String[] headers = reader.readNext();
      String[] inputLine = null;
      try {
        int counter = 1;
        int testNgDataIndex = 0;
        testNgData = new Object[endRow - startRow + 1][1];
        while ((inputLine = reader.readNext()) != null) {
          if (startRow <= counter && endRow >= counter) {
            Map<String, String> csvMap = new HashMap<String, String>();
            for (int index = 0; index < inputLine.length; index++) {
              csvMap.put(headers[index], inputLine[index]);
            }
            testNgData[testNgDataIndex++][0] = csvMap;
          }
          counter++;
        }
      } catch (IOException ioe) {
        System.out.println(ioe);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return testNgData;
  }

  public WebDriver withDriver(String browser)  {
    System.out.println("Create driver with browser " + browser);
    WebDriver driver = null;
    switch (browser) {
      case "chrome":
        driver = new ChromeDriver();
        break;
      case "chrome-remote":
        try {
          driver = new RemoteWebDriver(
              new URL(propertiesManager.getProperty("webdriver_url")),
              DesiredCapabilities.chrome());
        } catch (MalformedURLException e) {
          e.printStackTrace();
        }
        break;
      default:
        break;
    }
    return driver;
  }

  public WebDriver getDriver() {
    return driver;
  }
}
