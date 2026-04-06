package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class CampaignsHomePage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;
    public CampaignsHomePage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }

    private By clickingNewBtnFromCampaignTab_Loc = By.xpath("(//div[@class='slds-align_absolute-center'])[1]//li[@data-target-selection-name='sfdc:StandardButton.Campaign.New']/a[@title='New']");

    public CampaignsPage doClickNewBtnFromCampaign(){
        eleUtil.clickElementWhenReady(clickingNewBtnFromCampaignTab_Loc,200);
        eleUtil.doMoveToElementClick(clickingNewBtnFromCampaignTab_Loc);
        javaScriptUtil.waitForPageLoad(200);
        return new CampaignsPage(driver);
    }
}
