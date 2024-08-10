package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.exception.FrameworkException;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader = By.cssSelector("div#content h1"); 
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]//li");
	private By productPricing = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]//li");
	
	private Map<String, String> productMap = new HashMap<String,String>();
	/*
	 * If you want to maintain order based collection then you can use LinkedHashMap
	 * Use below code
	 */
//	private Map<String,String> productMap = new LinkedHashMap<String,String>();
	/*
	 * If you want to maintain alphabetical order based collection then you can use TreeMap
	 * Use below code
	 */
//	private Map<String,String> productMap = new TreeMap<String,String>();
	
	
	//page const...
		public ProductInfoPage(WebDriver driver) {
			this.driver = driver;
			eleUtil = new ElementUtil(this.driver);
		}
		
	public String getPorductHeaderName() {
		String productHeaderVal = eleUtil.doElementGetText(productHeader);
		System.err.println("Product Header is :" + productHeaderVal);
		return productHeaderVal;
	}

	public int getProductImageCount() {
		int imageCount = eleUtil.waitForVisibilityOfElements(productImages, AppConstants.MEDIUM_DEFAULT_WAIT).size();
		System.out.println("Prod : " + getPorductHeaderName() + " and Image count is : " + imageCount );
		return imageCount;
		
	}
	
	private void getProductMetaData() {
		List<WebElement> metaDataList =  eleUtil.waitForVisibilityOfElements(productMetaData, AppConstants.MEDIUM_DEFAULT_WAIT);
		
		for(WebElement e: metaDataList) {
			String metaData = e.getText();
			String metaKey = metaData.split(":")[0].trim();
			String metaValue = metaData.split(":")[1].trim();
			productMap.put(metaKey, metaValue);
		}
		
	}
	
	
	private void getProductPriceData() {
		List<WebElement> metaPriceList =  eleUtil.waitForVisibilityOfElements(productPricing, AppConstants.MEDIUM_DEFAULT_WAIT);
		
		String productPrice = metaPriceList.get(0).getText();
		String productExTax = metaPriceList.get(1).getText().split(":")[1].trim(); 
		productMap.put("Price", productPrice);
		productMap.put("Ex Tax Price", productExTax);
	}
		
	
	public Map<String,String> getProductDetails() {
		productMap.put("ProductName", getPorductHeaderName());
		getProductMetaData();
		getProductPriceData();
		
		System.out.println(productMap);
		return productMap;
	}
		

	/*
	 * Assignment
	 * capture all the footers in and write 5 soft assertions for first 5 footer links
	 */
	
}
