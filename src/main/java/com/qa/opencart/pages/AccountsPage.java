package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
		
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchIcon = By.xpath("//button[@class='btn btn-default btn-lg']");
	private By acctHeaders = By.xpath("//div[@id='content']//h2");
	
	
	//page const...
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//page actions/methods:
		public String getAccountPageTitle() {
			String title = eleUtil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
			System.out.println("Account page title is:" + title);
			return title;
		}
		
		public String getAccountPageUrl() {
			String aUrl = eleUtil.waitForURLContains(AppConstants.ACCOUNT_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
			System.out.println("The Account page URL is:"+ aUrl);
			return aUrl;
		}
		
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForVisibilityOfElement(logoutLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public void logout() {
		if(isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);
		}
	}

	public boolean isSearchFieldExist() {
		return eleUtil.waitForVisibilityOfElement(search, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public List<String> getAccountHeaders() {
		List<WebElement> headerList = eleUtil.waitForVisibilityOfElements(acctHeaders, AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> headerValList = new ArrayList<String>();
		for(WebElement e: headerList) {
			String text = e.getText();
			headerValList.add(text);
		}
		return headerValList;
	}
	
	public SearchResultPage doSearch(String searchKey) {
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultPage(driver);//TDD (Test Driven Development)
		
	}
	
	
	
}
