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
    private OpportunitiesDetailPage opportunitiesDetailPage;
    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
        opportunitiesDetailPage = new OpportunitiesDetailPage(this.driver);
    }
    private By fetchProductCode_Loc = By.xpath("//span[text()='Product Code']//ancestor::div[@role='listitem']//div[contains(@class,'itemBody')]//span[@class='uiOutputText']");
    private By fetchTotalPrice_Loc = By.xpath("//span[text()='Total Price']//ancestor::div[@data-target-selection-name='sfdc:RecordField.OpportunityLineItem.TotalPrice']//span[@class='forceOutputCurrency']");
    private By fetchOppName_Loc = By.xpath("//span[text()='Opportunity']//ancestor::div[@data-target-selection-name='sfdc:RecordField.OpportunityLineItem.OpportunityId']//div[@data-aura-class='forceOutputLookupWithPreview']/a");
    private By fetchOppAmount_Loc = By.xpath("//span[text()='Amount']//ancestor::div[@data-target-selection-name='sfdc:RecordField.Opportunity.Amount']//lightning-formatted-text");
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

    public boolean fetchTotalPriceFieldValue(){
        String TotalPriceFIeldValueText = null;
        double TotalPriceFIeldValue = 0;
        String oppAmountFieldValueText = null;
        double oppAmountFIeldValue = 0;
        boolean oppProductTotalPriceAndOppAmountEqualCheck = false;
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            TotalPriceFIeldValueText = eleUtil.doGetElementText(fetchTotalPrice_Loc).replace(",", "").replace("$", "");
            javaScriptUtil.waitForPageLoad(150);
            System.out.println("Value of the Total Price Text======>"+TotalPriceFIeldValueText);
            //TotalPriceFIeldValue = TotalPriceFIeldValueText.replace("$", "");
            TotalPriceFIeldValue = Double.parseDouble(TotalPriceFIeldValueText);
            System.out.println("Value of the Total Price ======>"+TotalPriceFIeldValue);
            javaScriptUtil.clickElementByJS(fetchOppName_Loc);
            opportunitiesDetailPage.doClickDetailTab();
            oppAmountFieldValueText = eleUtil.doGetElementText(fetchOppAmount_Loc).replace(",", "").replace("$", "");
            System.out.println("Value of the Opportunity Amount Text======>"+oppAmountFieldValueText);
            oppAmountFIeldValue = Double.parseDouble(oppAmountFieldValueText);
            System.out.println("Value of the Opportunity Amount ======>"+oppAmountFIeldValue);
            if(TotalPriceFIeldValue == oppAmountFIeldValue){
                oppProductTotalPriceAndOppAmountEqualCheck = true;
            }
            else{
                oppProductTotalPriceAndOppAmountEqualCheck = false;
            }



        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return oppProductTotalPriceAndOppAmountEqualCheck;
    }
}
