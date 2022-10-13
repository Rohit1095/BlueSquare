package com.qa.bluesquare.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.bluesquare.utils.Constants;
import com.qa.bluesquare.utils.ElementUtil;


public class RegistrationPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;

	// 1. Constructor
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	// 2. By locators
	private By firstName = By.cssSelector("input#input-firstname");
	private By lastName = By.cssSelector("input#input-lastname");
	private By email = By.name("email");
	private By telephone = By.xpath("//input[@type='tel']");
	private By password = By.cssSelector("input#input-password");
	private By confirmPassword = By.cssSelector("input#input-confirm");
	private By subscribeYes = By.xpath("(//label[@class='radio-inline']/input)[1]");
	private By subscriberNo = By.xpath("(//label[@class='radio-inline']/input)[2]");
	private By iAgree = By.xpath("//input[@name='agree']");
	private By continue_Btn = By.xpath("//input[@type='submit']");
	private By successMsg = By.cssSelector("div#content h1");
	private By logout = By.linkText("Logout");
	private By register = By.linkText("Register");

	// 3. page actions

	public boolean accountRegistrationData(String firstName, String lastName, String email, String phone, String password,
			String subscribe) {
		elementUtil.doSendKeys(this.firstName, firstName);
		elementUtil.doSendKeys(this.lastName, lastName);
		elementUtil.doSendKeys(this.email, email);
		elementUtil.doSendKeys(this.telephone, phone);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.confirmPassword, password);
		if (subscribe.equalsIgnoreCase("yes")) {
			elementUtil.doClick(subscribeYes);
		} else {
			elementUtil.doClick(subscriberNo);
		        }
		
		elementUtil.doClick(iAgree);
		elementUtil.doClick(continue_Btn);
		String msg=elementUtil.doVisibilityOfElement(successMsg, Constants.DEFAULT_TIME_OUT).getText();
		if(msg.contains(Constants.SUCCESS_MSG))
		{
			elementUtil.doClick(logout);
			elementUtil.doClick(register);
			
			return true;
		}
		return false;
	}


}
