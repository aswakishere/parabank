package com.parasoft.ft.PageObjectClasses;

import com.parasoft.ft.ObjectRepo.HomePageRepo;
import com.parasoft.ft.utilities.Utilities;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class HomePage extends HomePageRepo {

  WebDriver driver;

  public HomePage(WebDriver driver) {
    this.driver = driver;
  }

  public void clickOnAccountsOverview() {

    Wait<WebDriver> wait = new FluentWait<>(driver)
        //Wait for the condition
        .withTimeout(10, TimeUnit.SECONDS)
        // which to check for the condition with interval of 5 seconds.
        .pollingEvery(5, TimeUnit.SECONDS)
        //Which will ignore the NoSuchElementException
        .ignoring(NoSuchElementException.class)
        //will ignore illegal state Exception
        .ignoring(IllegalStateException.class)
        //send out string messages
        .withMessage("will wait 5 secs");
    wait.until(ExpectedConditions.presenceOfElementLocated(accountOverview)).click();

  }

  public void transferFunds(Map<String, String> map) {

    Wait<WebDriver> wait = new FluentWait<>(driver)
        //Wait for the condition
        .withTimeout(10, TimeUnit.SECONDS)
        // which to check for the condition with interval of 5 seconds.
        .pollingEvery(5, TimeUnit.SECONDS)
        //Which will ignore the NoSuchElementException
        .ignoring(NoSuchElementException.class)
        //will ignore illegal state Exception
        .ignoring(IllegalStateException.class)
        //send out string messages
        .withMessage("will wait 5 secs");
    wait.until(ExpectedConditions.presenceOfElementLocated(transferFunds)).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(amount)).sendKeys(map.get("amount"));
    new Utilities(driver).takeScreenshot("target/transferFunds.png");
    wait.until(ExpectedConditions.presenceOfElementLocated(transfer)).click();

  }


}
