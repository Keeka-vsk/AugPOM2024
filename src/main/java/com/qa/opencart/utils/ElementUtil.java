package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exception.FrameworkException;

public class ElementUtil {
	
	private WebDriver driver;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Used to pass the locator base don locator type given by the user 
	 * @param locatorType
	 * @param locatorValue
	 * @return by value 
	 */
	public By getBy(String locatorType, String locatorValue) {
		By by = null;
		
		switch (locatorType.toLowerCase().trim()) {
		case "id":
			by  = By.id(locatorValue);
			break;
		case "name":
			by = By.name(locatorValue);
			break;
		case "class":
			by = By.className(locatorValue);
			break;
		case "xpath":
			by = By.xpath(locatorValue);
			break;
		case "css":
			by = By.cssSelector(locatorValue);
			break;
		case "linktext":
			by = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			by = By.partialLinkText(locatorValue);
			break;
		case "tag":
			by = By.tagName(locatorValue);
			break;
		default:
			System.out.println("Wrong locator type is passed... " + locatorType);
			throw new FrameworkException("WRONG LOCATOR TYPE");
		}
		return by;
	}
	
	
	/**
	 * This internally call getElement and getBy method and pass the locator 
	 * and locator type should be given by the user and value was entered in the 
	 * application. It is an overloaded method.
	 * @param locatorType
	 * @param locatorValue
	 * @param value
	 */
//	Approach 1:
//	public void doSendKeys(String locatorType, String locatorValue, String value) {
//		getElement(getBy(locatorType, locatorValue)).sendKeys(value);
//	}
	public void doSendKeys(String locatorType, String locatorValue, String value) {
		getElement(locatorType, locatorValue).sendKeys(value);
	}
	
	/**
	 * This internally call getElement method and pass the locator and then enter the 
	 * value given by the user
	 * @param locator 
	 * @param value
	 */
	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}
		
	/**
	 * This will create the WebElement using locators. 
	 * @param locator you have to pass which is captured from the application by the user  
	 * @return WebElement
	 */
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	
	/**
	 * This is an overloaded method of getElement method
	 * @param locatorType
	 * @param locatorValue
	 * @return WebElement
	 */
	public WebElement getElement(String locatorType, String locatorValue) {
		return driver.findElement(getBy(locatorType, locatorValue));
	}
	
	/**
	 * This will internally call the getElement method and click the locator 
	 * @param locator
	 */
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	/**
	 * Here you pass the locator in the form of type and value an it will click on it and it 
	 * is also an overloaded method.
	 * @param locatorType
	 * @param locatorValue
	 */
	public void doClick(String locatorType, String locatorValue) {
		getElement(locatorType, locatorValue).click();
	}
	
	/**
	 * To get the visible text of the element and internally calls the getElement method 
	 * to pass the locator
	 * @param locator 
	 * @return Element text in the form of String
	 */
	public  String  doElementGetText(By locator) {
		return getElement(locator).getText();
	}
	
	/**
	 * Here you pass the locator in the form of String using type and value
	 * and capture the text and it is an overloaded method.
	 * @param locatorType
	 * @param locatorValue
	 * @return Element text in the form of String
	 */
	public String doElementGetText(String locatorType, String locatorValue) {
		return getElement(locatorType, locatorValue).getText();
	}
	
	/**
	 * This method will give you the attribute value in which you have to 
	 * pass locator and attribute name.
	 * @param locator
	 * @param attrName
	 * @return attribute value in the form of String
	 */
	public String doGetElementAtribute(By locator,String attrName) {
		return getElement(locator).getAttribute(attrName);
	}
	
	public  List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public  int getElementsCount(By locator) {
		return getElements(locator).size();
	}
	
	/**
	 * This will capture all the links in a particular web page using locator
	 * @param locator
	 * @return It will return List<String> which holds all the links text in the web page given
	 * WAF : capture the text of all the web page links and return List<String>
	 */
	public  List<String> getElementTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e: eleList) {
			String text = e.getText();
			if(text.length()!=0) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}
	
	/**
	 * This will capture specific attribute values from the list in a web page  
	 * @param locator
	 * @param attrName
	 * @return It returns attribute value in the form of List<String>
	 * WAF: capture specific attribute from the list
	 */
	public List<String> getElementsAttributeList(By locator,String attrName) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleAttrList = new ArrayList<String>();
		for(WebElement e: eleList) {
			String attrValue = e.getAttribute(attrName);
			eleAttrList.add(attrValue);
		}
		return eleAttrList;
	}
	
	/**
	 * This will click on a particular link from google suggestion list 
	 * where you have to pass the search value and have to gave the locators accordingly
	 * @param searchField
	 * @param suggestions
	 * @param searchKey
	 * @param suggName
	 * @throws InterruptedException
	 */
	public void Search(By searchField,By suggestions, String searchKey, String suggName) throws InterruptedException {
		//driver.findElement(searchField).sendKeys(searchKey);
		//getElement(searchField).sendKeys(searchKey);
		doSendKeys(searchField, searchKey);
		Thread.sleep(3000);
		
		//List<WebElement> suggList = driver.findElements(suggestions);
		List<WebElement> suggList = getElements(suggestions);
		System.out.println(suggList.size());
		
		for(WebElement e:suggList) {
			String suggListText = e.getText();
			System.out.println(suggListText);
			if(suggListText.contains(suggName)) {
				e.click();
				break;
			}
		}	
	}
	
	/**
	 * This helps to click on an element from the captured list
	 * @param locator
	 * @param eleText
	 */
	public void clickOnElement(By locator,String eleText) {
		List<WebElement> eleList = getElements(locator);
		System.out.println(eleList.size());
		for(WebElement e: eleList) {
		String text = e.getText();
		System.out.println(text);
			if(text.equals(eleText)) {
			e.click();
			break;
				}
			}
		}
	
	//***********************SELECT DROP DOWN UTILS *******************************************//
	
	/**
	 * This will select the option in drop down using
	 * the option index value for this we use 
	 * SelectByIndex method which is under Select class
	 * @param locator
	 * @param index 
	 */
	public void doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	/**
	 * This will select the option in drop down using
	 * the options Visible text for this we use
	 * SelectByVisibleText method which is under Select class
	 * @param locator
	 * @param value
	 */
	public void doSelectDropDownByVisibleText(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}
	
	/**
	 * This will select the option in drop down using
	 * the options Values for this we use
	 * SelectByValue method which is under Select class
	 * @param locator
	 * @param value
	 */
	public void doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	
	/**
	 * This will give you all the values under the Drop down
	 * using getOptions method in Select class and click on a
	 * particular value you given 
	 * @param locator
	 * @param Value
	 */
	public void getSelectDropDownOptions(By locator, String Value) {
		Select select = new Select(getElement(locator));
		List<WebElement> allOptions = select.getOptions();
		System.out.println(allOptions.size());
		for(WebElement e: allOptions ) {
			String text = e.getText();
			System.out.println(text);
			if(text.equals(Value)){
				e.click();
				break;
			}
		}
	}
	
	/**
	 * This will print all the options under drop down in the console
	 * @param locator
	 * @return List<String> which get all the options text under drop down 
	 * and print it
	 */
	public List<String> getDropdownOptionsText(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> allOptions = select.getOptions();
		List<String> optionsText = new ArrayList<String>();
		System.out.println(allOptions.size());
		
		for(WebElement e: allOptions ) {
			String text = e.getText();
			optionsText.add(text);
		}
		return optionsText;
	}
	
	/**
	 * This will give you the Total options count in the 
	 * dropdown
	 * @param locator
	 * @return
	 */
	public int getDropdownOptionsCount(By locator) {
		Select select = new Select(getElement(locator));
				return select.getOptions().size();
	}
	
	/**
	 * This will click on a particular option value without using select 
	 * class and methods of Select class. It uses By locator and
	 * directly print or click on a Drop down options 
	 * @param locator
	 * @param Value
	 */
	public void getDropDownValues(By locator,String Value) {
		List<WebElement> allOptionsList = getElements(locator);
		for(WebElement e: allOptionsList) {
			String text = e.getText();
			System.out.println(text);
			if(text.equals(Value)) {
				e.click();
				break;
			}
		}
	}
	
	public boolean isDropdownMultiple(By locator) {
		Select select = new Select(getElement(locator));
		return select.isMultiple() ? true : false ;		
	}
	
	/**
	 * this method is used to select values from the drop down. It can select;
	 * 1. Single selection
	 * 2. Multiple selection
	 * 3. All Selection: please pass "all" as a value to select all the values	
	 * @param locator
	 * @param value
	 */
	public void selectMultipleDropdownValues(By locator,By optionsLocator, String... value) {
		Select select = new Select(getElement(locator));
		if(isDropdownMultiple(locator)) {
			if(value[0].equalsIgnoreCase("all")) {
//				//for this we don't need options locator
//				List<WebElement> opt = select.getOptions();
//				for(WebElement e1: opt) {
//					e1.click();
//				}
				List<WebElement> captureOptions = getElements(optionsLocator);
				for(WebElement e : captureOptions) {
					e.click();
				}
			}
			else {
				for(String val: value) {
					select.selectByVisibleText(val);
				}
			}
			
		}
	}
	
	//*****************************ACTIONS utils**************************//
	
	/**
	 * This will hover to an element and click on a particular 
	 * option based on the list opened
	 * This is used for single menu to click
	 * @param parentMenuLocator
	 * @param childMenuLocator
	 * @throws InterruptedException
	 */
	public void twoLeveldMenuHandle(By parentMenuLocator, By childMenuLocator) throws InterruptedException {
		
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenuLocator)).build().perform();
		Thread.sleep(2000);
		doClick(childMenuLocator);
	}
	
	/**
	 * It will hover to multiple slides and then click on an Element
	 * This is used to handle four menu items
	 * @param parentMenu
	 * @param firstChildMenuLocator
	 * @param secondChildMenuLocator
	 * @param thirdChildMenuLocator
	 * @throws InterruptedException
	 */
	public void fourLevelMenuHandle(By parentMenu, By firstChildMenuLocator,
			By secondChildMenuLocator, By thirdChildMenuLocator) throws InterruptedException {
		
		Actions act = new Actions(driver);
		
		doClick(parentMenu);
		Thread.sleep(1000);
		
		act.moveToElement(getElement(firstChildMenuLocator)).build().perform();
		Thread.sleep(1000);
		
		act.moveToElement(getElement(secondChildMenuLocator)).build().perform();
		Thread.sleep(1000);
		
		doClick(thirdChildMenuLocator);
		Thread.sleep(1000);	
	}
	
	/**
	 * This will enter the given text in the webpage text box using 
	 * sendkeys method with actions class.
	 * @param locator
	 * @param Value
	 */
	//Actions senkeys
	public void doActionSendKeys(By locator, String Value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), Value).perform();
	}
	
	/**
	 * This will on a WebElement provided
	 * @param locator
	 */
	//Actions click
	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}
	
	/**
	 * This will enter the given text in the webpage  with 
	 * a break/slowly in the text box using sendkeys method 
	 * and with pause method
	 * @param locator
	 * @param value
	 */
	//sendKeys with pause
	public void doActionsSendKeysWithPause(By locator, String value) {
		Actions act = new Actions(driver);
		char[] val = value.toCharArray();
		for(char c: val) {
			act.sendKeys(getElement(locator), String.valueOf(c)).pause(500).build().perform();
		}
	}
	
	//***************************** Wait class ******************************************//
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * this doesn't mean that the element is visible
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForPresenceOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/**
	 * An expectation for checking an element is present on the DOM of a page and visible
	 * Visibility means that the element is not only displayed but also has a height and width 
	 * that is greater than 0
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForVisibilityOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/**
	 * An expectation for checking that all elements present on the webpage that match the 
	 * locator is visible.
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForVisibilityOfElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void doClickWithWait(By locator,int timeOut) {
		waitForVisibilityOfElement(locator, timeOut).click();
	}
	
	public void doSendKeysWithWait(By locator, String value, int timeOut) {
		waitForVisibilityOfElement(locator, timeOut).click();
	}
	
	/**
	 * An expectation for checking that there is at least one element present on the WebPage 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForPresenceOfElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	/**
	 * Based on given partial title it check the page and print it on the console
	 * else it will throw an exception
	 * @param titleFraction
	 * @param timeOut
	 * @return
	 */
	public String waitForTitleContains(String titleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		
		try {
		if(wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
		}
		}
		catch(TimeoutException e){
			System.out.println("Title is not present");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Based on given title it checks the page and print it on the console
	 * else it will throw an exception
	 * @param title
	 * @param timeOut
	 * @return
	 */
	public String waitForTitleIs(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		
		try {
		if(wait.until(ExpectedConditions.titleIs(title))) {
			return driver.getTitle();
		}
		}
		catch(TimeoutException e){
			System.out.println("Title is not present");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Check for URL
	 * @param urlFraction
	 * @param timeOut
	 * @return
	 */
	public String waitForURLContains(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		
		try {
		if(wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
		}
		}
		catch(TimeoutException e){
			System.out.println(urlFraction + "Title is not present");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Checks for URL
	 * @param url
	 * @param timeOut
	 * @return
	 */
	public String waitForURLIs(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		
		try {
		if(wait.until(ExpectedConditions.urlToBe(url))) {
			return driver.getTitle();
		}
		}
		catch(TimeoutException e){
			System.out.println(url + "Title is not present");
			e.printStackTrace();
		}
		return null;
	}
	
	//Wait for Alert:
	
	public Alert waitForJSAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptJSAlert(int timeOut) {
		waitForJSAlert(timeOut).accept();
	}
	
	public void dismissJSAlert(int timeOut) {
		waitForJSAlert(timeOut).dismiss();
	}
	
	public void getJSAlertText(int timeOut) {
		waitForJSAlert(timeOut).getText();
	}
	
	public void enterValueOnJSAlert(int timeOut, String value) {
		waitForJSAlert(timeOut).sendKeys(value);
	}
	
	//wait for frames
	
	public void waitForFrameByLocator(By frameLocator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	public void waitForFrameByIndex(int frameIndex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}
	
	public void waitForFrameByIDOrName(String IDOrName, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IDOrName));
	}
	
	public void waitForFrameByElement(WebElement frameElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	

	//wait for windows
	public boolean checkNewWindowExist(int timeOut, int ExpectedNumberOfWindows) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
		if(wait.until(ExpectedConditions.numberOfWindowsToBe(ExpectedNumberOfWindows))) {
			return true;
		}
		}
		catch(TimeoutException e) {
			System.out.println("Number of windows is different...");
		}
		return false;
	}
	
	/**
	 * An expectation for checking an element is visible and enabled such that you can click it.
	 * @param locator
	 * @param timeOut
	 */
	public void clickElementWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		}
		catch(TimeoutException e) {
			System.out.println("Element is not clickable");
		}
	}
	
	
	//*****Fluent wait*****//
public WebElement waitForElementWithFluentWait(By locator, int timeOut, int intervalTime) {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(intervalTime))
				.withMessage("Time nout done... Element Not found...")
				.ignoring(NoSuchElementException.class);

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

}
