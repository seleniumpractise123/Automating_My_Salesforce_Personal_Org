package com.qa.salesforce.base;

import java.util.Properties;

import com.qa.salesforce.pages.*;
import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.ExcelUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.salesforce.factory.DriveryFactory;
import com.qa.salesforce.utils.PropertyReader;


public class BaseTest {

	WebDriver driver;
	protected QSALESHomePage qsalesHomePage;
	protected LoginPage loginPage;
	protected AccountHomePage accountHomePage;
	protected Accounts accounts;
	protected AccountDetailPage accountDetailPage;
	protected ReportsHomePage reportsHomePage;
	protected ReportBuilderPage reportBuilderPage;
	protected ReportsDetailPage reportsDetailPage;
	protected ExcelUtil excelUtil;
	protected JavaScriptUtil javaScriptUtil;
	protected ElementUtil elementUtil;
	
	protected PropertyReader properreader;
	
	protected DriveryFactory df;
	protected Properties prop;
	protected SoftAssert softAssert;
	
	@BeforeTest
	public void setup() {
		df = new DriveryFactory();
		prop = df.initProp();
		
		
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		accountHomePage = new AccountHomePage(driver);
		accounts = new Accounts(driver);
		accountDetailPage = new AccountDetailPage(driver);
		reportsHomePage = new ReportsHomePage(driver);
		reportBuilderPage = new ReportBuilderPage(driver);
		reportsDetailPage = new ReportsDetailPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	

}
