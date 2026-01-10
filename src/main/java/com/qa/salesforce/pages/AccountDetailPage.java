package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDetailPage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;

    public AccountDetailPage(WebDriver driver) {
        //TODO Auto-generated constructor stub

        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
    }

    private By detailTabLoc = By.xpath(" (//lightning-tab-bar[@variant='standard' and contains(@exportparts,'tab-item')])[position()=1]/ul/li/a[@id='detailTab__item']");
    private By cpaturingRatingfieldLoc = By.xpath("//span[text()='Rating']/ancestor::div[@role='listitem']//lightning-formatted-text[@slot='outputField']");
    private By typeFieldLoc = By.xpath("//span[text()='Type']/ancestor::div[@role='listitem']//lightning-formatted-text[@slot='outputField']");
    private By industryFieldLoc = By.xpath("//span[text()='Industry']/ancestor::div[@role='listitem']//lightning-formatted-text[@slot='outputField']");
    private By moreBtn_Loc = By.xpath("(//div[contains(@class,'actionsContainer')]//ul[@class='slds-button-group-list' and @role='presentation'])[position()=1]/li[contains(@class,'slds-dropdown-trigger_clic')]//button");
    public void clickingDetailTab(){
        eleUtil.checkElementClickable(detailTabLoc,20);
        javaScriptUtil.clickElementByJS(detailTabLoc);
    }

    public String getRatingFieldValue(){
        eleUtil.waitForElementVisible(cpaturingRatingfieldLoc,20);
        String ratingFieldvalue = eleUtil.doGetElementText(cpaturingRatingfieldLoc);
        System.out.println("Rating Field Value========>"+ratingFieldvalue);
        return ratingFieldvalue;
    }

    public String getTypeFieldValue(){
        eleUtil.waitForElementVisible(typeFieldLoc,20);
        String typeFieldvalue = eleUtil.doGetElementText(typeFieldLoc);
        System.out.println("type Field Value========>"+typeFieldvalue);
        return typeFieldvalue;
    }

    public String getIndustryFieldValue(){
        eleUtil.waitForElementVisible(industryFieldLoc,20);
        String industryFieldvalue = eleUtil.doGetElementText(industryFieldLoc);
        System.out.println("industry Field Value========>"+industryFieldvalue);
        return industryFieldvalue;
    }

    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    public Accounts clickEditBtnFromMoreBtn(){
        javaScriptUtil.waitForPageLoad(30);
        eleUtil.clickElementWhenReady(moreBtn_Loc, 30);
        javaScriptUtil.selectingDrpDownValuesDynamically(moreBtn_Loc,"//span[text()='Edit']/parent::a[@role='menuitem']");
        return new Accounts(driver);
    }


}
