package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class CasesDetailPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;
    public CasesDetailPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }

    private By fetchStatusField_Loc = By.xpath("//span[text()='Status']//ancestor::records-record-layout-item//slot[@name='outputField']/lightning-formatted-text[@slot='outputField']");
    private By fetchCustomCaseID_Loc = By.xpath("//span[text()='Custom Case ID']//ancestor::records-record-layout-item[@field-label='Custom Case ID']//slot[@name='outputField']/lightning-formatted-text");
    private By clickingEditBtnFromCaseDetailPage_Loc = By.xpath("//div[contains(@class,'actionsContainer')]//li[@data-target-selection-name='sfdc:StandardButton.Case.Edit']//button[@name='Edit']");
    private By fetchProductFieldValue_Loc = By.xpath("//span[text()='Product']//ancestor::records-record-layout-item[@field-label='Product']//lightning-formatted-text[@slot='outputField']");
    private By clickingPencilBtn_Loc = By.xpath("//span[text()='Potential Liability']//ancestor::records-record-layout-item[@field-label='Potential Liability']//div[@class='slds-form-element__control']//button[@title='Edit Potential Liability']");
    private By clickinPotentialPicklistBtn_Loc = By.xpath("//label[text()='Potential Liability']//ancestor::records-record-layout-item[@field-label='Potential Liability']//button[@type='button' and @aria-label='Potential Liability']");
    private By clickinSLAViolationPicklistBtn_Loc = By.xpath("//label[text()='SLA Violation']//ancestor::records-record-layout-item[@field-label='SLA Violation']//button[@type='button' and @aria-label='SLA Violation']");
    private By enteringEngReqNumberFieldValue_Loc = By.xpath("//label[text()='Engineering Req Number']//ancestor::records-record-layout-item[@field-label='Engineering Req Number']//input[@name='EngineeringReqNumber__c']");
    private By enteringInternalCommentsField_Loc = By.xpath("//label[text()='Internal Comments']//ancestor::records-record-layout-item[@field-label='Internal Comments']//textarea[@part='textarea']");
    private By clickingSaveBtnFromDetailPage_Loc = By.xpath("//records-form-footer[contains(@class,'slds-docked-form-footer')]//runtime_platform_actions-action-renderer[@apiname='SaveEdit']//button[@name='SaveEdit' and @type='button']");
    private By clickingConfirmBtn_Loc = By.xpath("//span[text()='Delete']/parent::button[@type='button' and @title='Delete']");
    private By clcikingDeleteBtnFromCaseDetailPage_Loc = By.xpath("(//div[contains(@class,'slds-grid_vertical-align-center')])[position()=3]//li[@data-target-selection-name='sfdc:StandardButton.Case.Delete']//button[@name='Delete']");
    private By fetchCaseProductfieldValue_Loc = By.xpath("//span[text()='Product']//ancestor::records-record-layout-item[@field-label='Product']//lightning-formatted-text[@slot='outputField']");
    private By clickingMarkStageBtn_Loc = By.xpath("//div[@class='runtime_sales_pathassistantCollapsibleDrawer']//button[contains(@class,'slds-path__mark-complete')]");


    public String fetchCaseStatusFieldValue(){
        String CaseStatus = null;
        //driver.navigate().refresh();
        try {
            Thread.sleep(25000);
            javaScriptUtil.drawBorder(driver.findElement(fetchStatusField_Loc));
            CaseStatus = eleUtil.doGetElementText(fetchStatusField_Loc);
            System.out.println("Value of the CaseStatus==========>"+CaseStatus);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return CaseStatus;
    }

    public String fetchCaseProductFieldValue(){
        String CaseProduct = null;
        try {
            driver.navigate().refresh();

            Thread.sleep(25000);
            javaScriptUtil.scrollIntoView(driver.findElement(By.xpath("//span[text()='Case Reason']")));
            Thread.sleep(10000);
            javaScriptUtil.drawBorder(driver.findElement(fetchCaseProductfieldValue_Loc));
            CaseProduct = eleUtil.doGetElementText(fetchCaseProductfieldValue_Loc);
            System.out.println("Value of the CaseProduct==========>"+CaseProduct);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return CaseProduct;
    }

    public String getCaseCurrentURL(){
        javaScriptUtil.waitForPageLoad(300);
        String caseUrl = driver.getCurrentUrl();
        return caseUrl;
    }

    public String setFetchCustomCaseID(){
        String Custom_CaseID = null;
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            Custom_CaseID = eleUtil.doGetElementText(fetchCustomCaseID_Loc);
            System.out.println("Value of the Custom_CaseID=========>"+Custom_CaseID);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return Custom_CaseID;

    }

    public Cases doClickEditBtnFromCaseDetailPage(){
        driver.navigate().refresh();
        eleUtil.waitForElementVisible(clickingEditBtnFromCaseDetailPage_Loc,300);
        eleUtil.doMoveToElementClick(clickingEditBtnFromCaseDetailPage_Loc);
        javaScriptUtil.waitForPageLoad(200);
        return new Cases(driver);
    }

    public String fetchProductFieldValue(){
        String product = null;
        driver.navigate().refresh();
        try {
            Thread.sleep(20000);
            javaScriptUtil.drawBorder(driver.findElement(fetchProductFieldValue_Loc));
            product = eleUtil.doGetElementText(fetchProductFieldValue_Loc);
            System.out.println("Value of the product==========>"+product);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return product;
    }

    public void doClickInlineEditBtn(){
        try {
            Thread.sleep(20000);
            eleUtil.doMoveToElementClick(clickingPencilBtn_Loc);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void doClickPotentialPicklistPicklist(String pickValue){
        //driver.navigate().refresh();
        try {
            Thread.sleep(20000);
            javaScriptUtil.selectingDrpDownValuesDynamically(clickinPotentialPicklistBtn_Loc, "//span[text()='"+pickValue+"']");
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void doClickSLAViolationPicklistPicklist(String SLApickValue){
        try {
            Thread.sleep(20000);
            javaScriptUtil.selectingDrpDownValuesDynamically(clickinSLAViolationPicklistBtn_Loc, "//span[text()='"+SLApickValue+"']");
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void doEnterEngReqNumberFieldValue(String engReqValue){
        try {
            Thread.sleep(20000);
            eleUtil.doSendKeys(enteringEngReqNumberFieldValue_Loc, engReqValue);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void doEnterInternalCommentsFieldValue(String comments){
        try {
            Thread.sleep(20000);
            eleUtil.doSendKeys(enteringInternalCommentsField_Loc, comments);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void doClickSaveBtnOnDetailPage(){
        try {
            Thread.sleep(10000);
            eleUtil.doClick(clickingSaveBtnFromDetailPage_Loc);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void editingRecentlyCaseByUsingInlineEdiProcess(String pickValue,String engReqValue,String SLApickValue,String comments){
        doClickPotentialPicklistPicklist(pickValue);
        doEnterEngReqNumberFieldValue(engReqValue);
        doClickSLAViolationPicklistPicklist(SLApickValue);
        doEnterInternalCommentsFieldValue(comments);
        doClickSaveBtnOnDetailPage();
        javaScriptUtil.waitForPageLoad(200);
    }

    public void doClickDeleteBtn(){
        eleUtil.waitForElementsVisible(clcikingDeleteBtnFromCaseDetailPage_Loc, 200);
        eleUtil.doClick(clcikingDeleteBtnFromCaseDetailPage_Loc);
        javaScriptUtil.waitForPageLoad(200);
    }

    public CasesHomePage doClickConfirmDeleteBtn(){
        eleUtil.waitForElementsVisible(clickingConfirmBtn_Loc, 200);
        eleUtil.doClick(clickingConfirmBtn_Loc);
        javaScriptUtil.waitForPageLoad(200);
        return  new CasesHomePage(driver);
    }

    public void doClickMarkStatusAsCompleteBtn(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(250);
            javaScriptUtil.drawBorder(driver.findElement(clickingMarkStageBtn_Loc));
            //eleUtil.doMoveToElementClick(clickingMarkStageBtn_Loc);
            actions.moveToElement(driver.findElement(clickingMarkStageBtn_Loc)).doubleClick().build().perform();
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}
