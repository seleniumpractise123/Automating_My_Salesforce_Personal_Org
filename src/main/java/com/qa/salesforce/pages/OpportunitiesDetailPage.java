package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class OpportunitiesDetailPage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;
    public OpportunitiesDetailPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }

    private By fetchOpportunityStagefieldValue_Loc = By.xpath("//span[text()='Stage']//ancestor::records-record-layout-item[@field-label='Stage']//lightning-formatted-text[@slot='outputField']");
    private By clickingOppDetailTab_Loc = By.xpath("//lightning-tab-bar[@variant='standard']/ul[@role='tablist']//li[@data-label='Details']/a[@data-label='Details']");
    private By fetchOpportunityCustomIDFieldValue_Loc = By.xpath("//span[text()='Custom_Opportunity ID']/ancestor::records-record-layout-item[@field-label='Custom_Opportunity ID']//lightning-formatted-text[@slot='outputField']");
    private By moreBtn_Loc = By.xpath("(//div[contains(@class,'actionsContainer')]//ul[@class='slds-button-group-list' and @role='presentation'])[position()=1]/li[contains(@class,'slds-dropdown-trigger_clic')]//button");
    private By getTypePicklistFieldValue_Loc = By.xpath("//span[text()='Type']/ancestor::records-record-layout-item[@field-label='Type']//lightning-formatted-text[@slot='outputField']");
    private By getLeadSourcePicklistFieldValue_Loc = By.xpath("//span[text()='Lead Source']/ancestor::records-record-layout-item[@field-label='Lead Source']//lightning-formatted-text[@slot='outputField']");
    private By setClickingOpportunityRelatedListTa_Loc = By.xpath("//lightning-tab-bar[@variant='standard']/ul/li[@title='Related Records' and @role='presentation']/a");
    private By setClickingAddProductsButtonOnProductRelatedlist_loc = By.xpath("//div[text()='Add Products']/parent::a[@title='Add Products']");
    private By clickingMarkStageBtn_Loc = By.xpath("//div[@class='runtime_sales_pathassistantCollapsibleDrawer']//button[contains(@class,'slds-path__mark-complete')]");
    private By capturingOppStageFieldValue_Loc = By.xpath("//span[text()='Stage']/ancestor::records-record-layout-item[@field-label='Stage']//div[@class='slds-form-element__control']//lightning-formatted-text[@slot='outputField']");
    private By selectingclosedStatus_Loc = By.xpath("//span[text()='Stage']/ancestor::div[contains(@class,'slds-p-around_medium')]//select");
    private By clickingSaveBtnOnCloseThisOpportunityPage_Loc = By.xpath("//span[text()='Save']/parent::button[@type='button' and @title='Save']");
    private By clickingConfirmBtn_Loc = By.xpath("//span[text()='Delete']/parent::button[@type='button' and @title='Delete']");


    public void doClickDetailTab(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(400);
            javaScriptUtil.clickElementByJS(clickingOppDetailTab_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public String setFetchOpportunityStage(){
        String OppStageFieldValue = null;
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            OppStageFieldValue = eleUtil.doGetElementText(fetchOpportunityStagefieldValue_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return OppStageFieldValue;
    }

    public String getOpportunityCurrentURL(){
        String oppUrl = driver.getCurrentUrl();
        return oppUrl;
    }

    public String fetchOpportunityID(){
        String oppCustomID = null;
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            oppCustomID = eleUtil.doGetElementText(fetchOpportunityCustomIDFieldValue_Loc);
            System.out.println("Value of the oppCustomID=====>"+oppCustomID);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return oppCustomID;

    }
    public OpportunitiesPage clickEditBtnFromMoreBtn(String action){
        try {
            Thread.sleep(20000);
            javaScriptUtil.waitForPageLoad(30);
            javaScriptUtil.selectingDrpDownValuesDynamically(moreBtn_Loc,"//span[text()='"+action+"']/parent::a[@role='menuitem']");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new OpportunitiesPage(driver);
    }

    public String setGetTypePicklistFieldValue(){
        String TypePicklistValue = null;
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.drawBorder(driver.findElement(getTypePicklistFieldValue_Loc));
            TypePicklistValue = eleUtil.doGetElementText(getTypePicklistFieldValue_Loc);
            javaScriptUtil.waitForPageLoad(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return TypePicklistValue;

    }

    public String setGetLeadSourcePicklistFieldValue(){
        String LeadSourcePicklistValue = null;
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.drawBorder(driver.findElement(getLeadSourcePicklistFieldValue_Loc));
            LeadSourcePicklistValue = eleUtil.doGetElementText(getLeadSourcePicklistFieldValue_Loc);
            javaScriptUtil.waitForPageLoad(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return LeadSourcePicklistValue;

    }

    public void setSetClickingOpportunityRelatedListTab(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(300);
            javaScriptUtil.drawBorder(driver.findElement(setClickingOpportunityRelatedListTa_Loc));
            eleUtil.doMoveToElementClick(setClickingOpportunityRelatedListTa_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public ProductsHomePage setClickingAddProductBtnOnOpportunityRelatedList(){
        try {
            //driver.navigate().refresh();
            Thread.sleep(10000);
            eleUtil.checkElementClickable(setClickingAddProductsButtonOnProductRelatedlist_loc,250);
            eleUtil.doMoveToElementClick(setClickingAddProductsButtonOnProductRelatedlist_loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
      return new ProductsHomePage(driver);
    }

    public ProductDetailPage clickingRecentlyCreatedProductRecord(String pName){
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            By productLink_Loc = By.linkText(pName);
            eleUtil.doMoveToElementClick(productLink_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new ProductDetailPage(driver);
    }

    public void doClickMarkStatusAsCompleteBtn(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(250);
            javaScriptUtil.drawBorder(driver.findElement(clickingMarkStageBtn_Loc));
            eleUtil.doMoveToElementClick(clickingMarkStageBtn_Loc);
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public String setFetchOpportunityStagefieldValue(){
        String oppStageValue = null;
        try{
            driver.navigate().refresh();
            doClickDetailTab();
            eleUtil.waitForElementsVisible(capturingOppStageFieldValue_Loc, 200);
            oppStageValue = eleUtil.doGetElementText(capturingOppStageFieldValue_Loc);
            System.out.println("Value of the oppStageValue==========>"+oppStageValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return oppStageValue;
    }

    public String changingTheStageToClosedWon(String stageText){
        String oppClosedWONStatus = null;
        try{
            eleUtil.waitForElementsVisible(selectingclosedStatus_Loc, 200);
            eleUtil.doSelectDropDownByVisibleText(selectingclosedStatus_Loc, stageText);
            eleUtil.doMoveToElementClick(clickingSaveBtnOnCloseThisOpportunityPage_Loc);
            javaScriptUtil.waitForPageLoad(150);
            driver.navigate().refresh();
            doClickDetailTab();
            eleUtil.waitForElementsVisible(capturingOppStageFieldValue_Loc, 200);
            oppClosedWONStatus = eleUtil.doGetElementText(capturingOppStageFieldValue_Loc);
            System.out.println("Value of the oppStageValue==========>"+oppClosedWONStatus);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return oppClosedWONStatus;
    }

    public OpportunitiesHomePage navigateToOpportunitiesHomePage(){
        javaScriptUtil.waitForPageLoad(40);
        driver.navigate().back();
        driver.navigate().refresh();
        javaScriptUtil.waitForPageLoad(40);
        return new OpportunitiesHomePage(driver);
    }

    public OpportunitiesHomePage clickingConfirmBtn(){
        eleUtil.waitForElementsVisible(clickingConfirmBtn_Loc, 200);
        eleUtil.doMoveToElementClick(clickingConfirmBtn_Loc);
        javaScriptUtil.waitForPageLoad(150);
        return new OpportunitiesHomePage(driver);
    }


}
