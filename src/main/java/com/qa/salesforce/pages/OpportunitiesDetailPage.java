package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class OpportunitiesDetailPage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;
    public OpportunitiesDetailPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }

    private By fetchOpportunityStagefieldValue_Loc = By.xpath("//span[text()='Stage']//ancestor::records-record-layout-item[@field-label='Stage']//lightning-formatted-text[@slot='outputField']");
    private By clickingOppDetailTab_Loc = By.xpath("(//lightning-tab-bar[@variant='standard']/ul[@role='tablist']//li[@data-label='Details']/a[@data-label='Details'])[2]");


    public void doClickDetailTab(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doMoveToElementClick(clickingOppDetailTab_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public String setFetchOpportunityStage(){
        String OppStageFieldValue = null;
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            OppStageFieldValue = eleUtil.doGetElementText(fetchOpportunityStagefieldValue_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return OppStageFieldValue;
    }
}
