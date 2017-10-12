package com.parasoft.ft.ObjectRepo;

import org.openqa.selenium.By;

public class HomePageRepo {
	
	protected By accountOverview=By.xpath("//a[contains(.,'Accounts Overview')]");
	protected By transferFunds=By.xpath("//a[contains(.,'Transfer Funds')]");
	protected By amount=By.xpath("//input[contains(@id,'amount')]");
	protected By transfer=By.xpath("//input[contains(@type,'submit')]");
	
	

}
