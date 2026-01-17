package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.security.Key;
import java.security.KeyStore;

public class AccountDetailPage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    Actions actions;

    public AccountDetailPage(WebDriver driver) {
        //TODO Auto-generated constructor stub

        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }

    private By detailTabLoc = By.xpath(" (//lightning-tab-bar[@variant='standard' and contains(@exportparts,'tab-item')])[position()=1]/ul/li/a[@id='detailTab__item']");
    private By cpaturingRatingfieldLoc = By.xpath("//span[text()='Rating']/ancestor::div[@role='listitem']//lightning-formatted-text[@slot='outputField']");
    private By typeFieldLoc = By.xpath("//span[text()='Type']/ancestor::div[@role='listitem']//lightning-formatted-text[@slot='outputField']");
    private By industryFieldLoc = By.xpath("//span[text()='Industry']/ancestor::div[@role='listitem']//lightning-formatted-text[@slot='outputField']");
    private By moreBtn_Loc = By.xpath("(//div[contains(@class,'actionsContainer')]//ul[@class='slds-button-group-list' and @role='presentation'])[position()=1]/li[contains(@class,'slds-dropdown-trigger_clic')]//button");
    private By saveBtnLoc = By.xpath(" //div[@class='footer-full-width']//ul[@role='presentation']//runtime_platform_actions-action-renderer[@apiname='SaveEdit']//button[@name='SaveEdit']");
    private By clickingInlineEditBtn_Loc = By.xpath("//records-record-layout-item[@field-label='Ownership']//div[@class='slds-form-element__control']/button[@title='Edit Ownership']");
    private By ownershipPicklist_Loc = By.xpath("//label[text()='Ownership']//ancestor::lightning-combobox[@variant='label-inline']//button[@aria-label='Ownership']");
    private By customerPriorityPicklist_Loc = By.xpath("//label[text()='Customer Priority']//ancestor::lightning-combobox[@variant='label-inline']//button[@aria-label='Customer Priority']");
    private By slaPicklist_Loc = By.xpath("//label[text()='SLA']//ancestor::lightning-combobox[@variant='label-inline']//button[@aria-label='SLA']");
    private By upsellOpportunityPicklist_Loc = By.xpath("//label[text()='Upsell Opportunity']//ancestor::lightning-combobox[@variant='label-inline']//button[@aria-label='Upsell Opportunity']");
    private By activePicklist_Loc = By.xpath("//label[text()='Active']//ancestor::lightning-combobox[@variant='label-inline']//button[@aria-label='Active']");
    private By slaSerialNumber_Loc = By.xpath("//label[text()='SLA Serial Number']/ancestor::lightning-primitive-input-simple[@variant='label-inline']//input[@name='SLASerialNumber__c']");
    private By numberOfLocation_Loc = By.xpath("//label[text()='Number of Locations']/ancestor::lightning-primitive-input-simple[@variant='label-inline']//input[@name='NumberofLocations__c']");
    private By OwnerFieldValue_Loc = By.xpath("//span[text()='Ownership']//ancestor::div[@role='listitem']//slot[@name='outputField']/lightning-formatted-text[@slot='outputField']");
    private By customerPriorityFieldValue_Loc = By.xpath("//span[text()='Customer Priority']//ancestor::div[@role='listitem']//slot[@name='outputField']/lightning-formatted-text[@slot='outputField']");
    private By slaFieldValue_Loc = By.xpath("//span[text()='SLA']//ancestor::div[@role='listitem']//slot[@name='outputField']/lightning-formatted-text[@slot='outputField']");
    private By upsellOpportunityFieldValue_Loc = By.xpath("//span[text()='Upsell Opportunity']//ancestor::div[@role='listitem']//slot[@name='outputField']/lightning-formatted-text[@slot='outputField']");
    private By activeFieldValue_Loc = By.xpath("//span[text()='Active']//ancestor::div[@role='listitem']//slot[@name='outputField']/lightning-formatted-text[@slot='outputField']");
    private By customerIdFieldValue_Loc = By.xpath("//span[text()='Customer ID']/ancestor::div[contains(@class,'slds-form-element_readonly')]//lightning-formatted-text[@slot='outputField']");
    private By clickingDetailTabAfterReportLoc = By.id("detailTab__item");
    private By clickingConfirmingDeleteBtn_Loc = By.xpath("(//span[text()='Delete'])[position()=2]/parent::button[@title='Delete']");

    //Methods
    public void clickingDetailTab(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(250);
            javaScriptUtil.drawBorder(driver.findElement(detailTabLoc));
            javaScriptUtil.clickElementByJS(detailTabLoc);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void clickingDetailTabAfterOpeningAccountFromReport(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(250);
            javaScriptUtil.drawBorder(driver.findElement(clickingDetailTabAfterReportLoc));
            javaScriptUtil.clickElementByJS(clickingDetailTabAfterReportLoc);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void setClickingConfirmingDeleteBtn(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.drawBorder(driver.findElement(clickingConfirmingDeleteBtn_Loc));
            eleUtil.doMoveToElementClick(clickingConfirmingDeleteBtn_Loc);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getRatingFieldValue(){
        eleUtil.waitForElementVisible(cpaturingRatingfieldLoc,20);
        String ratingFieldvalue = eleUtil.doGetElementText(cpaturingRatingfieldLoc);
        System.out.println("Rating Field Value========>"+ratingFieldvalue);
        return ratingFieldvalue;
    }

    public String getTypeFieldValue(){
        eleUtil.waitForElementVisible(typeFieldLoc,20);
        String typeFieldvalue = eleUtil.doGetElementText(typeFieldLoc);
        System.out.println("type Field Value========>"+typeFieldvalue);
        return typeFieldvalue;
    }

    public String getIndustryFieldValue(){
        eleUtil.waitForElementVisible(industryFieldLoc,20);
        String industryFieldvalue = eleUtil.doGetElementText(industryFieldLoc);
        System.out.println("industry Field Value========>"+industryFieldvalue);
        return industryFieldvalue;
    }

    public String getCurrentURL(){
        javaScriptUtil.waitForPageLoad(30);
        String currentURL = driver.getCurrentUrl();
        System.out.println("Value of the currentUTR===>"+currentURL);
        return currentURL;
    }

    public String getCustomerIDFieldValue(){
        eleUtil.waitForElementVisible(customerIdFieldValue_Loc,30);
        String customerIDFieldvalue = eleUtil.doGetElementText(customerIdFieldValue_Loc);
        System.out.println("Customer ID Field Value========>"+customerIDFieldvalue);
        return customerIDFieldvalue;
    }

    public Accounts clickEditBtnFromMoreBtn(){
        try {
            Thread.sleep(20000);
            javaScriptUtil.waitForPageLoad(30);
            javaScriptUtil.selectingDrpDownValuesDynamically(moreBtn_Loc,"//span[text()='Edit']/parent::a[@role='menuitem']");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new Accounts(driver);
    }

    public Accounts clickDeleteBtnFromMoreBtn(){
        try {
            Thread.sleep(20000);
            javaScriptUtil.waitForPageLoad(30);
            javaScriptUtil.drawBorder(driver.findElement(moreBtn_Loc));
            javaScriptUtil.selectingDrpDownValuesDynamically(moreBtn_Loc,"//span[text()='Delete']/parent::a[@role='menuitem']");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new Accounts(driver);
    }


    public void clickSaveBtn(){
        while(true){
            eleUtil.waitForElementsVisible(saveBtnLoc, 25);
            eleUtil.doClick(saveBtnLoc);
            break;
        }
    }

    public void setClickingInlineEditBtn(){
        try {
            Thread.sleep(15000);

        javaScriptUtil.waitForPageLoad(30);
        javaScriptUtil.clickElementByJS(clickingInlineEditBtn_Loc);
        javaScriptUtil.waitForPageLoad(30);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void putOwnerShipFieldValue(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(30);
            javaScriptUtil.selectingDrpDownValuesDynamically(ownershipPicklist_Loc,"//span[text()='Public']");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void putCustomerPriorityFieldValue(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(30);
            javaScriptUtil.selectingDrpDownValuesDynamically(customerPriorityPicklist_Loc,"//span[text()='Medium']");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void putSLAFieldValue(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(30);
            javaScriptUtil.selectingDrpDownValuesDynamically(slaPicklist_Loc,"//span[text()='Silver']");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void putUpsellOpportunityFieldValue(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(30);
            javaScriptUtil.selectingDrpDownValuesDynamically(upsellOpportunityPicklist_Loc,"//span[text()='Maybe']");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void putActiveFieldValue(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(30);
            javaScriptUtil.selectingDrpDownValuesDynamically(activePicklist_Loc,"(//span[text()='No'])[position()=2]");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void givingSLASerialNumberFieldValue(String slaNumber){
        javaScriptUtil.waitForPageLoad(20);
        eleUtil.doSendKeys(slaSerialNumber_Loc,slaNumber);
    }

    public void givingNumberOfLocationFieldValue(String nLocations){
        javaScriptUtil.waitForPageLoad(20);
        eleUtil.doSendKeys(numberOfLocation_Loc,nLocations);
    }

    public void editingAccountPageByClickingInlineEditing(String slaNumber,String numLoc){

            putOwnerShipFieldValue();
            putCustomerPriorityFieldValue();
            putSLAFieldValue();
            givingSLASerialNumberFieldValue(slaNumber);
            givingNumberOfLocationFieldValue(numLoc);
            putUpsellOpportunityFieldValue();
            putActiveFieldValue();
            clickSaveBtn();
            javaScriptUtil.waitForPageLoad(40);
            driver.navigate().refresh();
            javaScriptUtil.waitForPageLoad(40);
            clickingDetailTab();
    }

    public String getOwnerShipFieldValue(){
        javaScriptUtil.waitForPageLoad(30);
        eleUtil.waitForElementVisible(OwnerFieldValue_Loc,45);
        String ownershipFieldvalue = eleUtil.doGetElementText(OwnerFieldValue_Loc);
        System.out.println("Ownership Field Value========>"+ownershipFieldvalue);
        return ownershipFieldvalue;
    }

    public String getCustomerPriorityFieldValue(){
        javaScriptUtil.waitForPageLoad(30);
        eleUtil.waitForElementVisible(customerPriorityFieldValue_Loc,45);
        String customerPriorityFieldvalue = eleUtil.doGetElementText(customerPriorityFieldValue_Loc);
        System.out.println("Customer Priority Field Value========>"+customerPriorityFieldvalue);
        return customerPriorityFieldvalue;
    }

    public String getSLAFieldValue(){
        javaScriptUtil.waitForPageLoad(30);
        eleUtil.waitForElementVisible(slaFieldValue_Loc,30);
        String slaFieldvalue = eleUtil.doGetElementText(slaFieldValue_Loc);
        System.out.println("SLA Field Value========>"+slaFieldvalue);
        return slaFieldvalue;
    }

    public String getUpsellOpportunityFieldValue(){
        javaScriptUtil.waitForPageLoad(30);
        eleUtil.waitForElementVisible(upsellOpportunityFieldValue_Loc,30);
        String upsellOpportunityFieldvalue = eleUtil.doGetElementText(upsellOpportunityFieldValue_Loc);
        System.out.println("Upsell Opportunity Field Value========>"+upsellOpportunityFieldvalue);
        return upsellOpportunityFieldvalue;
    }

    public String getActiveFieldValue(){
        javaScriptUtil.waitForPageLoad(30);
        eleUtil.waitForElementVisible(activeFieldValue_Loc,30);
        String activeFieldvalue = eleUtil.doGetElementText(activeFieldValue_Loc);
        System.out.println("Active Field Value========>"+activeFieldvalue);
        return activeFieldvalue;
    }

    public AccountHomePage navigateBackToAccountsHome(){
        javaScriptUtil.waitForPageLoad(40);
        driver.navigate().back();
        driver.navigate().refresh();
        javaScriptUtil.waitForPageLoad(40);
        return new AccountHomePage(driver);

    }


}
