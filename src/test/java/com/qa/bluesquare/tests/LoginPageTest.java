package com.qa.bluesquare.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.bluesquare.utils.Constants;


public class LoginPageTest extends BaseTest{
	
	@Test
	public void loginPageTitleTest()
	{
		String actualTitle=loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void forgetPasswordLinkTest() {

	Assert.assertTrue(loginPage.isForgetPasswordLinkExist());
}
	
	@Test
	public void loginTest() 
	{
		loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test
	public void loginPageFooterLinksCountTest()
	{
		int size=loginPage.getFooterList().size();
		Assert.assertEquals(size, 15);
	}
	
	@Test
	public void loginPageFooterContainsTest()
	{
		Assert.assertTrue(loginPage.getFooterList().contains("Returns"));
	}

}
