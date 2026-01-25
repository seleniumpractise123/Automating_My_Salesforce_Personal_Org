package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ContactsHomePage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;

    public ContactsHomePage(WebDriver driver) {
        //TODO Auto-generated constructor stub

        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }

    //By Locators
    private By clickingNewTabBtn_Loc = By.xpath("//div[@class='slds-align_absolute-center']//a[@title='New']");
    private By clickingContactLink_Loc = By.xpath("(//table[contains(@class,'slds-table_resizable-cols')]/tbody//tr[@class='slds-hint-parent']//a[@class='slds-truncate'])[position()=1]");
    private By clickingListViewBtn_Loc = By.xpath("//span[text()='Select a List View: Contacts']//parent::button[@title='Select a List View: Contacts']");
    private By clickingFilterBtn_Loc = By.xpath("//span[text()='Filters']/ancestor::button[@title='Filters' and @role='button']");
    private By clickingAddFilterBtn_Loc = By.xpath("//a[text()='Add Filter']");
    private By clickingFieldFilterBtn_loc = By.xpath("//label[text()='Field']/ancestor::div[@part='combobox']//div[@class='slds-combobox_container']//button[@name='Field' and @role='combobox']");
    private By valueField_Loc = By.xpath("//label[text()='Value']/ancestor::lightning-input[@data-id='OperandInput']//input[@part='input']");
    private By clickingSaveBtnOnFilterProvidedPage_Loc = By.xpath("//div[contains(@class,'slds-m-bottom_small')]//lightning-button[@data-id='SaveButton']/button[@type='button']");
    private By getClickingSaveBtnOnFilterProvidedPage_Loc = By.xpath("//div[contains(@class,'slds-grid_align-end')]//lightning-button[@data-id='saveButton']/button[@type='button']");
    private By clearingFilterCondition_Loc = By.xpath("//a[text()='Remove All']");
    private By checkingFilterCondition_Loc = By.xpath("(//div[contains(@class,'slds-grid_vertical')])[position()=2]//p");
    private By contactHeader_Loc = By.xpath("//div[contains(@class,'slds-page-header--object-home')]//div[contains(@class,'slds-line-height_reset')]/h1");

    public void clicikingFileterBtn(){
        javaScriptUtil.waitForPageLoad(40);
        eleUtil.doClick(clickingFilterBtn_Loc);
        javaScriptUtil.waitForPageLoad(40);
    }

    public void setClickingAddFilterBtn(){
        javaScriptUtil.waitForPageLoad(40);
        eleUtil.doClick(clickingAddFilterBtn_Loc);
        javaScriptUtil.waitForPageLoad(40);

    }

    public void selectingAllContactsListView(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(200);
            javaScriptUtil.selectingDrpDownValuesDynamically(clickingListViewBtn_Loc,"//span[text()='All Contacts']");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void passingFiltersDynamically(String fieldFilterValue,String valueFilterValue){
        javaScriptUtil.selectingDrpDownValuesDynamically(clickingFieldFilterBtn_loc,"//span[text()='"+fieldFilterValue+"']");
        eleUtil.doSendKeys(valueField_Loc,valueFilterValue);
        eleUtil.doClick(clickingSaveBtnOnFilterProvidedPage_Loc);
    }

    public void clickingClearFilterCondition(){
        javaScriptUtil.waitForPageLoad(40);
        eleUtil.doClick(clearingFilterCondition_Loc);
        eleUtil.doClick(getClickingSaveBtnOnFilterProvidedPage_Loc);

    }


    public void searchingContactIDThroughListview(String fieldFilterValue,String valueFilterValue){
        try {
            Thread.sleep(15000);
            selectingAllContactsListView();
            clicikingFileterBtn();
            String filterCondition = eleUtil.doGetElementText(checkingFilterCondition_Loc);
            if(filterCondition.contains("Matching all of these filters")){
                Thread.sleep(10000);
                clickingClearFilterCondition();
                Thread.sleep(10000);
                setClickingAddFilterBtn();
                passingFiltersDynamically(fieldFilterValue,valueFilterValue);
                eleUtil.doClick(getClickingSaveBtnOnFilterProvidedPage_Loc);
                javaScriptUtil.waitForPageLoad(150);
            }else{
                setClickingAddFilterBtn();
                passingFiltersDynamically(fieldFilterValue,valueFilterValue);
                eleUtil.doClick(getClickingSaveBtnOnFilterProvidedPage_Loc);
                javaScriptUtil.waitForPageLoad(150);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public Contacts doClickingNewBtn() {
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doClick(clickingNewTabBtn_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new Contacts(driver);
    }

    public ContactDetailPage clickingContactLinkFromContactHomePage(){
        try {
            Thread.sleep(15000);
            //javaScriptUtil.waitForPageLoad(200);
            Thread.sleep(10000);
            javaScriptUtil.drawBorder(driver.findElement(clickingContactLink_Loc));
            eleUtil.doMoveToElementClick(clickingContactLink_Loc);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new ContactDetailPage(driver);

    }

    public ContactDetailPage clickingContactDetailPageThroughListView(){
        By contactLink = By.xpath("//div[contains(@class,'slds-table_header-fixed_container')]//table[contains(@class,slds-table_header-fixed)]//tbody//th[@data-label='Name']//div[@class='slds-grid']/a");
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(15000);
            javaScriptUtil.drawBorder(driver.findElement(contactLink));
            javaScriptUtil.clickElementByJS(contactLink);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new ContactDetailPage(driver);

    }

    public String getContactHeader(){
        String objectHeader = null;
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.drawBorder(driver.findElement(contactHeader_Loc));
            objectHeader = eleUtil.doGetElementText(contactHeader_Loc);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return objectHeader.toString();


    }

    public void clearningFilterConditionAfterCapturingTheData(){
        javaScriptUtil.waitForPageLoad(40);
        driver.navigate().back();
        javaScriptUtil.waitForPageLoad(40);
        try {
            Thread.sleep(10000);
            selectingAllContactsListView();
            Thread.sleep(10000);
            clicikingFileterBtn();
            Thread.sleep(10000);
            clickingClearFilterCondition();
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
