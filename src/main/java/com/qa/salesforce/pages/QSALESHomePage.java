package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class QSALESHomePage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;

    public QSALESHomePage(WebDriver driver) {
        //TODO Auto-generated constructor stub

        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
    }

    private By clickLeadTab = By.xpath("//span[text()='Leads']/parent::a[contains(@href,'/Lead/home') and @title='Leads']");
    private By AccountTabLoc = By.xpath("//div[@class='slds-context-bar']/one-app-nav-bar/nav[contains(@class,'slds-context-bar__secondary') and @role='navigation']//a[@title='Accounts']");


    public AccountHomePage clickingAccountsTab(){
        //eleUtil.doClick(AccountTabLoc, 10);
        System.out.println("Compiler came to clickingAccountsTab method");
        javaScriptUtil.waitForPageLoad(20);
        javaScriptUtil.clickElementByJS(AccountTabLoc);
        return new AccountHomePage(driver);
    }

    public AccountDetailPage navigatingToAccountDetailPageByUsingUrl(String accUrl){
        System.out.println("Value of the URL====> " + accUrl);
        // Find where "https" starts
        int startIndex = accUrl.indexOf("https");
        // Extract substring from that index
        String acurl = accUrl.substring(startIndex);
        System.out.println("Value of the acurl URL====> " + acurl);
        driver.get(acurl);
        return new AccountDetailPage(driver);

    }


}
