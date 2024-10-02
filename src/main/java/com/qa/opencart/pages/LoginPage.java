package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;//if you make it public then if anyone can access it then they get null pointer exception 
	private ElementUtil eleUtil;
	
	//By locators: Object Repository 
	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@class='btn btn-primary']");
	private By forgotPwd = By.linkText("Forgotten Password");
	private By logo = By.xpath("//img[@title='naveenopencart']");
	
	private By registerLink = By.linkText("Register");
	
	
	
	//page const..
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//page actions/methods:
	public String getLoginPageTitle() {
		
//		String title = eleUtil.waitForTitleIs("Account Login", 5);
//		System.out.println("login page title:" + title);
//		return title;
		
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TILE , AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("login page title is:" + title);
		return title;
		
		//this represents normal code 
//		String title = driver.getTitle();
//		System.out.println("Login page title is : " + title);
//		return title;
	}
	
	public String getLoginPageUrl() {
		
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION , AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("The login page URL is:"+ url);
		return url;
		
//		String url = driver.getCurrentUrl();
//		System.out.println("Login page URL is : " + url);
//		return url;
	}
	
	public boolean isForgotPwdLinkExist() {
		
		return eleUtil.waitForVisibilityOfElement(forgotPwd, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
		
//		driver.findElement(forgotPwd).isDisplayed();
	}
	
	public boolean isLogoExist() {

		return eleUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
		
//		return driver.findElement(logo).isDisplayed();
	}
	
	//After login it will land on accounts page
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("creds are:"+ username +":" + pwd);
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
				
//		driver.findElement(userName).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
//		System.out.println("User is logged in...");
//		return true;
	}
	
	//Will navigate to register page 
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.SHORT_DEFAULT_WAIT);
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);// here we use page chaining approcach and it is called TDD
	}

	
	

}
