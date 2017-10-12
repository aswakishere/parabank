package com.parasoft.ft;

import com.parasoft.ft.PageObjectClasses.HomePage;
import com.parasoft.ft.PageObjectClasses.LoginPage;
import com.parasoft.ft.utilities.BaseClass;
import com.parasoft.ft.utilities.Utilities;
import java.net.MalformedURLException;
import java.util.Map;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AccountsOverViewAndTransferFunds extends BaseClass {

  LoginPage loginPage;
  HomePage homePage;

  @Parameters({"browser", "endRow", "startRow", "csvFile"})
  public AccountsOverViewAndTransferFunds(String browser, int endRow, int startRow,
      String csvFile) {
    super(browser, endRow, startRow, csvFile);
  }


  @Test(dataProvider = "csvData")
  public void createNewAccountFromHomePage(Map<String, String> map) {
    loginPage = navigateToApplication();
    homePage = loginPage.Login(map);
    homePage.clickOnAccountsOverview();
    homePage.transferFunds(map);
    CloseBrowser();

  }


}
