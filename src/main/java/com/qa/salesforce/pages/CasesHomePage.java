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


    public Cases doClickingNewCaseCreationBtn(){
        eleUtil.waitForElementVisible(clickingNewBtnOnCaseHomePage_Loc, 200);
        eleUtil.doClick(clickingNewBtnOnCaseHomePage_Loc);
        javaScriptUtil.waitForPageLoad(150);
        return new Cases(driver);
    }
}
