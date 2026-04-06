package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class CasesHomePage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;
    public CasesHomePage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }

    private By clickingNewBtnOnCaseHomePage_Loc = By.xpath("//div[contains(@class,'actionsWrapper')]/ul[contains(@data-aura-class,'forceActionsContainer')]//a[@title='New']");
    private By clickingRecentlyCreatedCaseLink_Loc = By.xpath("(//table[@role='grid']/tbody/tr[@role='row']//th[@role='rowheader']//a)[1]");
    private By clickingListViewBtn_Loc = By.xpath("//span[text()='Select a List View: Cases']//parent::button[@title='Select a List View: Cases']");
    private By clickingFilterBtn_Loc = By.xpath("//span[text()='Filters']/ancestor::button[@title='Filters' and @role='button']");
    private By clickingAddFilterBtn_Loc = By.xpath("//a[text()='Add Filter']");
    private By clickingFieldFilterBtn_loc = By.xpath("//label[text()='Field']/ancestor::div[@part='combobox']//div[@class='slds-combobox_container']//button[@name='Field' and @role='combobox']");
    private By valueField_Loc = By.xpath("//label[text()='Value']/ancestor::lightning-input[@data-id='OperandInput']//input[@part='input']");
    private By clickingSaveBtnOnFilterProvidedPage_Loc = By.xpath("//div[contains(@class,'slds-m-bottom_small')]//lightning-button[@data-id='SaveButton']/button[@type='button']");
    private By clickingSaveBtnOnMainFIltetPage_Loc = By.xpath("//div[contains(@class,'slds-grid_align-end')]//lightning-button[@data-id='saveButton']/button[@type='button']");
    private By clearingFilterCondition_Loc = By.xpath("//a[text()='Remove All']");
    private By checkingFilterCondition_Loc = By.xpath("(//div[contains(@class,'listDisplays')]//form[contains(@class,'slds-panel_docked-right')]//div//p)[position()=2]");
    private By ObjectHeader_Loc = By.xpath("(//div[@data-aura-class='navexSplitViewWrapper']//h1)[1]");


    public Cases doClickingNewCaseCreationBtn(){
        eleUtil.waitForElementVisible(clickingNewBtnOnCaseHomePage_Loc, 200);
        eleUtil.doClick(clickingNewBtnOnCaseHomePage_Loc);
        javaScriptUtil.waitForPageLoad(150);
        return new Cases(driver);
    }

    public CasesDetailPage doClickRecentlyCase(){
        eleUtil.checkElementClickable(clickingRecentlyCreatedCaseLink_Loc,300);
        eleUtil.doMoveToElementClick(clickingRecentlyCreatedCaseLink_Loc);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new CasesDetailPage(driver);
    }
    public void clicikingFileterBtn() {
        javaScriptUtil.waitForPageLoad(250);
        javaScriptUtil.drawBorder(driver.findElement(clickingFilterBtn_Loc));
        //eleUtil.doMoveToElementClick(clickingFilterBtn_Loc);
        javaScriptUtil.clickElementByJS(clickingFilterBtn_Loc);
        javaScriptUtil.waitForPageLoad(250);
    }

    public void setClickingAddFilterBtn() {
        javaScriptUtil.waitForPageLoad(250);
        //eleUtil.doClick(clickingAddFilterBtn_Loc);
        //eleUtil.doMoveToElementClick(clickingAddFilterBtn_Loc);
        javaScriptUtil.clickElementByJS(clickingAddFilterBtn_Loc);
        javaScriptUtil.waitForPageLoad(250);

    }

    public void selectingAllContactsListView() {
        driver.navigate().refresh();
        try {
            Thread.sleep(20000);
            javaScriptUtil.waitForPageLoad(200);
            javaScriptUtil.selectingDrpDownValuesDynamically(clickingListViewBtn_Loc, "//span[text()='All Open Cases']");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void passingFiltersDynamically(String fieldFilterValue, String valueFilterValue) {

        try {
            Thread.sleep(20000);
            javaScriptUtil.selectingDrpDownValuesDynamically(clickingFieldFilterBtn_loc, "//span[text()='" + fieldFilterValue + "']");
            eleUtil.doSendKeys(valueField_Loc, valueFilterValue);
            Thread.sleep(10000);
            javaScriptUtil.drawBorder(driver.findElement(clickingSaveBtnOnFilterProvidedPage_Loc));
            eleUtil.doMoveToElementClick(clickingSaveBtnOnFilterProvidedPage_Loc);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void clickingClearFilterCondition() {
        javaScriptUtil.waitForPageLoad(200);
        javaScriptUtil.clickElementByJS(clearingFilterCondition_Loc);
        javaScriptUtil.clickElementByJS(clickingSaveBtnOnMainFIltetPage_Loc);

    }


    public void searchingContactIDThroughListview(String fieldFilterValue, String valueFilterValue) {
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            //selectingAllContactsListView();
            //clicikingFileterBtn();
            setClickingAddFilterBtn();
            passingFiltersDynamically(fieldFilterValue,valueFilterValue);
            javaScriptUtil.waitForPageLoad(250);
            eleUtil.doClick(clickingSaveBtnOnMainFIltetPage_Loc);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void clearningFilterConditionAfterCapturingTheData() {
        try {
            Thread.sleep(10000);
            //selectingAllContactsListView();
            Thread.sleep(10000);
            clicikingFileterBtn();
            Thread.sleep(10000);
            clickingClearFilterCondition();
            Thread.sleep(5000);
            //javaScriptUtil.drawBorder(driver.findElement(clickingSaveBtnOnMainFIltetPage_Loc));
            //eleUtil.doMoveToElementClick(clickingSaveBtnOnMainFIltetPage_Loc);
            //javaScriptUtil.waitForPageLoad(250);
            driver.navigate().refresh();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void clearFilterConditionIfPresent() {
        try {
            //driver.navigate().refresh();
            Thread.sleep(25000);
            selectingAllContactsListView();
            Thread.sleep(10000);
            clicikingFileterBtn();
            Thread.sleep(10000);
            String filterCondition = eleUtil.doGetElementText(checkingFilterCondition_Loc);
            System.out.println("Values of the filterCondition====>"+filterCondition);
            if(filterCondition.contains("Matching all of these filters")){
                Thread.sleep(10000);
                clickingClearFilterCondition();
                javaScriptUtil.waitForPageLoad(150);
            }else{
                System.out.println("No Filter Conditions are Available");
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public CasesDetailPage clickingCasePageThroughListView() {
        By contactLink = By.xpath("//div[contains(@class,'slds-table_header-fixed_container')]//table[contains(@class,slds-table_header-fixed)]//tbody//th[@data-label='Case Number']//a");
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(15000);
            javaScriptUtil.drawBorder(driver.findElement(contactLink));
            javaScriptUtil.clickElementByJS(contactLink);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new CasesDetailPage(driver);

    }

    public String fetchObjecHeader(){
        String objectHeaderText = null;
        eleUtil.waitForElementsVisible(ObjectHeader_Loc, 200);
        objectHeaderText = eleUtil.doGetElementText(ObjectHeader_Loc);
        System.out.println("Value of the objectHeaderText========>"+objectHeaderText);
        return objectHeaderText;
    }
}
