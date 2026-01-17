package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class ReportBuilderPage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;

    public ReportBuilderPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }
    private By frame_Loc = By.xpath("//iframe[@data-aura-class='reportsReportBuilder']");
    //private By removingColumns_Loc = By.xpath("//h2[text()='Columns']/ancestor::div[@class='sectionable-table-section']//div[@class='sectionable-table-group-content']/ul[@role='group']//span[@aria-label='Remove Column: Last Activity']");
    private By clickingFilterTab_Loc = By.xpath("(//h2[text()='Filters'])[position()=1]//ancestor::a[@role='tab']");
    private By updatingToggleBtn_Loc = By.xpath("//span[text()='Update Preview Automatically']//ancestor::div[contains(@class,'auto-refresh-toggle')]//span[@class='slds-checkbox_faux_container']/span[@class='slds-checkbox_on']");
    private By selectingShowMeFilter_Loc = By.xpath("((//div[@role='tabpanel'])[2]//li[@class='sectionable-table-row'])[1]//button[@type='button' and @aria-haspopup='dialog']");
    private By selectigCreatedDateFilter_Loc = By.xpath("((//div[@role='tabpanel'])[2]//li[@class='sectionable-table-row'])[2]//button[@type='button' and @aria-haspopup='dialog']");
    private By selectingEditFilter_Loc = By.xpath("//h2[text()='Edit Filter']//ancestor::span[@classname='design-system-react-portal']//button[@id='undefined-list']");
    private By selectingRangeFilter_Loc = By.xpath("(//h2[text()='Filter by Created Date']//ancestor::span[@classname='design-system-react-portal']//button[@type='button'])[position()=3]");
    private By selectingApplyBtnOnEditFilter_Loc = By.xpath("//footer[contains(@class,'slds-text-align_right')]//button[text()='Apply']");
    private By clickingReportRunBtn_Loc = By.xpath("//div[@class='headerPanelItems']//div[@class='action-bars']//div[@class='actionBarButtonGroup']/button[text()='Run']");
    private By enteringNewFieldFilterCondtion_Loc = By.xpath("//label[text()='Add filter...']//ancestor::div[@class='sectionable-table-section']//div[@class='slds-combobox_container']//input[@placeholder='Add filter...' and @role='textbox']");
    private By enteringNewFIeldFilterValue_Loc = By.id("undefined-input");
    private By enteringColumnField_Loc = By.xpath("(//div[@class='sectionable-table-section'])[position()=2]//div[@role='none']/input[@placeholder='Add column...' and @role='textbox']");
    private By clickingSaveBtnOnAddColumnFilterCondition_Loc = By.xpath("(//footer[contains(@class,'filter-footer')]//div[contains(@class,'filter-button-group-margin')]/button)[last()]");
    private By clearColumnsFIlter_Loc = By.xpath("(//div[@class='sectionable-table-section'])[position()=2]//ul[@role='group']/li//span[@class='column-name']");


    public List<WebElement> getListOfColumnsFilter() {
        List<WebElement> columnsEleList;
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            columnsEleList = eleUtil.getElements(clearColumnsFIlter_Loc);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return columnsEleList;

    }



    public void clearningColumnsValue(String columnFilter){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            int columnsFilterCount = getListOfColumnsFilter().size();
            System.out.println("Value of the columnsFilterCount====>"+columnsFilterCount);
            String columnFilterText = null;
            if(columnsFilterCount > 0){
                System.out.println("Driver Came to if part");
                for (int i = 0; i < columnsFilterCount; i++) {
                    System.out.println("Driver Came to Loop");
                    List<WebElement> existingAllDealRegLeads = getListOfColumnsFilter();
                    WebElement columnFilter1 = existingAllDealRegLeads.get(0);
                    System.out.println("Value of the columnFilter1===>"+columnFilter1);
                    columnFilterText = columnFilter1.getText().toString();
                    System.out.println("Value of the columnFilterText====>" + columnFilterText);
                    if(columnFilterText.equals(columnFilter)){
                        System.out.println("No Need to remove the column");
                    }else{
                        javaScriptUtil.waitForPageLoad(150);
                        By clearColumnFilter = By.xpath("//h2[text()='Columns']/ancestor::div[@class='sectionable-table-section']//div[@class='sectionable-table-group-content']/ul[@role='group']//span[@aria-label='Remove Column: "+columnFilterText+"']");
                        javaScriptUtil.waitForPageLoad(150);
                        eleUtil.doClick(clearColumnFilter);
                        javaScriptUtil.waitForPageLoad(40);
                    }


                }
            }else{
                System.out.println("record count size is ZERO");
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void doEnterNewColumnField(String addColumn){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.clickElementByJS(enteringColumnField_Loc);
            eleUtil.doSendKeys(enteringColumnField_Loc,addColumn);
            javaScriptUtil.waitForPageLoad(150);
            By selectingFieldFilter = By.xpath("//span[text()='Customer ID']");
            actions.moveToElement(driver.findElement(selectingFieldFilter)).click().build().perform();
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void clickingFilterTabBtn(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(40);
            javaScriptUtil.drawBorder(driver.findElement(clickingFilterTab_Loc));
            //eleUtil.doClick(clickingFilterTab_Loc);
            //eleUtil.doMoveToElement(clickingFilterTab_Loc);
            javaScriptUtil.clickElementByJS(clickingFilterTab_Loc);
            javaScriptUtil.waitForPageLoad(40);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setClickingShowMeFilterCondition(){
        try {
            Thread.sleep(20000);
            //javaScriptUtil.waitForPageLoad(60);
            //eleUtil.doMoveToElement(selectingShowMeFilter_Loc);
            javaScriptUtil.drawBorder(driver.findElement(selectingShowMeFilter_Loc));
            javaScriptUtil.clickElementByJS(selectingShowMeFilter_Loc);
            javaScriptUtil.waitForPageLoad(200);
            javaScriptUtil.drawBorder(driver.findElement(selectingEditFilter_Loc));
            javaScriptUtil.selectingDrpDownValuesDynamically(selectingEditFilter_Loc,"//span[text()='All accounts']/parent::a[@role='menuitem']");
            javaScriptUtil.waitForPageLoad(200);
            javaScriptUtil.drawBorder(driver.findElement(selectingApplyBtnOnEditFilter_Loc));
            actions.moveToElement(driver.findElement(selectingApplyBtnOnEditFilter_Loc)).click().build().perform();
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void setClickingCreatedDateFilterCondition(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(60);
            //eleUtil.doMoveToElement(selectigCreatedDateFilter_Loc);
            javaScriptUtil.drawBorder(driver.findElement(selectigCreatedDateFilter_Loc));
            javaScriptUtil.clickElementByJS(selectigCreatedDateFilter_Loc);
            javaScriptUtil.waitForPageLoad(60);
            javaScriptUtil.drawBorder(driver.findElement(selectingRangeFilter_Loc));
            javaScriptUtil.selectingDrpDownValuesDynamically(selectingRangeFilter_Loc,"//span[text()='All Time']/parent::a[@role='menuitem']");
            javaScriptUtil.waitForPageLoad(60);
            javaScriptUtil.drawBorder(driver.findElement(selectingApplyBtnOnEditFilter_Loc));
            //eleUtil.doMoveToElement(selectingApplyBtnOnEditFilter_Loc);
            actions.moveToElement(driver.findElement(selectingApplyBtnOnEditFilter_Loc)).click().build().perform();
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public void putCustomerIDFilterFieldValue(String FieldFilter,String filterValue){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(60);
            javaScriptUtil.clickElementByJS(enteringNewFieldFilterCondtion_Loc);
            eleUtil.doSendKeys(enteringNewFieldFilterCondtion_Loc,FieldFilter);
            javaScriptUtil.waitForPageLoad(150);
            By selectingFieldFilter = By.xpath("(//span[text()='Customer ID'])[last()]");
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.clickElementByJS(selectingFieldFilter);
            javaScriptUtil.waitForPageLoad(200);
            eleUtil.doSendKeys(enteringNewFIeldFilterValue_Loc,filterValue);
            javaScriptUtil.waitForPageLoad(200);
            actions.moveToElement(driver.findElement(clickingSaveBtnOnAddColumnFilterCondition_Loc)).click().build().perform();
            //javaScriptUtil.clickElementByJS(clickingSaveBtnOnAddColumnFilterCondition_Loc);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void doClickingReportRunBtn(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(200);
            //System.out.println("Value of the frame:===>"+driver.findElement(By.xpath("//iframe[@data-aura-class='reportsReportBuilder']")));
            javaScriptUtil.drawBorder(driver.findElement(clickingReportRunBtn_Loc));
            eleUtil.doMoveToElementClick(clickingReportRunBtn_Loc);
            javaScriptUtil.waitForPageLoad(60);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public ReportsDetailPage buildingReportByUsingCustomerID(String ColumnFilterValue1,
                                                             String addColumn,String FieldFilter,String filterValue){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(40);
            javaScriptUtil.clickElementByJS(updatingToggleBtn_Loc);
            javaScriptUtil.waitForPageLoad(40);
            clearningColumnsValue(ColumnFilterValue1);
            doEnterNewColumnField(addColumn);
            clickingFilterTabBtn();
            javaScriptUtil.waitForPageLoad(60);
            setClickingShowMeFilterCondition();
            setClickingCreatedDateFilterCondition();
            putCustomerIDFilterFieldValue(FieldFilter,filterValue);
            doClickingReportRunBtn();
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new ReportsDetailPage(driver);


    }

}
