package com.qa.bluesquare.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.bluesquare.utils.Constants;
import com.qa.bluesquare.utils.ExcelUtil;


public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void registrationSetUp() {
		registrationPage = loginPage.navigateToRegistrationPage();
	}
	
	
	public static String randomEmailGenerator()
	{
		Random r= new Random();
		String email="testautomation"+r.nextInt(1000)+"@gmail.com";
		return email;
	}

	@DataProvider
	public Object[][] registrationData() {
		return ExcelUtil.getRegistrationData(Constants.REGISTRATION_DATA);
	}

	@Test(dataProvider = "registrationData")
	public void userRegistrationTest(String firstName, String lastName, String phone, String password,
			String subscribe) {
		Assert.assertTrue(
				registrationPage.accountRegistrationData(firstName, lastName, randomEmailGenerator(), phone, password, subscribe));

	}


	
}
