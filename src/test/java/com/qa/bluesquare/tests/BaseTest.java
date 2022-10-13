package com.qa.bluesquare.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.bluesquare.driverfactory.DriverFactory;
import com.qa.bluesquare.pages.AccountsPage;
import com.qa.bluesquare.pages.LoginPage;
import com.qa.bluesquare.pages.ProductInfoPage;
import com.qa.bluesquare.pages.RegistrationPage;
import com.qa.bluesquare.pages.SearchResultPage;


public class BaseTest {
	
	public DriverFactory df;
	public Properties prop;
	public WebDriver driver;
	public LoginPage loginPage;
	public AccountsPage accPage;
	public SearchResultPage searchResultPage;
	public ProductInfoPage productInfoPage;
	public RegistrationPage registrationPage;

	public SoftAssert softAssert;

	@Parameters("browser")
	@BeforeTest
	public void setUp(String browserName) {
		df = new DriverFactory();
		prop = df.init_Prop();
		if(browserName!=null)
		{
			prop.setProperty("browser", browserName);
		}
		
		driver = df.init_driver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}


}
