package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CampaignsPage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;
    public CampaignsPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }

    private By enteringCampaignNameField_Loc = By.xpath("(//span[text()='Campaign Name'])[2]//ancestor::div[@class='slds-form-element__control']//input[@type='text']");
    private By clickingActiveCheckbox_Loc = By.xpath("//span[text()='Active']//ancestor::div[@class='slds-form-element__control']//input[@type='checkbox']");
    private By clickingStatusField_Loc = By.xpath("(//span[text()='Status'])[2]//ancestor::div[@class='slds-form-element__control']//a[@role='button']");
    private By clickingTypeField_Loc = By.xpath("(//span[text()='Type'])[2]//ancestor::div[@class='slds-form-element__control']//a[@role='button']");
    private By enteringStartDateField_Loc = By.xpath("(//span[text()='Start Date'])[2]//ancestor::div[@class='slds-form-element__control']//input[@type='text']");
    private By enteringEndDateField_Loc = By.xpath("(//span[text()='End Date'])[2]//ancestor::div[@class='slds-form-element__control']//input[@type='text']");
    private By enteringexpectedRevuneField_Loc = By.xpath("//span[text()='Expected Revenue in Campaign']//ancestor::div[@class='slds-form-element__control']//input[@type='text']");
    private By enteringBudgetCampaignField_Loc = By.xpath("//span[text()='Budgeted Cost in Campaign']//ancestor::div[@class='slds-form-element__control']//input[@type='text']");
    private By clickingsaveBtnOnCampaigncreationPage_Loc = By.xpath("//div[@class='inlineFooter']//button[@type='button' and @title='Save']");
    private By enteringCampaignDescriptionField_Loc = By.xpath("//span[text()='Description']//ancestor::div[@class='slds-form-element__control']//textarea[@role='textbox']");
/*
    String getDate(String Days){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, Days);;
        return sdf.format(c.getTime());
    }

    public String selectDate(String addDays){
        String month = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE,addDays);
        String date = getDate(addDays);
        List<String> splitDate = date.split(" ");
        String year = splitDate.get(2);
        List<String> days = splitDate.get(1).split(",");
        String day = days.get(0);
        month = (c.get(Calendar.MONTH) + 1);
        return month + "/" + day + "/" + year;


    }

    public WebElement setCampaignStartDate(){
        return driver.findElement(enteringStartDateField_Loc);
    }

    public WebElement setCampaignEndDate(){
        return driver.findElement(enteringEndDateField_Loc);
    }

 */


    public void setEnteringCampaignNameField(String campName){
        eleUtil.waitForElementsVisible(enteringCampaignNameField_Loc, 200);
        eleUtil.doSendKeys(enteringCampaignNameField_Loc,campName);
        javaScriptUtil.waitForPageLoad(200);

    }

    public void doClickingActiveield(){
        eleUtil.waitForElementsVisible(clickingActiveCheckbox_Loc, 200);
        eleUtil.doClick(clickingActiveCheckbox_Loc);
        javaScriptUtil.waitForPageLoad(200);

    }

    public void setStatusField(String status){
        try {
            Thread.sleep(10000);
            javaScriptUtil.selectingDrpDownValuesDynamically(clickingStatusField_Loc, "//a[text()='"+status+"']");
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public void setTypeField(String type){
        try {
            Thread.sleep(10000);
            javaScriptUtil.selectingDrpDownValuesDynamically(clickingTypeField_Loc, "//a[text()='"+type+"']");
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCampaignStartDateField(){
        eleUtil.waitForElementVisible(enteringStartDateField_Loc, 200);
        eleUtil.doSendKeys(enteringStartDateField_Loc, "04/01/2026");
        javaScriptUtil.waitForPageLoad(200);
    }

    public void setCampaignEndDateField(){
        eleUtil.waitForElementVisible(enteringEndDateField_Loc, 200);
        eleUtil.doSendKeys(enteringEndDateField_Loc, "09/01/2026");
        javaScriptUtil.waitForPageLoad(200);

    }

    public void setCampaignReveuneField(String reveune){
        eleUtil.waitForElementsVisible(enteringexpectedRevuneField_Loc, 200);
        eleUtil.doSendKeys(enteringexpectedRevuneField_Loc,reveune);
        javaScriptUtil.waitForPageLoad(200);

    }

    public void setCampaignBudgetField(String budget){
        eleUtil.waitForElementsVisible(enteringBudgetCampaignField_Loc, 200);
        eleUtil.doSendKeys(enteringBudgetCampaignField_Loc,budget);
        javaScriptUtil.waitForPageLoad(200);

    }

    public void setCampaignDescriptionField(String desc){
        eleUtil.waitForElementsVisible(enteringCampaignDescriptionField_Loc, 200);
        eleUtil.doSendKeys(enteringCampaignDescriptionField_Loc,desc);
        javaScriptUtil.waitForPageLoad(200);
    }

    public void doClickSaveBtn(){
        try {
            Thread.sleep(10000);
            eleUtil.doMoveToElementClick(clickingsaveBtnOnCampaigncreationPage_Loc);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public CampaignsDetailPage creatingCampaignRecord(String campName,String status,String type,String reveune,
                                       String budget,String desc){
        setEnteringCampaignNameField(campName);
        doClickingActiveield();
        setStatusField(status);
        setTypeField(type);
        setCampaignStartDateField();
        setCampaignEndDateField();
        setCampaignReveuneField(reveune);
        setCampaignBudgetField(budget);
        setCampaignDescriptionField(desc);
        doClickSaveBtn();
        return new CampaignsDetailPage(driver);
    }






}
