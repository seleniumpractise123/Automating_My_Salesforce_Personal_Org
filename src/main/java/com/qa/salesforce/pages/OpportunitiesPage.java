package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class OpportunitiesPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;

    public OpportunitiesPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);

    }

    private By enteringOpportunityNameFieldValue_Loc = By.xpath("//label[text()='Opportunity Name']/ancestor::records-record-layout-base-input[@slot='inputField']//input[@name='Name']");
    private By enteringAccountFieldValue_Loc = By.xpath("//label[text()='Account Name']/ancestor::records-record-layout-lookup[@slot='inputField']//input[contains(@placeholder,'Search Accounts..') and @part='input']");
    private By enteringCloseDateFieldValue_Loc = By.xpath("//label[text()='Close Date']/ancestor::lightning-input[@slot='inputField']//input[@name='CloseDate']");
    private By enteringStageFieldValue_Loc = By.xpath("//label[text()='Stage']/ancestor::records-record-layout-item[@field-label='Stage']//button[@aria-label='Stage' and @type='button']");
    private By enteringDescriptionFieldValue_Loc = By.xpath("//label[text()='Description']/ancestor::records-record-layout-item[@field-label='Description']//textarea[@part='textarea']");
    private By clickingSearchIcon = By.xpath("(//lightning-base-combobox-item[@data-value='actionAdvancedSearch' and @role='option']//span)[2]");
    private By selectingAccountValue_loc = By.xpath("//div[@class='dt-outer-container']//table[contains(@class,'slds-table_resizable-cols')]//tbody/tr[@class='slds-hint-parent']//td[@role='gridcell']//span[@class='slds-radio']/input[@type='radio']");
    private By clickingSelectBtnOnLookupPage_Loc = By.xpath("//lightning-modal-footer[@class='lightning-lookup-advanced-modal__footer']//button[@kx-scope='button-brand']");
    private By clickingSaveBtn_Loc = By.xpath("//records-form-footer[contains(@class,'slds-docked-form-footer')]//runtime_platform_actions-action-renderer[@apiname='SaveEdit']//button[@name='SaveEdit']");
    private By clickingOpportunityCreationCheckbox_Loc = By.xpath("(//span[text()='Opportunity_Created_Account_Related_list'])[last()]//ancestor::slot[@name='inputField']//span[@part='input-checkbox']/input[@name='Opportunity_Created_Account_Related_list__c' and @type='checkbox']");
    private By clickingOpportunityCreationFromContactRelatedListCheckbox_Loc = By.xpath("//span[text()='Opportunity_Created_Contact_Related_list']//ancestor::slot[@name='inputField']//span[@part='input-checkbox']/input[@name='Opportunity_Created_Contact_Related_list__c' and @type='checkbox']");
    private By setClickingTypePicklistField_Loc = By.xpath("//label[text()='Type']/ancestor::records-form-picklist//lightning-base-combobox[@class='slds-combobox_container']//button[@type='button' and @role='combobox']");
    private By setClickingLeadSourcePicklistField_Loc = By.xpath("//label[text()='Lead Source']/ancestor::records-form-picklist//lightning-base-combobox[@class='slds-combobox_container']//button[@type='button' and @role='combobox']");



    public void setOpportunityFieldName(String OppName){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringOpportunityNameFieldValue_Loc,OppName);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAccountNameFieldValue(String custID){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.drawBorder(driver.findElement(enteringAccountFieldValue_Loc));
            eleUtil.doClick(enteringAccountFieldValue_Loc);
            eleUtil.doSendKeys(enteringAccountFieldValue_Loc,custID);
            actions.moveToElement(driver.findElement(enteringAccountFieldValue_Loc)).sendKeys(Keys.ENTER).build().perform();
            Thread.sleep(10000);
            javaScriptUtil.drawBorder(driver.findElement(clickingSearchIcon));
            eleUtil.doClick(clickingSearchIcon);
            Thread.sleep(10000);
            javaScriptUtil.drawBorder(driver.findElement(selectingAccountValue_loc));
            javaScriptUtil.clickElementByJS(selectingAccountValue_loc);
            Thread.sleep(10000);
            javaScriptUtil.drawBorder(driver.findElement(clickingSelectBtnOnLookupPage_Loc));
            javaScriptUtil.clickElementByJS(clickingSelectBtnOnLookupPage_Loc);
            //eleUtil.doMoveToElementClick(clickingSelectBtnOnLookupPage_Loc);
            //eleUtil.doClick(clickingSelectBtnOnLookupPage_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEnteringCloseDateField(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringCloseDateFieldValue_Loc, "03/10/2026");
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEnteringStageField(String stage){
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.selectingDrpDownValuesDynamically(enteringStageFieldValue_Loc,"(//span[text()='"+stage+"'])[last()]");
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void setClickingOpportunityCreationCheckbox(){
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(250);
            eleUtil.doMoveToElementClick(clickingOpportunityCreationCheckbox_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void setClickingOpportunityCreationFromContactRelatedListCheckbox(){
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(250);
            eleUtil.doMoveToElementClick(clickingOpportunityCreationFromContactRelatedListCheckbox_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void setEnteringDescriptionField(String desc){
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringDescriptionFieldValue_Loc,desc);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public void setClickingSaveBtn(){
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            //eleUtil.doMoveToElementClick(clickingSaveBtn_Loc);
            javaScriptUtil.clickElementByJS(clickingSaveBtn_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public OpportunitiesDetailPage createNewOpportunity(String OppName,String custID,String stage,String desc){
            boolean flag=false;
            while (true){
                setOpportunityFieldName(OppName);
                setAccountNameFieldValue(custID);
                setEnteringCloseDateField();
                setEnteringStageField(stage);
                setEnteringDescriptionField(desc);
                setClickingSaveBtn();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;

            }
        javaScriptUtil.waitForPageLoad(250);

        return new OpportunitiesDetailPage(driver);

    }

    public AccountDetailPage createNewOpportunityFromAccountRelatedList(String OppName,String stage,String desc){
        boolean flag = false;
        while(true){
            setOpportunityFieldName(OppName);
            setEnteringCloseDateField();
            setEnteringStageField(stage);
            setClickingOpportunityCreationCheckbox();
            setEnteringDescriptionField(desc);
            setClickingSaveBtn();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            break;
        }
        javaScriptUtil.waitForPageLoad(250);

        return new AccountDetailPage(driver);

    }

    public ContactDetailPage createNewOpportunityFromContactRelatedList(String OppName,String stage,String desc){
        setOpportunityFieldName(OppName);
        setEnteringCloseDateField();
        setEnteringStageField(stage);
        setClickingOpportunityCreationFromContactRelatedListCheckbox();
        setEnteringDescriptionField(desc);
        setClickingSaveBtn();

        return new ContactDetailPage(driver);

    }

    public void settingTypePicklistFieldValue(String typeValue){
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.selectingDrpDownValuesDynamically(setClickingTypePicklistField_Loc,"//span[text()='"+typeValue+"']");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void settingLeadSourcePicklistFieldValue(String leadSourceValue){
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.selectingDrpDownValuesDynamically(setClickingLeadSourcePicklistField_Loc,"//span[text()='"+leadSourceValue+"']");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public OpportunitiesDetailPage EditingRecentlyCreatedOpportunityByUsingEditButton(String typeValue,String leadSourceValue){
        boolean flag = false;
        while(true){
            javaScriptUtil.waitForPageLoad(150);
            settingTypePicklistFieldValue(typeValue);
            settingLeadSourcePicklistFieldValue(leadSourceValue);
            setClickingSaveBtn();
            try {
                Thread.sleep(10000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            break;
        }


        return new OpportunitiesDetailPage(driver);

    }
}
