package com.qa.bluesquare.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.bluesquare.utils.Constants;
import com.qa.bluesquare.utils.ElementUtil;


public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;

	// 1 Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	// 2 Page Locators
	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By login_btn =By.xpath("//input[@value='Login']");
	private By forgetPassword = By.linkText("Forgotten Password");
	private By fotterList=By.xpath("//footer//div[@class='row']//a");
	private By registration=By.linkText("Register");

	// 3 page actions

	public RegistrationPage navigateToRegistrationPage()
	{
		elementUtil.doClick(registration);
		return new RegistrationPage(driver);
	}
	
	public String getLoginPageTitle() {
		return elementUtil.getPageTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public boolean isForgetPasswordLinkExist() {
		return elementUtil.doIsDisplayed(forgetPassword);
	}
	
	public AccountsPage doLogin(String userName, String pwd)
	{
	 elementUtil.doPresenceOfElementLocated(email, Constants.DEFAULT_TIME_OUT).sendKeys(userName);
	 elementUtil.doSendKeys(password, pwd);
	 elementUtil.doClick(login_btn);
	 return new AccountsPage(driver);
	}
	
	public List<String> getFooterList()
	{
		return elementUtil.getElementsTextList(fotterList);
	}


}
