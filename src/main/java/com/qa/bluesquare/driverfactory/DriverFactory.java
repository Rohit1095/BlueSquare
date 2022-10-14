package com.qa.bluesquare.driverfactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser");
		System.out.println("launched browser is " + browserName);

		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionsManager.getChromOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromOptions()));
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsManager.getFirfoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirfoxOptions()));
		}

		else {
			System.out.println("this application only run on chrome and firefox browser");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();

	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties init_Prop() {
		prop = new Properties();
		FileInputStream ip = null;
		String env = System.getProperty("env");
		if (env == null) {
			System.out.println("Running on production environment");
			try {
				ip = new FileInputStream("./src\\test.resources\\config\\prod.config.properties");
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
		}

		else {
			System.out.println("running on environment..." + env);

			try {
				switch ("env") {
				case "qa":
					ip = new FileInputStream("./src\\test.resources\\config\\qa.config.properties");
					break;

				case "stage":
					ip = new FileInputStream("./src\\test.resources\\config\\stage.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src\\test.resources\\config\\dev.config.properties");
					break;

				default:
					break;
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
		try {
			prop.load(ip);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return prop;
	}

}
