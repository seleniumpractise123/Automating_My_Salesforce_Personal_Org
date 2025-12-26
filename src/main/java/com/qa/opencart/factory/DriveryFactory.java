package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.frameworkexceptions.FrameException;

public class DriveryFactory {
	
	WebDriver driver;
	optionManager options_manager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser").trim();
		System.out.println("Browser Name is====> " + browserName);
		options_manager = new optionManager(prop);
		switch (browserName.toLowerCase()) {
		case "chrome":
			//driver = new ChromeDriver(options_manager.getChromeOptions());
			tlDriver.set(new ChromeDriver(options_manager.getChromeOptions()));
			break;
		case "firefox":
			driver = new FirefoxDriver(options_manager.getFirefoxOptions());
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "safari":
			driver = new SafariDriver();
			break;

		default:
			System.out.println("plz pass the right Browser====> " + browserName);
			throw new FrameException("NO_BROWSER_FOUND_EXCEPTION");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
		
	}
	
	//return the thread local copy of the driver
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public Properties initProp() {
		Properties prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/main/resource/config/config.properties");
			try {
				prop.load(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return prop;
		
	}

}
