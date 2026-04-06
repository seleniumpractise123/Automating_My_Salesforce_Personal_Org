package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class LeadsHomePage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;
    public LeadsHomePage(WebDriver driver) {

        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }

    private By clickingNewButton_Loc = By.xpath("//div[@class='slds-align_absolute-center']//a[@title='New']");
    private By clickingListViewBtn_Loc = By.xpath("//span[text()='Select a List View: Leads']//parent::button[@title='Select a List View: Leads']");
    private By clickingFilterBtn_Loc = By.xpath("//span[text()='Filters']/ancestor::button[@title='Filters' and @role='button']");
    private By clickingAddFilterBtn_Loc = By.xpath("//a[text()='Add Filter']");
    private By clickingFieldFilterBtn_loc = By.xpath("//label[text()='Field']/ancestor::div[@part='combobox']//div[@class='slds-combobox_container']//button[@name='Field' and @role='combobox']");
    private By valueField_Loc = By.xpath("//label[text()='Value']/ancestor::lightning-input[@data-id='OperandInput']//input[@part='input']");
    private By clickingSaveBtnOnFilterProvidedPage_Loc = By.xpath("//div[contains(@class,'slds-m-bottom_small')]//lightning-button[@data-id='SaveButton']/button[@type='button']");
    private By tClickingSaveBtnOnMainFilterFilterProvidedPage_Loc = By.xpath("//div[contains(@class,'slds-grid_align-end')]//lightning-button[@data-id='saveButton']/button[@type='button']");
    private By clearingFilterCondition_Loc = By.xpath("//a[text()='Remove All']");
    private By checkingFilterCondition_Loc = By.xpath("((//div[contains(@class,'slds-grid_vertical')])[position()=2]//p)[position()=2]");
    private By leadHeader_Loc = By.xpath("//div[contains(@class,'slds-page-header--object-home')]//div[contains(@class,'slds-line-height_reset')]/h1");


    public LeadsPage doClickingLeadCreationBtn(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            //eleUtil.doMoveToElementClick(clickingNewButton_Loc);
            javaScriptUtil.clickElementByJS(clickingNewButton_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new LeadsPage(driver);
    }

    public void selectingAllLeadsListView(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(200);
            javaScriptUtil.selectingDrpDownValuesDynamically(clickingListViewBtn_Loc,"//span[text()='All Open Leads']");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void clicikingFileterBtn(){
        javaScriptUtil.waitForPageLoad(40);
        eleUtil.doClick(clickingFilterBtn_Loc);
        javaScriptUtil.waitForPageLoad(40);
    }

    public void setClickingAddFilterBtn(){
        try {
            Thread.sleep(10000);
            //eleUtil.doClick(clickingAddFilterBtn_Loc);
            javaScriptUtil.drawBorder(driver.findElement(clickingAddFilterBtn_Loc));
            eleUtil.doMoveToElementClick(clickingAddFilterBtn_Loc);
            javaScriptUtil.waitForPageLoad(40);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public void passingFiltersDynamically(String fieldFilterValue,String valueFilterValue){
        javaScriptUtil.selectingDrpDownValuesDynamically(clickingFieldFilterBtn_loc,"//span[text()='"+fieldFilterValue+"']");
        eleUtil.doSendKeys(valueField_Loc,valueFilterValue);
        //eleUtil.doClick(clickingSaveBtnOnFilterProvidedPage_Loc);
        eleUtil.doMoveToElementClick(clickingSaveBtnOnFilterProvidedPage_Loc);
    }

    public void clickingClearFilterCondition(){
        javaScriptUtil.waitForPageLoad(40);
        //eleUtil.doClick(clearingFilterCondition_Loc);
        eleUtil.doMoveToElementClick(clearingFilterCondition_Loc);
        //eleUtil.doClick(getClickingSaveBtnOnFilterProvidedPage_Loc);
        eleUtil.doMoveToElementClick(tClickingSaveBtnOnMainFilterFilterProvidedPage_Loc);
        driver.navigate().refresh();

    }
    public void checkingLeadIDFilterIsAvailableOrNot(){
        try {
            Thread.sleep(15000);
            selectingAllLeadsListView();
               clicikingFileterBtn();
                if(driver.findElement(By.xpath("//span[text()='Custom_Lead ID']")).isDisplayed()){
                    System.out.println("Driver Came to Custom Lead ID filters If part");
                    Thread.sleep(15000);
                    By clickingRemoveFilterbtn = By.xpath("(//lightning-button-icon[@variant='bare'])[3]/button[contains(@title,'Remove filter:')]");
                    javaScriptUtil.drawBorder(driver.findElement(clickingRemoveFilterbtn));
                    eleUtil.doMoveToElementClick(clickingRemoveFilterbtn);
                    Thread.sleep(5000);
                    eleUtil.doMoveToElementClick(tClickingSaveBtnOnMainFilterFilterProvidedPage_Loc);
                    javaScriptUtil.waitForPageLoad(250);

                }else{
                    System.out.println("No Lead ID filter");
                }


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void searchingLeadIDThroughListview(String fieldFilterValue,String valueFilterValue){
        try {
            Thread.sleep(15000);
            selectingAllLeadsListView();
            clicikingFileterBtn();

            setClickingAddFilterBtn();
            passingFiltersDynamically(fieldFilterValue,valueFilterValue);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    public LeadsDetailPage clickingLeadDetailPageThroughListView(){
        By leadLink = By.xpath("//table[@role='grid']/tbody//th[@data-label='Name']//a");
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(15000);
            javaScriptUtil.drawBorder(driver.findElement(leadLink));
            javaScriptUtil.clickElementByJS(leadLink);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new LeadsDetailPage(driver);

    }
    public void clearningFilterConditionAfterCapturingThefieldValue(){
        javaScriptUtil.waitForPageLoad(40);
        driver.navigate().back();
        javaScriptUtil.waitForPageLoad(40);
        clicikingFileterBtn();
        String filterCondition = eleUtil.doGetElementText(checkingFilterCondition_Loc);
        if(filterCondition.contains("Matching all of these filters")) {
            if (driver.findElement(By.xpath("//span[text()='Custom_Lead ID']")).isDisplayed()) {
                try {
                    Thread.sleep(15000);
                    By clickingRemoveFilterbtn = By.xpath("(//lightning-button-icon[@variant='bare'])[3]/button[contains(@title,'Remove filter:')]");
                    javaScriptUtil.drawBorder(driver.findElement(clickingRemoveFilterbtn));
                    eleUtil.doMoveToElementClick(clickingRemoveFilterbtn);
                    javaScriptUtil.waitForPageLoad(150);
                    driver.navigate().refresh();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Lead ID filter is not available");
            }

        }else{
            System.out.println("No filter is not available");
        }
    }
    public String getLeadHeaderPage(){
        String leaderHeaderTitle = null;
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            leaderHeaderTitle = eleUtil.doGetElementText(leadHeader_Loc);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return leaderHeaderTitle;

    }



}
