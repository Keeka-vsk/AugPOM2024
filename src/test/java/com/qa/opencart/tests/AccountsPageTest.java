package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {
	
	
	@BeforeTest
	public void accSetUp() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
//		accpage = loginpage.doLogin("keerthuint@gmail.com", "Vsk@9194");
//		accpage = new AccountsPage(driver);
	}
	
	
	@Test
	public void accountPageTitleTest() {
		Assert.assertEquals(accpage.getAccountPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void accountUrlTest() {
		Assert.assertTrue(accpage.getAccountPageUrl().contains(AppConstants.ACCOUNT_PAGE_URL_FRACTION));
	}
	
	@Test
	public void logoutLinkExistTest() {
		Assert.assertTrue(accpage.isLogoutLinkExist());
	}

	@Test
	public void searchFieldExistTest() {
		Assert.assertTrue(accpage.isSearchFieldExist());	
	}
	
	@Test
	public void acctPageHeadersCountTest() {
		List<String> actPageHeaderList = accpage.getAccountHeaders();
		System.out.println(actPageHeaderList);
		//Assert???
		Assert.assertEquals(actPageHeaderList.size(), AppConstants.ACCOUNT_PAGE_HEADERS_COUNT);
	}
	
	@Test
	public void acctPageHeadersTest() {
		List<String> actPageHeaderList = accpage.getAccountHeaders();
		System.out.println(actPageHeaderList);
		
		//Assignement:
		/*
		 * Sort the acctual list and 
		 * Sort the expected list and then
		 * Compare it
		 */
		
		Assert.assertEquals(actPageHeaderList, AppConstants.ACCOUNT_PAGE_HEADERS_LIST);
	}
	
	@Test
	public void searchTest() {
		searchrespage = accpage.doSearch("MacBook");
		prodinfopage = searchrespage.selectProduct("MacBook Pro");
		String actprodHeader = prodinfopage.getPorductHeaderName();
		Assert.assertEquals(actprodHeader, "MacBook Pro");
	}
	
	
	
}
