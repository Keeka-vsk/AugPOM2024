package com.qa.opencart.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	private WebDriver driver;
	private JavascriptExecutor js;

	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor)driver;
	}
	
	public String getTitleByJs() {  
		return js.executeScript("return document.title").toString();
	}
	
	public String getURLByJs() {
		return js.executeScript("return document.URL").toString();	
	}
	
	//To generate user defined alert
	public void generateJSAlert(String mesg) {
		js.executeScript("alert('"+mesg+"')");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.switchTo().alert().accept();
	}
	
	//to generate user defined prompt
	public void generateJSPrompt(String mesg, String Value) {
		js.executeScript("prompt('"+mesg+"')");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Alert alt = driver.switchTo().alert();
		alt.sendKeys(Value);
		alt.accept();
	}
	
	public void goBackWithJS() {
		js.executeScript("history.go(-1)");
	}
	
	public void goForwardWithJS() {
		js.executeScript("history.go(1)");
	}
	
	public void pageRefreshWithJS() {
		js.executeScript("history.go(0)");
	}
	
	public String getPageInnerText() {
		return js.executeScript("return document.documentElement.innerText").toString();
	}
	
	public void scrollPageDownWithJS() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
	
	public void scrollMiddlePageDownWithJS() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight/2);");
	}
	
	public void scrollPageDownWithJS(String height) {
		js.executeScript("window.scrollTo(0, '"+height+"');");
	}
	
	public void scrollPageUpWithJS() {
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0);");
	}
	
	public void scrollIntoView(WebElement element) {
		js.executeScript("argument[0].scrollIntoView(true);", element);
	}
	
	public void zoomChromeEdgeSafari(String zoomPercentage) {
		String zoom = "document.body.style.zoom = '"+zoomPercentage+"%'" ;
		js.executeScript(zoom);
	}
	
	public void drawBorder(WebElement elem) {
		js.executeScript("argument[0].style.border='3px solid red'", elem);
	}
	
	
	

}
