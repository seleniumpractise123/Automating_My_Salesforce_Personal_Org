package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Set;

public class QSALESHomePage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;

    public QSALESHomePage(WebDriver driver) {
        //TODO Auto-generated constructor stub

        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }

    private By clickLeadTab = By.xpath("//span[text()='Leads']/parent::a[contains(@href,'/Lead/home') and @title='Leads']");
    private By AccountTabLoc = By.xpath("//div[@class='slds-context-bar']/one-app-nav-bar/nav[contains(@class,'slds-context-bar__secondary') and @role='navigation']//a[@title='Accounts']");
    private By clickingGearIcon_Loc = By.xpath("(//span[@role='navigation' and @aria-label='Global Header']//ul[@class='slds-global-actions']//li)[position()=5]//a[@role='button']");
    private By clickingDeveloperConsole_Loc = By.xpath("//span[text()='Developer Console']//ancestor::a[@title='Developer Console']");
    private By getClickingExpandBtn_Loc = By.xpath("//img[@class='x-tool-expand-top' and @role='presentation']/parent::div");
    private By queryEditorTab_Loc = By.xpath("//span[text()='Query Editor']/parent::button[@type='button']");
    private By queryTextArea_Loc = By.xpath("//table[@id='queryEditorText']//tr[@id='queryEditorText-inputRow']//textarea[@name='queryEditorText-inputEl']");
    private By executeBtn_Loc = By.xpath("//span[text()='Execute']/parent::button[@type='button']");
    private By globalSearch_Loc = By.xpath("//div[contains(@class,'slds-global-header__item_search')]//button[@aria-label='Search' and @type='button']");
    private By accountHeaderLink_Loc = By.xpath("(//div[contains(@class,'forceListViewManagerGrid')])[position()=1]//div[contains(@class,'scroll-bidirectional')]//table[@data-aura-class='uiVirtualDataTable']/tbody//th[@scope='row']//a[@data-aura-class='forceOutputLookup']");
    private By enterSearchContext_Loc = By.xpath("//label[contains(text(),'Search...')]/parent::div[@part='input-text']//div[@part='input-container']");
    private By reportsTabLoc = By.xpath("//div[@class='slds-context-bar']/one-app-nav-bar/nav[contains(@class,'slds-context-bar__secondary') and @role='navigation']//a[@title='Reports']");




    public AccountHomePage clickingAccountsTab(){
        //eleUtil.doClick(AccountTabLoc, 10);
        System.out.println("Compiler came to clickingAccountsTab method");
        try {
            driver.navigate().refresh();
            Thread.sleep(15000);
            //javaScriptUtil.waitForPageLoad(20);
            javaScriptUtil.clickElementByJS(AccountTabLoc);
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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

    public String setClickingGearIcon_Loc(String custID) {
        String ExistingAccountSFDCID = null;
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(30);
            String ParentWindowID = driver.getWindowHandle();
            System.out.println("ID of the current window::::::" + ParentWindowID);
            //javaScriptUtil.selectingDrpDownValuesDynamically(clickingGearIcon_Loc, "//span[text()='Developer Console']//ancestor::a[@title='Developer Console']");
            //eleUtil.doClick(clickingGearIcon_Loc);
            javaScriptUtil.clickElementByJS(clickingGearIcon_Loc);
            //eleUtil.doClick(clickingDeveloperConsole_Loc);
            javaScriptUtil.clickElementByJS(clickingDeveloperConsole_Loc);
            javaScriptUtil.waitForPageLoad(30);
            //Switching to Child window

            Set<String> allWindowHandles = driver.getWindowHandles();
            for (String handle : allWindowHandles) {
                if (!handle.equals(ParentWindowID)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }
            //Clicking Query Editor tab
            System.out.println("ID of the current window::::::" + driver.getWindowHandle());
            javaScriptUtil.waitForPageLoad(40);
            driver.manage().window().maximize();
            //eleUtil.doClick(getClickingExpandBtn_Loc);
            //eleUtil.waitForPageLoad(30);
            //Clicking Query Editor tab
            eleUtil.doClick(queryEditorTab_Loc, 35);
            //Passing Query
            eleUtil.doClear(queryTextArea_Loc);
            eleUtil.doSendKeys(queryTextArea_Loc, "SELECT Id,Industry,Name FROM Account WHERE Customer_ID__c = '"+custID+"' ");
            eleUtil.doClick(executeBtn_Loc);
            Thread.sleep(35000);
            By SFDCID_Ele = By.xpath("((//table[contains(@class,'x-grid-table-resizer')])[1]//div)[1]");
            System.out.println("Value of the SFDCID_Ele===>" + SFDCID_Ele);
            //actions.doubleClick(SFDCID_Ele).click().build().perform();
            javaScriptUtil.clickElementByJS(SFDCID_Ele);
            ExistingAccountSFDCID = SFDCID_Ele.toString();
            System.out.println("Value of the ExistingAccountSFDCID=========>" + ExistingAccountSFDCID);
            driver.close();
            driver.switchTo().window(ParentWindowID);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ExistingAccountSFDCID;
    }

    public AccountDetailPage navigateToAccountDetailPageThroughSFDCID(String sfdcID){
        System.out.println("Account SFDC ID ======> " + sfdcID);
        String fullInstanceURL = driver.getCurrentUrl();
        System.out.println("Full Instance URL"+ fullInstanceURL);
        String InstanceURL = fullInstanceURL.substring(0, 50);
        System.out.println(InstanceURL);
        String urlContainsWithSFDCAccountID = InstanceURL+sfdcID;
        System.out.println("urlContainsWithSFDCAccountID URL"+ urlContainsWithSFDCAccountID);
        driver.get(urlContainsWithSFDCAccountID);
        javaScriptUtil.waitForPageLoad(40);
        return new AccountDetailPage(driver);

    }

    public String getCustID(String rawData){
        System.out.println("Valur of the row Data===> " + rawData);
        String cptureCustId = rawData.substring(0,9);
        System.out.println("Valur of the cptureCustId===> " + cptureCustId);
        return cptureCustId;


    }

    public AccountDetailPage searchCustIDGlobally(String cutID){
        System.out.println("Value of the CUST ID====> " + cutID);
        try {
            driver.navigate().refresh();
            Thread.sleep(20000);
            javaScriptUtil.waitForPageLoad(40);
            actions.moveToElement(driver.findElement(globalSearch_Loc)).click().build().perform();
            //eleUtil.doSendKeys(enterSearchContext_Loc,cutID);
            javaScriptUtil.waitForPageLoad(30);
            javaScriptUtil.drawBorder(driver.findElement(enterSearchContext_Loc));
            eleUtil.doActionsSendKeys(enterSearchContext_Loc,cutID);
            Thread.sleep(15000);
            WebElement searchOption = driver.findElement(By.xpath("//div[@role='option']//span[contains(@title,'Show more results for')]//span"));
            javaScriptUtil.drawBorder(searchOption);
            actions.moveToElement(searchOption).sendKeys(Keys.ENTER).perform();
            //actions.moveToElement(driver.findElement(accountHeaderLink_Loc)).click().build().perform();
            //eleUtil.doMoveToElement(accountHeaderLink_Loc);
            //javaScriptUtil.waitForPageLoad(40);
            Thread.sleep(15000);
            javaScriptUtil.drawBorder(driver.findElement(accountHeaderLink_Loc));
            javaScriptUtil.clickElementByJS(accountHeaderLink_Loc);
            javaScriptUtil.waitForPageLoad(30);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new AccountDetailPage(driver);
    }

    public  ReportsHomePage clickReportsTab(){
        System.out.println("Compiler came to clickingReportsTab method");
        try {
            driver.navigate().refresh();
            Thread.sleep(15000);
            //javaScriptUtil.waitForPageLoad(20);
            javaScriptUtil.clickElementByJS(reportsTabLoc);
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new ReportsHomePage(driver);
    }


}
