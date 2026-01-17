package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ReportsDetailPage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;

    public ReportsDetailPage(WebDriver driver) {
        System.out.println("Compiler Came to Report Detail Page class");
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }

    private By clickingAccountDetailPage = By.xpath("//div[@class='data-grid-table-ctr']/table[contains(@class,'data-grid-full-table')]//tbody//td[@role='gridcell']//a");

    public AccountDetailPage openingAccountDetailPageByClickingAccountLink(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.clickElementByJS(clickingAccountDetailPage);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new AccountDetailPage(driver);

    }
}
