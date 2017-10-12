package com.parasoft.ft.PageObjectClasses;

import com.parasoft.ft.ObjectRepo.LoginPageObjRepo;
import com.parasoft.ft.utilities.Utilities;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends LoginPageObjRepo {
	WebDriver driver;
	WebDriverWait webDriverWait;
	
	//webDriverWait = new WebDriverWait(driver, 3,100);
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		
	}

	public HomePage Login(Map<String, String> map) {
		Utilities util= new Utilities(driver);
		//util.LoginInfo(map);
		util.fillInTextInfo(username,map.get("username"));
		util.fillInTextInfo(password,map.get("password"));
		util.click(submit);
		return new HomePage(driver);
	}

}
