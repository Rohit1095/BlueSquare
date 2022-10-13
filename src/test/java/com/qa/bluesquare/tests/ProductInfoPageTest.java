package com.qa.bluesquare.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.bluesquare.utils.Constants;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void accountPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	public void productImageCountTest()
	{
		searchResultPage=accPage.doSearch("iMac");
		productInfoPage=searchResultPage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImagesCount(), Constants.IMAC_IMAGE_COUNT);
	}

	@Test()
	public void productInfoTest()
	{
	searchResultPage=accPage.doSearch("iMac");
	productInfoPage=searchResultPage.selectProduct("iMac");
	Map<String, String>prodData=productInfoPage.getProductInfo();
	//prodData.forEach((k,v)->System.out.println(k+" : "+v));
	
	softAssert.assertEquals(prodData.get("Name"), "iMac");
	softAssert.assertEquals(prodData.get("Availability"), " In Stock");
	softAssert.assertEquals(prodData.get("Price"), "$122.00");
	softAssert.assertEquals(prodData.get("Product Code"), " Product 14");
	softAssert.assertAll();
	
	}
	
	
	


}
