package com.qa.bluesquare.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.bluesquare.utils.Constants;
import com.qa.bluesquare.utils.ElementUtil;


public class ProductInfoPage {


	private WebDriver driver;
	private ElementUtil elementUtil;
	public Map<String, String> productInfoMap;

	// 1. Constructor
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	// 2. By locators

	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.xpath("//ul[@class='thumbnails']//a/img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addToCart = By.id("button-cart");
	private By cartTotal=By.xpath("//span[@id='cart-total']");
	private By successMsg = By.cssSelector("div.alert.alert-success.alert-dismissible");

	// 3. Actions
	public String getProductHeaderText() {
		return elementUtil.doGetText(productHeader);
	}

	public int getProductImagesCount() {
		return elementUtil.waitForVisibilityOfElements(productImages, Constants.DEFAULT_TIME_OUT).size();
	}

	public Map<String, String> getProductInfo() {
		productInfoMap = new LinkedHashMap<String, String>();
		productInfoMap.put("Name", getProductHeaderText());
		productMetaData();
		prodPriceData();
		return productInfoMap;
	}

	private void prodPriceData() {
		// price
		List<WebElement> priceData = elementUtil.getElements(productPriceData);
		String prodPrice = priceData.get(0).getText().trim();
		String[] prodExTaxPrice = priceData.get(1).getText().split(":");
		//productInfoMap.put(prodExTaxPrice[0], prodExTaxPrice[1]);
	//	String prodExTax=priceData.get(1).getText().trim();
		productInfoMap.put("Price", prodPrice);
		productInfoMap.put(prodExTaxPrice[0], prodExTaxPrice[1]);	

	}

	private void productMetaData() {

		List<WebElement> productDataList = elementUtil.getElements(productMetaData);
		for (WebElement e : productDataList) {
			String[] meta = e.getText().split(":");
			String metaKey = meta[0];
			String metaValue = meta[1];
			productInfoMap.put(metaKey, metaValue);
		}
	}


}
