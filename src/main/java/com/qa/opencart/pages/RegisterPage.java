package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[1]/input[@value ='1']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[2]/input[@value ='0']");
	
	private By agreecheckbox = By.xpath("//input[@name='agree' and @type='checkbox']");
	private By continueBtn = By.xpath("//input[@type='submit' and @value='Continue']");
	
	private By registerLink = By.linkText("Register");
	private By successMessage = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By logoutMessage = By.xpath("//h1[text()='Account Logout']");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public boolean userRegistration(String firstName,String lastName,String email,
			String telephone, String password, String subscribe) {
		
		eleUtil.waitForVisibilityOfElement(this.firstname, AppConstants.LONG_DEFAULT_WAIT).sendKeys(firstName);
		
		eleUtil.doSendKeys(this.lastname, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}else {
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doClick(agreecheckbox);
		eleUtil.doClick(continueBtn);
		
		String successmsg = eleUtil.waitForVisibilityOfElement(successMessage, AppConstants.MEDIUM_DEFAULT_WAIT).getText();
		System.out.println(successmsg);
		
		if(successmsg.contains(AppConstants.USER_REGISTER_SUCCESS_MESSAGE)) {
			eleUtil.doClick(logoutLink);
			eleUtil.waitForVisibilityOfElement(logoutMessage, AppConstants.MEDIUM_DEFAULT_WAIT);
			eleUtil.doClick(registerLink);
			
			
			return true;
		}else {
			return false;
		}
		
	}
	
	
	
	

}
