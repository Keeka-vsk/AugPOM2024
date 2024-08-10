	package com.qa.opencart.tests;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtils;

public class RegisterPageTest extends BaseTest{

	
	@BeforeClass
	public void regSetup() {
		registerpage = loginpage.navigateToRegisterPage();
	}
	
	public String getRamdomEmailID() {
		return "testautomation"+System.currentTimeMillis()+"@opencart.com";
		
		//return "testautomation"+ UUID.randomUUID()+"@gamil.com";
		/*
		 * You can generate some random number using UUID. it is a special class you have to import form java utils
		 */
		
	}
	
	
	
//	@DataProvider
//	public Object[][] getUserRegistrationData() {
//		return new Object[][] {
//			{"Karthi", "Somu", "293866246", "test@123", "yes"},
//			{"warren", "fly",  "3425435345234", "test@123", "yes"},
//			{"kalki", "art",  "56565", "test@123", "no"}
//		};
//	}
	
	@DataProvider
	public Object[][] getUserRegTestExcelData() {
		Object regData[][] = ExcelUtils.getTestData(AppConstants.REGISTER_DATA_SHEET_NAME);
		return regData;
	}
	
	
	
	
	@Test(dataProvider ="getUserRegTestExcelData")
	public void userRegisterTest(String firstName, String lastName, 
			String telephone, String password, String subscribe) {
			
		boolean isRegDone = registerpage.userRegistration(firstName, lastName, 
				getRamdomEmailID(), telephone, password, subscribe);
		Assert.assertTrue(isRegDone);
		
//		boolean isRegDone = registerpage.userRegistration("Tom", "Automation", "tom@opencart.com", "987238723","test@123", "yes");
//		Assert.assertTrue(isRegDone);
	}
	
	
	
	
}
