package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class OpportunitiesHomePage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;
    private JavascriptExecutor js;

    public OpportunitiesHomePage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }

    private By clickingNewBtn_Loc = By.xpath("//div[text()='New']/parent::a");
    private By clickingNewlyCreatedOpportunityLinkFromAccountRelatedList_Loc = By.xpath("(//table[contains(@class,'slds-table_resizable-cols')]/tbody//tr[@class='slds-hint-parent']//a[@class='slds-truncate'])[1]");
    private By ObjectHeader_Loc = By.xpath("(//div[@data-aura-class='navexSplitViewWrapper']//h1)[1]");
    private By clickingListViewBtn_Loc = By.xpath("//span[text()='Select a List View: Opportunities']//parent::button[@title='Select a List View: Opportunities']");
    private By clickingFilterBtn_Loc = By.xpath("//span[text()='Filters']/ancestor::button[@title='Filters' and @role='button']");
    private By clickingAddFilterBtn_Loc = By.xpath("//a[text()='Add Filter']");
    private By clickingFieldFilterBtn_loc = By.xpath("//label[text()='Field']/ancestor::div[@part='combobox']//div[@class='slds-combobox_container']//button[@name='Field' and @role='combobox']");
    private By valueField_Loc = By.xpath("//label[text()='Value']/ancestor::lightning-input[@data-id='OperandInput']//input[@part='input']");
    private By clickingSaveBtnOnFilterProvidedPage_Loc = By.xpath("//div[contains(@class,'slds-m-bottom_small')]//lightning-button[@data-id='SaveButton']/button[@type='button']");
    private By clickingSaveBtnOnMainFIltetPage_Loc = By.xpath("//div[contains(@class,'slds-grid_align-end')]//lightning-button[@data-id='saveButton']/button[@type='button']");
    private By clearingFilterCondition_Loc = By.xpath("//a[text()='Remove All']");
    private By checkingFilterCondition_Loc = By.xpath("(//div[contains(@class,'listDisplays')]//form[contains(@class,'slds-panel_docked-right')]//div//p)[position()=2]");


    public OpportunitiesPage setClickingNewBtn_Loc(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doMoveToElementClick(clickingNewBtn_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    return new OpportunitiesPage(driver);
    }

    public OpportunitiesDetailPage setClickingNewlyCreatedOpportunityLinkFromAccountRelatedList(){
        try {
            driver.navigate().refresh();
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            //By customOppIDSortBtn_Loc = By.xpath("//span[text()='Custom_Opportunity ID']/parent::a[@role='button' and @data-navigation='enable']");
            //eleUtil.doMoveToElementClick(customOppIDSortBtn_Loc);
            //Thread.sleep(10000);
            javaScriptUtil.drawBorder(driver.findElement(clickingNewlyCreatedOpportunityLinkFromAccountRelatedList_Loc));
            eleUtil.doMoveToElementClick(clickingNewlyCreatedOpportunityLinkFromAccountRelatedList_Loc);
            javaScriptUtil.waitForPageLoad(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new OpportunitiesDetailPage(driver);
    }

    public String fetchObjecHeader(){
        String objectHeaderText = null;
        driver.navigate().refresh();
        eleUtil.waitForElementsVisible(ObjectHeader_Loc, 200);
        objectHeaderText = eleUtil.doGetElementText(ObjectHeader_Loc);
        System.out.println("Value of the objectHeaderText========>"+objectHeaderText);
        return objectHeaderText;
    }

    public void clicikingFileterBtn() {
        javaScriptUtil.waitForPageLoad(250);
        javaScriptUtil.drawBorder(driver.findElement(clickingFilterBtn_Loc));
        eleUtil.doClick(clickingFilterBtn_Loc);
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
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(200);
            javaScriptUtil.selectingDrpDownValuesDynamically(clickingListViewBtn_Loc, "//span[text()='All Opportunities']");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void passingFiltersDynamically(String fieldFilterValue, String valueFilterValue) {

        try {
            Thread.sleep(15000);
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
        javaScriptUtil.waitForPageLoad(40);
        eleUtil.doClick(clearingFilterCondition_Loc);
        eleUtil.doClick(clickingSaveBtnOnMainFIltetPage_Loc);

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

    public OpportunitiesDetailPage clickingOpportunityDetailPageThroughListView() {
        By contactLink = By.xpath("//div[contains(@class,'slds-table_header-fixed_container')]//table[contains(@class,slds-table_header-fixed)]//tbody//th[@data-label='Opportunity Name']//a");
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(15000);
            javaScriptUtil.drawBorder(driver.findElement(contactLink));
            javaScriptUtil.clickElementByJS(contactLink);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new OpportunitiesDetailPage(driver);

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
            driver.navigate().refresh();
            Thread.sleep(15000);
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

}
