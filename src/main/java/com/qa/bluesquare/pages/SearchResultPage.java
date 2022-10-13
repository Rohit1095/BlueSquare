package com.qa.bluesquare.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.bluesquare.utils.Constants;
import com.qa.bluesquare.utils.ElementUtil;


public class SearchResultPage {
	

	private WebDriver driver;
	private ElementUtil elementUtil;

	// 1. Constructor
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	// 2. By locators

	private By searchHeaderName = By.cssSelector("div#content h1");
	private By productResult = By.cssSelector("div.caption a");

	// 3. Action

	public String getSearchHeaderName() {
		return elementUtil.doGetText(searchHeaderName);
	}

	public int getSearchResultCount()
	{
		return elementUtil.waitForVisibilityOfElements(productResult, Constants.DEFAULT_TIME_OUT).size();
	}
	
	public ProductInfoPage selectProduct(String mainProduct)
	{
		
		List<WebElement> searchList=elementUtil.waitForVisibilityOfElements(productResult, Constants.DEFAULT_TIME_OUT);
		for(WebElement e: searchList)
		{
			if(e.getText().equals(mainProduct))
			{
				e.click();
				break;
			}
		}
		
		return new ProductInfoPage(driver);
		
		
		
		
	}
	



}
