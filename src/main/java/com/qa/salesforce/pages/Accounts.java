package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Accounts {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;

    public Accounts(WebDriver driver) {
        //TODO Auto-generated constructor stub

        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
    }

    //2.Private By locators.
    private By Account_Name_Loc = By.xpath("//label[text()='Account Name']/ancestor::lightning-input[@variant='label-inline']//div[@part='input-container' and @type='text']/input[@name='Name']");
    private By Account_Number_Loc = By.xpath("//label[text()='Account Number']/ancestor::lightning-input[@variant='label-inline']//div[@part='input-container' and @type='text']/input[@name='AccountNumber']");
    private By Rating_Loc = By.xpath("//label[text()='Rating']/ancestor::lightning-combobox[@variant='label-inline']//button[@type='button']");
    private By TypeLoc = By.xpath("//label[text()='Type']/ancestor::lightning-combobox[@variant='label-inline']//button[@type='button']");
    private By IndustryLoc = By.xpath("//label[text()='Industry']/ancestor::lightning-combobox[@variant='label-inline']//button[@type='button']");
    private By descriptionLoc = By.xpath("//label[text()='Description']/ancestor::lightning-textarea[@variant='label-inline']//textarea[@part='textarea']");
    private By Shipping_Street_Loc = By.xpath("//label[text()='Shipping Street_c']/ancestor::lightning-textarea[@variant='label-inline']//textarea[@part='textarea']");
    private By Shiiping_State_Loc = By.xpath("//label[text()='Shipping State_c']/ancestor::lightning-input[@variant='label-inline']//input[@name='Shipping_State_c__c']");
    private By Shipping_city_Loc = By.xpath("//label[text()='Shipping City_c']/ancestor::lightning-input[@variant='label-inline']//input[@name='Shipping_City_c__c']");
    private By Shiiping_Country_Loc = By.xpath("//label[text()='Shipping Country_c']/ancestor::lightning-input[@variant='label-inline']//input[@name='Shipping_Country_c__c']");
    private By Shipping_Postal_Loc = By.xpath("//label[text()='Shipping Zipcode_c']/ancestor::lightning-input[@variant='label-inline']//input[@name='Shipping_Zipcode_c__c']");
    private By saveBtnLoc = By.xpath(" (//records-form-footer[contains(@class,'slds-docked-form-footer')]//ul[@role='presentation']//li)[position()=3]//button[@name='SaveEdit' and @type='button']");
    private By billing_Street_Loc = By.xpath("//label[text()='Billing Street_c']/ancestor::lightning-textarea[@variant='label-inline']//textarea[@part='textarea']");
    private By billing_State_Loc = By.xpath("//label[text()='Billing State_c']/ancestor::lightning-input[@variant='label-inline']//input[@name='Billing_State_c__c']");
    private By billing_city_Loc = By.xpath("//label[text()='Billing City_c']/ancestor::lightning-input[@variant='label-inline']//input[@name='Billing_City_c__c']");
    private By billing_Country_Loc = By.xpath("//label[text()='Billing Country_c']/ancestor::lightning-input[@variant='label-inline']//input[@name='Billing_Country_c__c']");
    private By billing_Postal_Loc = By.xpath("//label[text()='Billing Pincode_c']/ancestor::lightning-input[@variant='label-inline']//input[@name='Billing_Pincode__c']");
    //Methods
    public void putAccountName(String accName){
        eleUtil.waitForElementVisible(Account_Name_Loc, 25);
        eleUtil.doSendKeys(Account_Name_Loc,accName);
    }

    public void putAccountNumber(String accNumber){
        eleUtil.waitForElementVisible(Account_Number_Loc, 25);
        eleUtil.doSendKeys(Account_Number_Loc,accNumber);
    }

    public void putAccountDescription(String desc){
        eleUtil.waitForElementVisible(descriptionLoc, 25);
        eleUtil.doSendKeys(descriptionLoc,desc);
    }

    public void putShippingStreet(String street){
        eleUtil.waitForElementVisible(Shipping_Street_Loc, 25);
        eleUtil.doSendKeys(Shipping_Street_Loc,street);
    }

    public void putShippingState(String state){
        eleUtil.waitForElementVisible(Shiiping_State_Loc, 25);
        eleUtil.doSendKeys(Shiiping_State_Loc,state);
    }

    public void putShippingCity(String city){
        eleUtil.waitForElementVisible(Shipping_city_Loc, 25);
        eleUtil.doSendKeys(Shipping_city_Loc,city);
    }

    public void putShippingCountry(String country){
        eleUtil.waitForElementVisible(Shiiping_Country_Loc, 25);
        eleUtil.doSendKeys(Shiiping_Country_Loc,country);
    }

    public void putShippingzipCode(String zipcode){
        eleUtil.waitForElementVisible(Shipping_Postal_Loc, 25);
        eleUtil.doSendKeys(Shipping_Postal_Loc,zipcode);
    }

    public void selectingRatingField(){
        javaScriptUtil.waitForPageLoad(20);
        javaScriptUtil.selectingDrpDownValuesDynamically(Rating_Loc,"//span[text()='Warm']");
    }

    public void selectingTypeField(){
        javaScriptUtil.waitForPageLoad(20);
        javaScriptUtil.selectingDrpDownValuesDynamically(TypeLoc,"//span[text()='Customer - Direct']");
    }

    public void selectingIndustryField(){
        javaScriptUtil.waitForPageLoad(20);
        javaScriptUtil.selectingDrpDownValuesDynamically(IndustryLoc,"//span[text()='Communications']");
    }

    public void clickSaveBtn(){
        while(true){
            eleUtil.waitForElementsVisible(saveBtnLoc, 25);
            eleUtil.doClick(saveBtnLoc);
            break;
        }
    }
   //Below Method for AccountCreation --- Start
    public AccountDetailPage CreateNewAccount(String accName,String accNumber,String description,String sStreet,
                                              String sState,String sCity, String sCounty,String sPostalCode){
        boolean flag = false;
        while (true){
            putAccountName(accName);
            putAccountNumber(accNumber);
            selectingRatingField();
            selectingTypeField();
            selectingIndustryField();
            putAccountDescription(description);
            putShippingStreet(sStreet);
            putShippingState(sState);
            putShippingCity(sCity);
            putShippingCountry(sCounty);
            putShippingzipCode(sPostalCode);
            javaScriptUtil.waitForPageLoad(25);
            clickSaveBtn();
            break;
        }
        javaScriptUtil.waitForPageLoad(25);
        return new AccountDetailPage(driver);
    }
    //Account Creation Methods -- Ends

    //Updating Billing Address on Account by clicking Edit button --- Start
    public void putBillingStreet(String billingStreet){
        eleUtil.waitForElementVisible(billing_Street_Loc, 25);
        eleUtil.doSendKeys(billing_Street_Loc,billingStreet);
    }

    public void putBillingState(String billingState){
        eleUtil.waitForElementVisible(billing_State_Loc, 25);
        eleUtil.doSendKeys(billing_State_Loc,billingState);
    }

    public void putBillingCity(String billingCity){
        eleUtil.waitForElementVisible(billing_city_Loc, 25);
        eleUtil.doSendKeys(billing_city_Loc,billingCity);
    }

    public void putBillingCountry(String billingCountry){
        eleUtil.waitForElementVisible(billing_Country_Loc, 25);
        eleUtil.doSendKeys(billing_Country_Loc,billingCountry);
    }

    public void putBillingzipCode(String billingZipcode){
        eleUtil.waitForElementVisible(billing_Postal_Loc, 25);
        eleUtil.doSendKeys(billing_Postal_Loc,billingZipcode);
    }

    public AccountDetailPage updateBillingAddressByClickingEditbutton(String bStreet, String bState, String bCity, String bcountry, String bZipcode){
        javaScriptUtil.waitForPageLoad(30);
        putBillingStreet(bStreet);
        putBillingState(bState);
        putBillingCity(bCity);
        putBillingCountry(bcountry);
        putBillingzipCode(bZipcode);
        javaScriptUtil.waitForPageLoad(25);
        clickSaveBtn();
        System.out.println("Current Position of the driver" + driver.getCurrentUrl());
        return new AccountDetailPage(driver);
    }




    //Updating Billing Address on Account by clicking Edit button --- Ends



}
