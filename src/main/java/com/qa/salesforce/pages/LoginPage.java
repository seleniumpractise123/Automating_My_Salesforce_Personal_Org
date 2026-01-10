package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;

    //1.Const. of the page class
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(this.driver);
    }

    //2.Private By locators.
    private By UserNameLoc = By.id("username");
    private By PasswordLoc = By.id("password");
    private By LoginBtnLoc = By.id("Login");
    private By appLanchLocator = By.xpath("//div[contains(@class,'navexSetupNav')]//div[@role='navigation' and @aria-label='App']//button[@title='App Launcher']");
    private By proKeyLocator = By.xpath("//h2[text()='App Launcher']/ancestor::div[@class='container']//div[@part='input-container' and @type='search']/input[@type='search' and @role='combobox']");
    private By salesLocatorClick = By.xpath("//one-app-launcher-menu-item[@class='slds-dropdown__item']/a[@data-label='Sales']");

    public  void loginToSalesforce(String username,String password){
        eleUtil.waitForElementVisible(UserNameLoc,15);
        eleUtil.doSendKeys(UserNameLoc,username);
        eleUtil.doSendKeys(PasswordLoc,password);
        eleUtil.doClick(LoginBtnLoc, 10);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public QSALESHomePage switchingSalesApp(String username,String password,String appName){
        try{
        loginToSalesforce(username,password);
        driver.navigate().refresh();
            Thread.sleep(20000);
        javaScriptUtil.waitForPageLoad(30);
        javaScriptUtil.clickElementByJS(appLanchLocator);
        //javaScriptUtil.waitForPageLoad(30);
            Thread.sleep(20000);
        eleUtil.doSendKeys(proKeyLocator,appName);
        //javaScriptUtil.waitForPageLoad(30);
        //eleUtil.doClick(salesLocatorClick);
            Thread.sleep(30000);
        javaScriptUtil.clickElementByJS(salesLocatorClick);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new QSALESHomePage(driver);

    }

}