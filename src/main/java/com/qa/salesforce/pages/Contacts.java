package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v137.animation.model.KeyframeStyle;
import org.openqa.selenium.interactions.Actions;

import java.security.Key;

public class Contacts {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;

    public Contacts(WebDriver driver) {
        //TODO Auto-generated constructor stub

        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }

    private By firstName_Loc = By.xpath("//label[text()='First Name']/ancestor::lightning-primitive-input-simple[@variant='standard']//input[@name='firstName' and @placeholder='First Name']");
    private By lastName_Loc = By.xpath("//label[text()='Last Name']/ancestor::lightning-primitive-input-simple[@variant='standard']//input[@name='lastName' and @placeholder='Last Name']");
    private By accountName_Loc = By.xpath("//label[text()='Account Name']/ancestor::lightning-lookup-desktop//input[contains(@placeholder,'Search Accounts...')]");
    private By clickingSearchIcon = By.xpath("(//lightning-base-combobox-item[@data-value='actionAdvancedSearch' and @role='option']//span)[2]");
    private By selectingAccountValue_loc = By.xpath("//div[@class='dt-outer-container']//table[contains(@class,'slds-table_resizable-cols')]//tbody/tr[@class='slds-hint-parent']//td[@role='gridcell']//span[@class='slds-radio']/input[@type='radio']");
    private By clickingSelectBtnOnLookupPage_Loc = By.xpath("//lightning-modal-footer[@class='lightning-lookup-advanced-modal__footer']//button[@kx-scope='button-brand']");
    private By departmentField_Loc = By.xpath("//label[text()='Department']/ancestor::records-record-layout-base-input[@slot='inputField']//input[@name='Department']");
    private By phoneField_Loc = By.xpath("//label[text()='Phone']/ancestor::lightning-input[@slot='inputField']//input[@name='Phone']");
    private By mobilePhoneField_Loc = By.xpath("//label[text()='Mobile']/ancestor::lightning-input[@slot='inputField']//input[@name='MobilePhone']");
    private By emailField_Loc = By.xpath("//label[text()='Email']/ancestor::lightning-input[@slot='inputField']//input[@name='Email']");
    private By enteringDescriptionField_Loc = By.xpath("//label[text()='Description']/ancestor::records-record-layout-text-area[@slot='inputField']//textarea[@part='textarea']");
    private By clickingLeadSourceField_Loc = By.xpath("//label[text()='Lead Source']/ancestor::records-record-picklist[@slot='inputField']//button[@part='input-button' and @type='button']");
    private By clickingSaveBtnOnContactCretionPage_Loc = By.xpath("//records-form-footer[contains(@class,'slds-docked-form-footer')]//button[@name='SaveEdit']");
    private By enteringmailingaddress_Loc = By.xpath("//label[text()='Mailing_Street_c']/ancestor::records-record-layout-item[@field-label='Mailing_Street_c']//textarea[@class='slds-textarea']");
    private By enteringmailingCity_Loc = By.xpath("//label[text()='Mailing_City_c']/ancestor::records-record-layout-item[@field-label='Mailing_City_c']//input[@name='Mailing_City_c__c']");
    private By enteringMailingState_Loc = By.xpath("//label[text()='Mailing_State_c']/ancestor::records-record-layout-item[@field-label='Mailing_State_c']//input[@name='Mailing_State_c__c']");
    private By enteringmailingcountry_Loc = By.xpath("//label[text()='Mailing_Country_c']/ancestor::records-record-layout-item[@field-label='Mailing_Country_c']//input[@name='Mailing_Country_c__c']");
    private By enteringmailingpostalcode_Loc = By.xpath("//label[text()='Mailing_PostalCode_c']/ancestor::records-record-layout-item[@field-label='Mailing_PostalCode_c']//input[@name='Mailing_PostalCode_c__c']");
    private By enteringLanguageField_Loc = By.xpath("//label[text()='Languages']/ancestor::records-record-layout-item[@field-label='Languages']//input[@name='Languages__c']");
    private By clickingLevelFieldBtn_Loc = By.xpath("//label[text()='Level']/ancestor::records-record-layout-item[@field-label='Level']//button[@type='button' and @role='combobox']");




    public void setFirstNameFieldValue(String fName){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(firstName_Loc, 100, 10);
        eleUtil.doSendKeys(firstName_Loc,fName);
    }

    public void setLastNameFieldValue(String lName){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(lastName_Loc, 100, 10);
        eleUtil.doSendKeys(lastName_Loc,lName);
    }

    public void setAccountNameFieldValue(String custID){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(accountName_Loc, 100, 10);
        javaScriptUtil.drawBorder(driver.findElement(accountName_Loc));
        eleUtil.doClick(accountName_Loc);
        eleUtil.doSendKeys(accountName_Loc,custID);
        actions.moveToElement(driver.findElement(accountName_Loc)).sendKeys(Keys.ENTER).build().perform();
        eleUtil.waitForElementVisibleWithFluentWait(clickingSearchIcon, 100, 10);
        javaScriptUtil.drawBorder(driver.findElement(clickingSearchIcon));
        eleUtil.doClick(clickingSearchIcon);
        eleUtil.waitForElementVisibleWithFluentWait(selectingAccountValue_loc, 100, 10);
        javaScriptUtil.drawBorder(driver.findElement(selectingAccountValue_loc));
        javaScriptUtil.clickElementByJS(selectingAccountValue_loc);
        eleUtil.waitForElementVisibleWithFluentWait(clickingSelectBtnOnLookupPage_Loc, 100, 10);
        javaScriptUtil.drawBorder(driver.findElement(clickingSelectBtnOnLookupPage_Loc));
        javaScriptUtil.clickElementByJS(clickingSelectBtnOnLookupPage_Loc);
        //eleUtil.doMoveToElementClick(clickingSelectBtnOnLookupPage_Loc);
        //eleUtil.doClick(clickingSelectBtnOnLookupPage_Loc);
        javaScriptUtil.waitForPageLoad(150);
    }



    public void setDepartmentFieldValue(String depart){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(departmentField_Loc, 100, 10);
        eleUtil.doSendKeys(departmentField_Loc,depart);
        //actions.moveToElement(driver.findElement(departmentField_Loc)).sendKeys(depart).build().perform();
    }

    public void setPhoneFieldValue(String phone){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(phoneField_Loc, 100, 10);
        eleUtil.doSendKeys(phoneField_Loc,phone);
    }

    public void setMobilePhoneFieldValue(String phone){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(mobilePhoneField_Loc, 100, 10);
        eleUtil.doSendKeys(mobilePhoneField_Loc,phone);
    }

    public void setEMailFieldValue(String fName,String lName){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(emailField_Loc,150,10);
        String emailId = fName+lName+"@gmail.com";
        eleUtil.doSendKeys(emailField_Loc,emailId);
    }

    public void setDescriptionFieldValue(String desc){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(enteringDescriptionField_Loc,150,10);
        eleUtil.doSendKeys(enteringDescriptionField_Loc,desc);
    }

    public void setLeadSourceFieldValue(String source){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(clickingLeadSourceField_Loc,150,10);
        javaScriptUtil.selectingDrpDownValuesDynamically(clickingLeadSourceField_Loc, "//span[text()='"+source+"']");
    }

    public void doClickingSaveBtnOnCreationPage(){
        eleUtil.waitForElementVisibleWithFluentWait(clickingSaveBtnOnContactCretionPage_Loc,150,10);
        eleUtil.doClick(clickingSaveBtnOnContactCretionPage_Loc);
    }


    public ContactDetailPage creatingContactThroughContactsTab(String fName,String lName,String custID,String depart,
                                                  String phone,String desc,String source){

        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            setFirstNameFieldValue(fName);
            setLastNameFieldValue(lName);
            setAccountNameFieldValue(custID);
            setDepartmentFieldValue(depart);
            setPhoneFieldValue(phone);
            setMobilePhoneFieldValue(phone);
            setEMailFieldValue(fName,lName);
            setDescriptionFieldValue(desc);
            setLeadSourceFieldValue(source);
            doClickingSaveBtnOnCreationPage();
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new ContactDetailPage(driver);

    }

    public ContactDetailPage creatingContactThroughAccountRelatedList(String fName,String lName,String depart,
                                                               String phone,String desc,String source){

        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            setFirstNameFieldValue(fName);
            setLastNameFieldValue(lName);
            setDepartmentFieldValue(depart);
            setPhoneFieldValue(phone);
            setMobilePhoneFieldValue(phone);
            setEMailFieldValue(fName,lName);
            setDescriptionFieldValue(desc);
            setLeadSourceFieldValue(source);
            doClickingSaveBtnOnCreationPage();
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new ContactDetailPage(driver);

    }

    public void doEnterMailingAddress(String mailAddress){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(enteringmailingaddress_Loc,150,10);
        eleUtil.doSendKeys(enteringmailingaddress_Loc,mailAddress);
    }

    public void doEnterMailingCity(String mailCity){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(enteringmailingCity_Loc,150,10);
        eleUtil.doSendKeys(enteringmailingCity_Loc,mailCity);
    }

    public void doEnterMailingState(String mailState){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(enteringMailingState_Loc,150,10);
        eleUtil.doSendKeys(enteringMailingState_Loc,mailState);
    }

    public void doEnterMailingCountry(String mailCountry){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(enteringmailingcountry_Loc,150,10);
        eleUtil.doSendKeys(enteringmailingcountry_Loc,mailCountry);
    }

    public void doEnterMailingPostalCode(String mailPostalCode){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(enteringmailingpostalcode_Loc,150,10);
        eleUtil.doSendKeys(enteringmailingpostalcode_Loc,mailPostalCode);
    }

    public void doEnterLanguageFieldValue(String lang){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(enteringLanguageField_Loc,150,10);
        eleUtil.doSendKeys(enteringLanguageField_Loc,lang);
    }

    public void setLeavlFieldValue(String level){
        System.out.println("Value of the Level=====>"+level);
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(clickingLevelFieldBtn_Loc,150,10);
        javaScriptUtil.selectingDrpDownValuesDynamically(clickingLevelFieldBtn_Loc, "//span[text()='"+level+"']");
    }

    public ContactDetailPage doClickingEditButtonOnContactDetailPage(String mailAddress,String mailCity,String mailState,String mailCountry
                                                            ,String mailPostalCode,String lang,String level){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            doEnterMailingAddress(mailAddress);
            doEnterMailingCity(mailCity);
            doEnterMailingState(mailState);
            doEnterMailingCountry(mailCountry);
            doEnterMailingPostalCode(mailPostalCode);
            doEnterLanguageFieldValue(lang);
            setLeavlFieldValue(level);
            doClickingSaveBtnOnCreationPage();
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new ContactDetailPage(driver);

    }







}
