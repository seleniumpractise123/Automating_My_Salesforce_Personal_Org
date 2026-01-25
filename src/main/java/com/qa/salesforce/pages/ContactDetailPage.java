package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.apache.commons.collections4.splitmap.AbstractIterableGetMapDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ContactDetailPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;

    public ContactDetailPage(WebDriver driver) {
        //TODO Auto-generated constructor stub

        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }




    private By clickingDetailTabOnContact_Loc = By.xpath("(//lightning-tab-bar[@exportparts='tab-bar, tab-item'])[position()=1]//li[@data-tab-value='detailTab']/a");
    private By fetchLeadSourceField_Loc = By.xpath("//span[text()='Lead Source']//ancestor::records-record-layout-row[@class='slds-form__row']//lightning-formatted-text[@slot='outputField']");
    private By fetchDepartmentField_Loc = By.xpath("//span[text()='Department']//ancestor::records-record-layout-row[@class='slds-form__row']//lightning-formatted-text[@slot='outputField']");
    private By contactIDFieldValue_Loc = By.xpath("//span[text()='Contact ID']//ancestor::records-record-layout-row[@class='slds-form__row']//div[@class='slds-form-element__control']//lightning-formatted-text[@slot='outputField']");
    private By moreBtn_Loc = By.xpath("(//div[contains(@class,'actionsContainer')]//ul[@class='slds-button-group-list' and @role='presentation'])[position()=1]/li[contains(@class,'slds-dropdown-trigger_clic')]//button");
    private By fetchLevlField_Loc = By.xpath("(//span[text()='Level']//ancestor::records-record-layout-row[@class='slds-form__row']//lightning-formatted-text[@slot='outputField'])[position()=2]");
    private By clickingInlineEditBtn_Loc = By.xpath("//records-record-layout-item[@field-label='Home Phone']//div[@class='slds-form-element__control']/button[@title='Edit Home Phone']");
    private By enteringHomePhoneFieldValue_Loc = By.xpath("//label[text()='Home Phone']//ancestor::records-record-layout-item[@field-label='Home Phone']//input[@name='HomePhone']");
    private By enteringOtherPhoneFieldValue_Loc = By.xpath("//label[text()='Other Phone']//ancestor::records-record-layout-item[@field-label='Other Phone']//input[@name='OtherPhone']");
    private By enteringAssistantNameFieldValue_Loc = By.xpath("//label[text()='Assistant']//ancestor::records-record-layout-item[@field-label='Assistant']//input[@name='AssistantName']");
    private By enteringAssistPhoneFieldValue_Loc = By.xpath("//label[text()='Asst. Phone']//ancestor::records-record-layout-item[@field-label='Asst. Phone']//input[@name='AssistantPhone']");
    private By clickingSaveBtnOnDetailPage_Loc = By.xpath("//div[@class='record-body-container']//records-form-footer[contains(@class,'slds-docked-form-footer')]//ul[@role='presentation']//button[@name='SaveEdit']");
    private By fetchHomePhoneFieldValue_Loc = By.xpath("//span[text()='Home Phone']//ancestor::records-record-layout-item[@field-label='Home Phone']//lightning-formatted-phone/a");
    private By clickingDetailTabThroughReport_Loc = By.xpath("//a[text()='Details']");
    private By clickingDetailTabAfterReportLoc = By.xpath("//a[text()='Details']");
    private By clickingConfirmingDeleteBtn_Loc = By.xpath("(//span[text()='Delete'])[position()=2]/parent::button[@title='Delete']");


    public void clickDetailTabBtn(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(200);
            javaScriptUtil.drawBorder(driver.findElement(clickingDetailTabOnContact_Loc));
            eleUtil.doMoveToElementClick(clickingDetailTabOnContact_Loc);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickDetailTabBtnThroughReports(){
        try {
            Thread.sleep(15000);
            driver.navigate().refresh();
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.drawBorder(driver.findElement(clickingDetailTabThroughReport_Loc));
            eleUtil.doMoveToElementClick(clickingDetailTabThroughReport_Loc);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickDetailTabBtnWhichCameFromAccounts(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.drawBorder(driver.findElement(clickingDetailTabOnContact_Loc));
            //eleUtil.doMoveToElementClick(clickingDetailTabOnContact_Loc);
            javaScriptUtil.clickElementByJS(clickingDetailTabOnContact_Loc);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getLeadSourceFieldValue(){
        String leadSourceFieldValue = null;
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            leadSourceFieldValue = eleUtil.doGetElementText(fetchLeadSourceField_Loc);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return leadSourceFieldValue;
    }

    public String getDepartmentFieldValue(){
        String departmentFieldValue = null;
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            departmentFieldValue = eleUtil.doGetElementText(fetchDepartmentField_Loc);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return departmentFieldValue;
    }

    public String getHomePhoneFieldValue(){
        String finalHomePhoneFieldValue = null;
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            String homePhoneFieldValue = eleUtil.doGetElementText(fetchHomePhoneFieldValue_Loc);
            finalHomePhoneFieldValue = homePhoneFieldValue.replaceAll("[^0-9]", "");
            System.out.println("Value of the finalHomePhoneFieldValue====>" + finalHomePhoneFieldValue);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return finalHomePhoneFieldValue;
    }

    public String getCurrentURL(){
        javaScriptUtil.waitForPageLoad(30);
        String currentURL = driver.getCurrentUrl();
        System.out.println("Value of the currentUTR===>"+currentURL);
        return currentURL;
    }

    public String getContactID(){
        String finalContactID = null;
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            finalContactID = eleUtil.doGetElementText(contactIDFieldValue_Loc);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return finalContactID;
    }

    public Contacts clickEditBtnFromMoreBtn(){
        try {
            Thread.sleep(20000);
            javaScriptUtil.waitForPageLoad(30);
            javaScriptUtil.selectingDrpDownValuesDynamically(moreBtn_Loc,"//span[text()='Edit']/parent::a[@role='menuitem']");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new Contacts(driver);
    }

    public String getLevelFieldValue(){
        String levelFieldValue = null;
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            levelFieldValue = eleUtil.doGetElementText(fetchLevlField_Loc);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return levelFieldValue;
    }

    public void doClickInlineEditBtn(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doMoveToElementClick(clickingInlineEditBtn_Loc);
            Thread.sleep(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void doEnterHomePhoneFieldValue(String homePhone){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringHomePhoneFieldValue_Loc,homePhone);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void doEnterOtherPhoneFieldValue(String otherPhone){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringOtherPhoneFieldValue_Loc,otherPhone);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void doEnterAssistantNameFieldValue(String assistantName){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringAssistantNameFieldValue_Loc,assistantName);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void doEnterAssistantPhoneFieldValue(String assistantPhone){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringAssistPhoneFieldValue_Loc,assistantPhone);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void doClickSaveBtnOnDetailPage(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doMoveToElementClick(clickingSaveBtnOnDetailPage_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void editingContactDetailPageByClickingInlineEditing(String homePhone,String otherPhone,String assistantName,String assistantPhone){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            doEnterHomePhoneFieldValue(homePhone);
            doEnterOtherPhoneFieldValue(otherPhone);
            doEnterAssistantNameFieldValue(assistantName);
            doEnterAssistantPhoneFieldValue(assistantPhone);
            doClickSaveBtnOnDetailPage();
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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


    public ContactsHomePage setClickingConfirmingDeleteBtn(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.drawBorder(driver.findElement(clickingConfirmingDeleteBtn_Loc));
            eleUtil.doMoveToElementClick(clickingConfirmingDeleteBtn_Loc);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new ContactsHomePage(driver);
    }

    public ContactsHomePage navigateBackToAccountsHome(){
        javaScriptUtil.waitForPageLoad(40);
        driver.navigate().back();
        driver.navigate().refresh();
        javaScriptUtil.waitForPageLoad(40);
        return new ContactsHomePage(driver);

    }



}
