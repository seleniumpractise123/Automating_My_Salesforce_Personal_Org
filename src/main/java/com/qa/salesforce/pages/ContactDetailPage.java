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
    private By contactIDFieldValue_Loc = By.xpath("//span[text()='Custom_Contact ID']//ancestor::records-record-layout-row[@class='slds-form__row']//div[@class='slds-form-element__control']//lightning-formatted-text[@slot='outputField']");
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
    private By clickingCreateNewOpportunityBtnfromContactRelatedList_Loc = By.xpath("(//li[@class='visible' and contains(@data-target-selection-name,'sfdc:StandardButton.Opportunity.New') and @role='presentation']//button[@name='New' and @type='button'])[last()]");
    private By clickingOpportunitiesHeader_Loc = By.xpath("//article[@aria-label='Opportunities']//div[contains(@class,'firstHeaderRow')]//h2[@class='slds-card__header-title']/a");
    private By clickingNewCaseCreationBtnFromAccountRelatedList_Loc = By.xpath("//li[@class='visible' and contains(@data-target-selection-name,'sfdc:StandardButton.Case.NewCase')]//button[@name='NewCase' and @type='button']");
    private By ClickingCasesHeaderLinkFromAccountRelatedList_Loc = By.xpath("//article[@aria-label='Cases']//div[contains(@class,'firstHeaderRow')]//h2[@class='slds-card__header-title']/a");

    public void clickDetailTabBtn(){
        //Thread.sleep(15000);
        javaScriptUtil.waitForPageLoad(200);
        eleUtil.waitForElementVisibleWithFluentWait(clickingDetailTabOnContact_Loc, 150, 10);
        javaScriptUtil.drawBorder(driver.findElement(clickingDetailTabOnContact_Loc));
        //eleUtil.doMoveToElementClick(clickingDetailTabOnContact_Loc);
        javaScriptUtil.clickElementByJS(clickingDetailTabOnContact_Loc);
        javaScriptUtil.waitForPageLoad(200);
    }

    public void clickDetailTabBtnThroughReports(){
        driver.navigate().refresh();
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(clickingDetailTabThroughReport_Loc, 150, 10);
        javaScriptUtil.drawBorder(driver.findElement(clickingDetailTabThroughReport_Loc));
        eleUtil.doMoveToElementClick(clickingDetailTabThroughReport_Loc);
        javaScriptUtil.waitForPageLoad(200);
    }

    public void clickDetailTabBtnWhichCameFromAccounts(){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(clickingDetailTabOnContact_Loc, 150, 10);
        javaScriptUtil.drawBorder(driver.findElement(clickingDetailTabOnContact_Loc));
        //eleUtil.doMoveToElementClick(clickingDetailTabOnContact_Loc);
        javaScriptUtil.clickElementByJS(clickingDetailTabOnContact_Loc);
        javaScriptUtil.waitForPageLoad(200);
    }

    public String getLeadSourceFieldValue(){
        String leadSourceFieldValue = null;
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(fetchLeadSourceField_Loc, 150, 10);
        leadSourceFieldValue = eleUtil.doGetElementText(fetchLeadSourceField_Loc);
        return leadSourceFieldValue;
    }

    public String getDepartmentFieldValue(){
        String departmentFieldValue = null;
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(fetchDepartmentField_Loc, 150, 10);
        departmentFieldValue = eleUtil.doGetElementText(fetchDepartmentField_Loc);
        return departmentFieldValue;
    }

    public String getHomePhoneFieldValue(){
        String finalHomePhoneFieldValue = null;
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(fetchHomePhoneFieldValue_Loc, 200, 10);
        String homePhoneFieldValue = eleUtil.doGetElementText(fetchHomePhoneFieldValue_Loc);
        finalHomePhoneFieldValue = homePhoneFieldValue.replaceAll("[^0-9]", "");
        System.out.println("Value of the finalHomePhoneFieldValue====>" + finalHomePhoneFieldValue);
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
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(contactIDFieldValue_Loc, 150, 10);
        finalContactID = eleUtil.doGetElementText(contactIDFieldValue_Loc);
        return finalContactID;
    }

    public Contacts clickEditBtnFromMoreBtn(){
        javaScriptUtil.waitForPageLoad(30);
        eleUtil.waitForElementVisibleWithFluentWait(moreBtn_Loc, 200, 10);
        javaScriptUtil.selectingDrpDownValuesDynamically(moreBtn_Loc,"//span[text()='Edit']/parent::a[@role='menuitem']");

        return new Contacts(driver);
    }

    public String getLevelFieldValue(){
        String levelFieldValue = null;
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(fetchLevlField_Loc, 200, 10);
        levelFieldValue = eleUtil.doGetElementText(fetchLevlField_Loc);
        return levelFieldValue;
    }

    public void doClickInlineEditBtn(){
        try {
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.waitForElementVisibleWithFluentWait(clickingInlineEditBtn_Loc, 200, 10);
            eleUtil.doMoveToElementClick(clickingInlineEditBtn_Loc);
            Thread.sleep(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void doEnterHomePhoneFieldValue(String homePhone){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(enteringHomePhoneFieldValue_Loc, 200, 10);
        eleUtil.doSendKeys(enteringHomePhoneFieldValue_Loc,homePhone);
        javaScriptUtil.waitForPageLoad(150);
    }

    public void doEnterOtherPhoneFieldValue(String otherPhone){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(enteringOtherPhoneFieldValue_Loc, 200, 10);
        eleUtil.doSendKeys(enteringOtherPhoneFieldValue_Loc,otherPhone);
        javaScriptUtil.waitForPageLoad(150);
    }

    public void doEnterAssistantNameFieldValue(String assistantName){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(enteringAssistantNameFieldValue_Loc, 200, 10);
        eleUtil.doSendKeys(enteringAssistantNameFieldValue_Loc,assistantName);
        javaScriptUtil.waitForPageLoad(150);
    }

    public void doEnterAssistantPhoneFieldValue(String assistantPhone){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(enteringAssistPhoneFieldValue_Loc, 200, 10);
        eleUtil.doSendKeys(enteringAssistPhoneFieldValue_Loc,assistantPhone);
        javaScriptUtil.waitForPageLoad(150);
    }

    public void doClickSaveBtnOnDetailPage(){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(clickingSaveBtnOnDetailPage_Loc, 200, 10);
        eleUtil.doMoveToElementClick(clickingSaveBtnOnDetailPage_Loc);
        javaScriptUtil.waitForPageLoad(150);
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
        javaScriptUtil.waitForPageLoad(30);
        eleUtil.waitForElementVisibleWithFluentWait(moreBtn_Loc, 150, 10);
        javaScriptUtil.drawBorder(driver.findElement(moreBtn_Loc));
        javaScriptUtil.selectingDrpDownValuesDynamically(moreBtn_Loc,"//span[text()='Delete']/parent::a[@role='menuitem']");

        return new Accounts(driver);
    }


    public ContactsHomePage setClickingConfirmingDeleteBtn(){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(clickingConfirmingDeleteBtn_Loc, 150, 10);
        javaScriptUtil.drawBorder(driver.findElement(clickingConfirmingDeleteBtn_Loc));
        eleUtil.doMoveToElementClick(clickingConfirmingDeleteBtn_Loc);
        javaScriptUtil.waitForPageLoad(200);

        return new ContactsHomePage(driver);
    }

    public ContactsHomePage navigateBackToAccountsHome(){
        javaScriptUtil.waitForPageLoad(40);
        driver.navigate().back();
        driver.navigate().refresh();
        javaScriptUtil.waitForPageLoad(40);
        return new ContactsHomePage(driver);

    }

    public  OpportunitiesPage setClickingCreateNewOpportunityBtnfromContactRelatedList(){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(clickingCreateNewOpportunityBtnfromContactRelatedList_Loc, 100, 10);
        eleUtil.doMoveToElementClick(clickingCreateNewOpportunityBtnfromContactRelatedList_Loc);
        javaScriptUtil.waitForPageLoad(250);

        return new OpportunitiesPage(driver);
    }

    public OpportunitiesHomePage setClickingOpportunitiesHeader(){
        javaScriptUtil.waitForPageLoad(150);
        eleUtil.waitForElementVisibleWithFluentWait(clickingOpportunitiesHeader_Loc, 100, 10);
        eleUtil.doMoveToElementClick(clickingOpportunitiesHeader_Loc);
        javaScriptUtil.waitForPageLoad(150);

        return new OpportunitiesHomePage(driver);
    }

    public Cases doClickingNewCaseCreationBtn(){
        javaScriptUtil.scrollIntoView(driver.findElement(By.xpath("//button[text()='Show All Activities']")));
        eleUtil.waitForElementVisibleWithFluentWait(clickingNewCaseCreationBtnFromAccountRelatedList_Loc, 100, 10);
        eleUtil.waitForElementsVisible(clickingNewCaseCreationBtnFromAccountRelatedList_Loc, 200);
        eleUtil.doMoveToElementClick(clickingNewCaseCreationBtnFromAccountRelatedList_Loc);
        javaScriptUtil.waitForPageLoad(150);

        return new Cases(driver);
    }

    public CasesHomePage doClickingCasesHeaderFromContactRelatedList(){
        javaScriptUtil.scrollIntoView(driver.findElement(By.xpath("//button[text()='Show All Activities']")));
        eleUtil.waitForElementVisibleWithFluentWait(ClickingCasesHeaderLinkFromAccountRelatedList_Loc, 100, 10);
        eleUtil.doMoveToElementClick(ClickingCasesHeaderLinkFromAccountRelatedList_Loc);
        javaScriptUtil.waitForPageLoad(150);

        return new CasesHomePage(driver);

    }



}
