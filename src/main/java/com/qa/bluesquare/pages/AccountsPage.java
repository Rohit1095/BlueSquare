package com.qa.bluesquare.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.bluesquare.utils.Constants;
import com.qa.bluesquare.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	// 1. Constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	// 2. By locators

	private By accountLogo = By.xpath("//img[@title='naveenopencart']");
	private By accSection = By.xpath("//div[@id='content']//h2");
	private By footerNote = By.xpath("//p[contains(text(),'Powered By ')]");
	private By logoutLink = By.linkText("Logout");
	private By searchField = By.xpath("//input[@name='search']");
	private By searchButton = By.xpath("//span[@class='input-group-btn']/button");

	// 3. actions

	public boolean isLogoutLinkExist() {
		return elementUtil.doIsDisplayed(logoutLink);
	}

	public void doLogout() {
		if (isLogoutLinkExist())
			elementUtil.doClick(logoutLink);
	}

	public boolean isAccLogoDisplayed() {
		return elementUtil.doIsDisplayed(accountLogo);
	}

	public String getAccPageTitle() {
		return elementUtil.getPageTitle(Constants.ACCOUNT_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public boolean getAccPageUrl() {
		return elementUtil.waitForURL("route-count", Constants.DEFAULT_TIME_OUT);
	}

	public List<String> getAccSectionList() {
		List<String> accSecValList = new ArrayList<String>();
		List<WebElement> accSecList = elementUtil.waitForVisibilityOfElements(accSection, Constants.DEFAULT_TIME_OUT);

		for (WebElement e : accSecList) {
			String text = e.getText();
			accSecValList.add(text);
		}
		return accSecValList;
	}

	public SearchResultPage doSearch(String productName) {
		System.out.println("Searching product  " + productName);
		elementUtil.doSendKeys(searchField, productName);
		elementUtil.doClick(searchButton);
		return new SearchResultPage(driver);
	}

	public String getAccFooterNote() {
		return elementUtil.doGetText(footerNote);
	}


}
