package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Set;

public class ReportsHomePage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;

    public ReportsHomePage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }

    private By clickingNewReportBtn_Loc = By.xpath("(//div[contains(@class,'resize-reflow_flex-wrap')])[2]//a[@title='New Report']");
    private By selectingAllReportType_Loc = By.xpath("(//span[text()='Create Report']/ancestor::div[contains(@class,'recommended-report-type-picker-container')]//div[contains(@class,'report-type-picker')])[position()=2]//ul[@role='listbox']//li[@class='slds-nav-vertical__item']/a[@aria-label='All Report Type Category']");
    private By clickingReportSearchBox_Loc = By.xpath("//label[contains(text(),'Search Report Types...')]/parent::div[contains(@class,'report-type-search-no-filter')]//input[contains(@placeholder,'Search Report Types...') and @type='search']");
    //private By selectingReportName_Loc = By.xpath("(//table[contains(@aria-label,'Recently Used Report Types list')]/tbody//td[@role='gridcell'])[position()=4]//a");
    private By clickingStartNewReportBtn_Loc = By.id("start-report-btn");

    public void clickingNewReportsBtn(){
        javaScriptUtil.waitForPageLoad(40);
        eleUtil.doClick(clickingNewReportBtn_Loc);
        javaScriptUtil.waitForPageLoad(40);
    }

    public ReportBuilderPage selectingAllReportOption(String reportsName){
        try {
            Thread.sleep(15000);
            WebElement frmeEle = driver.findElement(By.xpath("//iframe[@title='Report Builder']"));
            driver.switchTo().frame(frmeEle);


            if(driver.findElement(selectingAllReportType_Loc).isDisplayed()){
                System.out.println("Compiler Came to If part");
                javaScriptUtil.drawBorder(driver.findElement(selectingAllReportType_Loc));
                actions.moveToElement(driver.findElement(selectingAllReportType_Loc)).click().build().perform();
                Thread.sleep(10000);
                javaScriptUtil.drawBorder(driver.findElement(clickingReportSearchBox_Loc));
                eleUtil.doSendKeys(clickingReportSearchBox_Loc, reportsName);
                By selectingReportType = By.xpath("//p[text()='"+reportsName+"']/parent::a");
                Thread.sleep(10000);
                javaScriptUtil.drawBorder(driver.findElement(selectingReportType));
                //eleUtil.doClick(selectingReportType);
                javaScriptUtil.clickElementByJS(selectingReportType);
                Thread.sleep(10000);
                javaScriptUtil.clickElementByJS(clickingStartNewReportBtn_Loc);
            }else{
                System.out.println("NO Elements are found");
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new ReportBuilderPage(driver);


    }
}
