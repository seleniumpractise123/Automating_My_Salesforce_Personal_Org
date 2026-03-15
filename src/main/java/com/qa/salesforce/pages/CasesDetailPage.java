package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class CasesDetailPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;
    public CasesDetailPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }

    private By fetchStatusField_Loc = By.xpath("//span[text()='Status']//ancestor::div[contains(@class,'slds-form-element_horizontal')]//lightning-formatted-text[@slot='outputField']");

    public String fetchCaseStatusFieldValue(){
        String CaseStatus = null;
        try {
            driver.navigate().refresh();
            Thread.sleep(10000);
            eleUtil.waitForElementsVisible(fetchStatusField_Loc, 300);
            javaScriptUtil.drawBorder(driver.findElement(fetchStatusField_Loc));
            CaseStatus = eleUtil.doGetElementText(fetchStatusField_Loc);
            System.out.println("Value of the CaseStatus==========>"+CaseStatus);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return CaseStatus;
    }
}
