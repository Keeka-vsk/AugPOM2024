package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.utils.ExcelUtils;

public class ProductResultPageTest extends BaseTest{
	
	@BeforeClass
	public void productInfoSetup() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
//	@DataProvider
//	public Object[][] getSearchData() {
//		return new Object[][] {
//			{"MacBook", "MacBook Pro", 4},
//			{"MacBook", "MacBook Air", 4},
//			{"iMac","iMac", 3},
//			{"Samsung","Samsung Galaxy Tab 10.1", 7}
//		};
//	}
	
	@DataProvider
	public Object[][] getSearchExcelData() {
		return ExcelUtils.getTestData(AppConstants.PRODUCT_DATA_SHEET_NAME);
	}
	
	
	
	@Test(dataProvider = "getSearchExcelData")
	public void productImageTest(String searchKey, String productName, String imageCount) {
		searchrespage =  accpage.doSearch(searchKey);
		prodinfopage =  searchrespage.selectProduct(productName);
		Assert.assertEquals(String.valueOf(prodinfopage.getProductImageCount()),imageCount);
	}
	
	@Test
	/*
	 * Do this assignement maintain 8 colums and mapp with data provider 
	 * and aslo do the assignment for data provider using excel sheet using this productInfotest
	 */
	public void productInfoTest() {
		searchrespage =  accpage.doSearch("MacBook");
		prodinfopage =  searchrespage.selectProduct("MacBook Pro");
		Map<String,String> productDetailsMap =  prodinfopage.getProductDetails();
		
		softassert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		softassert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		softassert.assertEquals(productDetailsMap.get("Reward Points"), "800");
		softassert.assertEquals(productDetailsMap.get("Availability"), "In Stock");
		
		softassert.assertEquals(productDetailsMap.get("Price"), "$2,000.00");
		softassert.assertEquals(productDetailsMap.get("Ex Tax Price"), "$2,000.00");
		
		softassert.assertAll();
		
	}

	/*
	 * Home work
	 * go to login page 
	 * write negative scenario for username and [pwd
	 * negatie data you have to maintian in excel sheet
	 * maintian 2 column and many rows as possible up to 6 or 8 
	 * give wrong name and pwd 
	 */
}
