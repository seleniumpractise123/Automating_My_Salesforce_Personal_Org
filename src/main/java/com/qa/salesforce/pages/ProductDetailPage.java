package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ProductDetailPage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;
    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }
    private By fetchProductCode_Loc = By.xpath("//span[text()='Product Code']//ancestor::div[@role='listitem']//div[contains(@class,'itemBody')]//span[@class='uiOutputText']");

    public String setFetchProductCode(){
        String ProductCodeText = null;
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            ProductCodeText = eleUtil.doGetElementText(fetchProductCode_Loc);
            javaScriptUtil.waitForPageLoad(150);
            System.out.println("Value of the ProductCodeText======>"+ProductCodeText);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ProductCodeText;
    }
}
