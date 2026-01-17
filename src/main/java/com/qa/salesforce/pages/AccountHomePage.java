package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountHomePage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;

    //1.Const. of the page class
    public AccountHomePage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(this.driver);
    }

    //2.Private By locators.
    private By NewBtnLoc = By.xpath("//div[@class='slds-align_absolute-center']//a[@title='New']");
    private By clickingListViewBtn_Loc = By.xpath("//span[text()='Select a List View: Accounts']/ancestor::button[@title='Select a List View: Accounts' and @type='button']");
    private By clickingFilterBtn_Loc = By.xpath("//span[text()='Filters']/ancestor::button[@title='Filters' and @role='button']");
    private By clickingAddFilterBtn_Loc = By.xpath("//a[text()='Add Filter']");
    private By clickingFieldFilterBtn_loc = By.xpath("//label[text()='Field']/ancestor::div[@part='combobox']//div[@class='slds-combobox_container']//button[@name='Field' and @role='combobox']");
    private By valueField_Loc = By.xpath("//label[text()='Value']/ancestor::lightning-input[@data-id='OperandInput']//input[@part='input']");
    private By clickingSaveBtnOnFilterProvidedPage_Loc = By.xpath("//div[contains(@class,'slds-m-bottom_small')]//lightning-button[@data-id='SaveButton']/button[@type='button']");
    private By getClickingSaveBtnOnFilterProvidedPage_Loc = By.xpath("//div[contains(@class,'slds-grid_align-end')]//lightning-button[@data-id='saveButton']/button[@type='button']");
    private By clearingFilterCondition_Loc = By.xpath("//a[text()='Remove All']");
    private By checkingFilterCondition_Loc = By.xpath("(//div[contains(@class,'slds-grid_vertical')])[position()=2]//p");
    private By accountHeader_Loc = By.xpath("//div[contains(@class,'slds-page-header--object-home')]//div[contains(@class,'slds-line-height_reset')]/h1");


    public Accounts creatingNewAccountCreationBtn(){
        boolean flag = false;
        while(true){
            eleUtil.doClick(NewBtnLoc, 120);
            break;
        }

        return new Accounts(driver);
    }

    public void selectingAllAccountListView(){
        javaScriptUtil.waitForPageLoad(40);
        javaScriptUtil.selectingDrpDownValuesDynamically(clickingListViewBtn_Loc,"//span[text()='All Accounts']/ancestor::lightning-base-combobox-item[@data-value='AllAccounts' and @role='option']");
        javaScriptUtil.waitForPageLoad(40);

    }

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

    public void searchingCustomerIDThroughListview(String fieldFilterValue,String valueFilterValue){
        try {
            Thread.sleep(15000);
            selectingAllAccountListView();
            clicikingFileterBtn();
            setClickingAddFilterBtn();
            passingFiltersDynamically(fieldFilterValue,valueFilterValue);
            eleUtil.doClick(getClickingSaveBtnOnFilterProvidedPage_Loc);
            javaScriptUtil.waitForPageLoad(40);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



    }

    public AccountDetailPage clickingAccountName(){
        By clickingAccountName_Loc = By.xpath("//div[contains(@class,'slds-table_header-fixed_container')]//table[contains(@class,slds-table_header-fixed)]//tbody//th[@data-label='Account Name']//div[@class='slds-grid']/a");
        try {
            Thread.sleep(20000);
            //eleUtil.doMoveToElement(clickingAccountName_Loc);
            javaScriptUtil.drawBorder(driver.findElement(clickingAccountName_Loc));
            javaScriptUtil.clickElementByJS(clickingAccountName_Loc);
            javaScriptUtil.waitForPageLoad(40);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new AccountDetailPage(driver);

    }

    public void clearningFilterConditionAfterCapturingTheData(){
        javaScriptUtil.waitForPageLoad(40);
        driver.navigate().back();
        javaScriptUtil.waitForPageLoad(40);
        try {
            Thread.sleep(10000);
            selectingAllAccountListView();
            Thread.sleep(10000);
            clicikingFileterBtn();
            Thread.sleep(10000);
            clickingClearFilterCondition();
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void clearFilterConditionIfPresent(){
        try {
            driver.navigate().refresh();
            javaScriptUtil.waitForPageLoad(40);
            Thread.sleep(15000);
            String filterCondition = eleUtil.doGetElementText(checkingFilterCondition_Loc);
            if(filterCondition.contains("Matching all of these filters")) {
                Thread.sleep(10000);
                selectingAllAccountListView();
                Thread.sleep(10000);
                clicikingFileterBtn();
                Thread.sleep(10000);
                clickingClearFilterCondition();
                Thread.sleep(10000);
            }else{
                System.out.println("No Filter Conditions");
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public String getAccountHeader(){
        String objectHeader = null;
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.drawBorder(driver.findElement(accountHeader_Loc));
            objectHeader = eleUtil.doGetElementText(accountHeader_Loc);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return objectHeader.toString();


    }



}