package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.beust.jcommander.Parameter;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {
	
	protected WebDriver driver;
	/*
	 * You are restricting the access to all and only within the package and only child members 
	 * can access it. Obviously you are using inheritance concept here so all the Test classes
	 * should access base it hence protected is the access modifier that should be used here.
	 */
	protected Properties prop;
	DriverFactory df;
	protected LoginPage loginpage;
	protected AccountsPage accpage;
	protected SearchResultPage searchrespage;
	protected ProductInfoPage prodinfopage;
	protected RegisterPage registerpage;
	
	protected SoftAssert softassert;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
		
		if(browserName!= null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);
		loginpage = new LoginPage(driver);
		
		
		
		softassert = new SoftAssert();
		
//		df = new DriverFactory();
//		prop = df.initProp();
//		driver = df.initDriver(prop);//using call by reference 
		
//		driver = df.initDriver("chrome");
//		loginpage = new LoginPage(driver);
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
