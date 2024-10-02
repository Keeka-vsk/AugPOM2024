package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	OptionsManager optionsmanager;
	//we implement threadlocal concept to get new and individual driver each time while we use threads 
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>(); 
	
	public WebDriver initDriver(Properties prop /*String browserName*/) { // instead of browserName you can call the Properties reference 
		
		String browserName = prop.getProperty("browser");
//		String browserName = System.getProperty("browser");
		System.out.println("Browser name is :" +browserName);
		
		optionsmanager = new OptionsManager(prop);
		
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
//			driver = new ChromeDriver();
//			break;
//			driver = new ChromeDriver(optionsmanager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsmanager.getChromeOptions()));
			break;
		case "firefox":
//			driver = new FirefoxDriver();
//			break;
//			driver = new FirefoxDriver(optionsmanager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsmanager.getFirefoxOptions()));
			break;
		case "edge":
//			driver = new EdgeDriver(optionsmanager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsmanager.getEdgeOptions()));
			break;
		default:
			System.out.println("please pass the right browser name..." +browserName);
			throw new FrameworkException("No browser found...");
		}
		
//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
//		driver.get(prop.getProperty("url"));//Using properties reference you can remove the hardcoded url value
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
//		return driver;
		return getDriver();
		
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public Properties initProp() {
		
		/*
		 * to execute the code on specific environment we need to use specific command in
		 * maven while you run the code. i.e:
		 * mvn clean install -Denv="qa" 
		 */
		FileInputStream fip = null;
		prop = new Properties();
		
		String envName = System.getProperty("env");
		System.out.println("env name is:"+ envName);
		
		try {
		if(envName == null) {
			System.out.println("your env is null... hence running tests on QA env...");
			fip = new FileInputStream("./src/test/resources/config/config.qa.properties");
		}
		else {
			switch (envName.toLowerCase().trim()) {
			case "qa":
				fip = new FileInputStream("./src/test/resources/config/config.qa.properties");
				break;
			case "dev":
				fip = new FileInputStream("./src/test/resources/config/config.dev.properties");
				break;
//			case "uat":
//				fip = new FileInputStream("./src/test/resources/config/config.uat.properties");
//				break;
//			case "prod":
//				fip = new FileInputStream("./src/test/resources/config/config.prod.properties");
//				break;

			default:
				System.out.println("please pass the right env name..."+ envName);
				throw new FrameworkException("Wrogn env name..." +	envName);
			}
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		try {
			prop.load(fip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		
//		//to establish a connection with the file 
//		// here ./ (dot slash represent root)
//		prop = new Properties();
//		try {
//			FileInputStream fip = new FileInputStream("./src/test/resources/config/config.properties");
//			prop.load(fip);//used to establish connection and load the key and value from config file to properties class object ref
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		
		
	}

	/**
	 * take screenshot
	 */
	public static String getScreenshot(String methodName) {

		//takes screen shot is an interface in selenium
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
}

