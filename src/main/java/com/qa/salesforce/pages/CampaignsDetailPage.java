package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class CampaignsDetailPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;
    public CampaignsDetailPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }

    private By clickingDetailTab_Loc = By.xpath("//span[text()='Details']/parent::a[@title='Details' and @class='tabHeader']");
    private By fetchTypeFieldValue_Loc = By.xpath("//span[text()='Type']//ancestor::div[@data-target-selection-name='sfdc:RecordField.Campaign.Type']//span[contains(@class,'slds-form-element__static')]/span");
    private By fetchStatusFieldValue_Loc = By.xpath("//span[text()='Status']//ancestor::div[@data-target-selection-name='sfdc:RecordField.Campaign.Status']//span[contains(@class,'slds-form-element__static')]/span");
    private By fetchCampaignNameFieldValue_Loc = By.xpath("//span[text()='Campaign Name']//ancestor::div[@data-target-selection-name='sfdc:RecordField.Campaign.Name']//span[contains(@class,'slds-form-element__static')]/span");
    private By fetchCamapignCustomIDFieldValue_Loc = By.xpath("//span[text()='Campaign ID']//ancestor::div[@data-target-selection-name='sfdc:RecordField.Campaign.Campaign_ID__c']//span[contains(@class,'slds-form-element__static')]/span");



    public void doClickDetailTab(){
        eleUtil.clickElementWhenReady(clickingDetailTab_Loc,200);
        eleUtil.doMoveToElementClick(clickingDetailTab_Loc);
        javaScriptUtil.waitForPageLoad(200);
    }

    public String fetchCampaignTypeFieldValue() {
        String camType = null;
        eleUtil.waitForElementVisible(fetchTypeFieldValue_Loc,200);
        camType = eleUtil.doGetElementText(fetchTypeFieldValue_Loc);
        System.out.println("Value of the camType=============>"+camType);
        return camType;
    }

    public String fetchCampaignStatusFieldValue() {
        String camStatus = null;
        eleUtil.waitForElementVisible(fetchStatusFieldValue_Loc,200);
        camStatus = eleUtil.doGetElementText(fetchStatusFieldValue_Loc);
        System.out.println("Value of the camStatus=============>"+camStatus);
        return camStatus;
    }

    public String fetchCampaignNameFieldValue() {
        String camName = null;
        eleUtil.waitForElementVisible(fetchCampaignNameFieldValue_Loc,200);
        camName = eleUtil.doGetElementText(fetchCampaignNameFieldValue_Loc);
        System.out.println("Value of the camName=============>"+camName);
        return camName;
    }

    public String fetchCampaignCustomIDFieldValue() {
        String camCustomID = null;
        eleUtil.waitForElementVisible(fetchCamapignCustomIDFieldValue_Loc,200);
        camCustomID = eleUtil.doGetElementText(fetchCamapignCustomIDFieldValue_Loc);
        System.out.println("Value of the camCustomID=============>"+camCustomID);
        return camCustomID;
    }

    public String getCampaignCurrentURL(){
        return driver.getCurrentUrl();
    }
}
