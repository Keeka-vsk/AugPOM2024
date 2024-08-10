package com.qa.opencart.tests;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest{
	
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginpage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TILE);
	}

	@Test(priority = 2)
	public void loginPageURLTest() {
		String actURL = loginpage.getLoginPageUrl();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Test(priority = 3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginpage.isForgotPwdLinkExist());
	}
	
	@Test(priority = 4)
	public void appLogoExistTest() {
		Assert.assertTrue(loginpage.isLogoExist());
	}
	
	@Test(priority = 5)
	public void loginTest() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accpage.isLogoutLinkExist());
		
		
		
//		Assert.assertTrue(loginpage.doLogin("keerthuint@gmail.com", "Vsk2803"));
	}
	
}
