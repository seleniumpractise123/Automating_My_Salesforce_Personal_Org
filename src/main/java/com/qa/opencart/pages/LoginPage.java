package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1.Const. of the page class
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//2.Private By locators.
	private By  emailId = By.id("input-email");
    private By  passwordId = By.id("input-password");
	private By  loginBtn = By.xpath("//input[@type='submit' and @value='Login']");
	private By  forgetPwdlink = By.linkText("Forgotten Password");
	private By  footerLinks = By.xpath("//footer//a");
	private By loginErrorMessage = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	private By registerLink = By.linkText("Register");

	//public static Logger log = LogManager.getLogger(LoginPage.class);
	
	
	//3.Page actions/Methods:
	public String getLoginPageTitle() {
		/*
		String title = driver.getTitle();
		System.out.println("login Page title : " + title);
		return title;
		*/
		return eleUtil.waitForTitleIsAndCapture(AppConstants.LOGIN_PAGE_TITLE_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
	}
	
	public String getLoginPageURL() {
		/*
		String url = driver.getCurrentUrl();
		System.out.println("login Page url : " + url);
		return url;
		*/
		return eleUtil.waitForURLContainsAndCapture(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE, AppConstants.MEDIUM_DEFAULT_WAIT);
	}
	
	public boolean isForgetPwdLinkExist() {
		//return driver.findElement(forgetPwdlink).isDisplayed();
		return eleUtil.checkElementIsDisplayed(forgetPwdlink);
	}
	
	public List<String> getFooterLinksList() {
		
		//List<WebElement> footerLinksList = driver.findElements(footerLinks);
		List<WebElement> footerLinksList = eleUtil.waitForElementsVisible(footerLinks, AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> footerTextLink = new ArrayList<String>();
		for(WebElement e: footerLinksList) {
			String text = e.getText();
			footerTextLink.add(text);
		}
		
		return footerTextLink;
		
	}
	
	public AccountsPage doLogin(String userName, String pwd) {
		System.out.println("Correct Creds are : " + userName + " : " + pwd);
		//driver.findElement(emailId).sendKeys(userName);
		//driver.findElement(passwordId).sendKeys(pwd);
		//driver.findElement(loginBtn).click();
		
		eleUtil.waitForElementVisible(emailId, AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(emailId,userName);
		eleUtil.doSendKeys(passwordId, pwd);
		eleUtil.doClick(loginBtn);
		//return the next landing page -- AccountsPage -- page chaining model
		return new AccountsPage(driver);
		
	}
	
public boolean doLoginWithWrongCredentials(String userName, String pwd) {
	    System.out.println("Wrong Creds are : " + userName + " : " + pwd);
		eleUtil.waitForElementVisible(emailId, AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(emailId,userName);
		eleUtil.doSendKeys(passwordId, pwd);
		eleUtil.doClick(loginBtn,30);
        String errorMessage = eleUtil.doGetElementText(loginErrorMessage);
        System.out.println("Value of the error message===>" + errorMessage);
        if(errorMessage.contains(AppConstants.LOGIN_ERROR_MESSAGE)) {
        	return true;
        }
        return false;
		
	}

public RegisterPage navigateToRegisterPage() {
	eleUtil.doClick(registerLink);
	return new RegisterPage(driver);
}
	
	
	

}
