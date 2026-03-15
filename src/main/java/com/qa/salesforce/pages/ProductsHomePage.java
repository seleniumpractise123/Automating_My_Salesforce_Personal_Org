package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class ProductsHomePage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;
    private OpportunitiesDetailPage opportunitiesDetailPage;
    public ProductsHomePage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
        opportunitiesDetailPage = new OpportunitiesDetailPage(this.driver);
    }

    private By doClickNextButtonOnProductAddPage_Loc = By.xpath("//span[text()='Next']/parent::button[@title='Next' and @type='button']");
    private By doClickQtyFieldBtn_Loc = By.xpath("//span[text()='Quantity']/ancestor::table[@role='grid' and @data-aura-class='uiVirtualDataTable']/tbody//td//button[contains(@title,'Edit Quantity: Item')]");
    private By doEnterQtyOfProduct_Loc = By.xpath("//label[text()='Quantity']//ancestor::lightning-input[@variant='label-hidden']//input[@part='input']");
    private By doClickSaveBtnOnEditSelectProductPage_Loc = By.xpath("//span[text()='Save']/parent::button[@title='Save']");
    private By searchingProducts_Loc = By.xpath("(//span[text()='Search Products'])[1]/ancestor::div[@data-aura-class='forceMultiAddUsingLVM']//input[@role='combobox' and @title='Search Products']");
    private By searchingProductLink_Loc = By.xpath("(//div[contains(@class,'lookup__menu') and @role='listbox']//span)[3]");
    //private By selectingProduct_Loc = By.xpath("//a[text()='GenWatt Gasoline 2000kW']/ancestor::table[@role='grid']//tbody/tr//td[@role='gridcell']//input[@type='checkbox']");

    public List<WebElement> getListOfProducts() {
        List<WebElement> productsList;
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            productsList = driver.findElements(By.xpath("//span[text()='Quantity']/ancestor::table[@role='grid' and @data-aura-class='uiVirtualDataTable']/tbody//td//button[contains(@title,'Edit Quantity: Item')]"));
            System.out.println("Size of the productList============>" + productsList.size());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return productsList;

    }

    public void selectingSingleProductToOpp(String pName){
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            By selectingSingleProduct_Loc = By.xpath("((//a[text()='"+pName+"']/ancestor::table[@data-aura-class='uiVirtualDataTable']//tr)[5]//td)[2]//input[@type='checkbox']");
            eleUtil.doMoveToElementClick(selectingSingleProduct_Loc);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void clickOnNextButtonOnProductAddPage(){
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doMoveToElementClick(doClickNextButtonOnProductAddPage_Loc);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void doClickAndEnterProductQty(String Qty){
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(300);
            javaScriptUtil.drawBorder(driver.findElement(doClickQtyFieldBtn_Loc));
            eleUtil.doMoveToElementClick(doClickQtyFieldBtn_Loc);
            javaScriptUtil.waitForPageLoad(250);
            eleUtil.doSendKeys(doEnterQtyOfProduct_Loc, Qty);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void doClickSaveBtn(){
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doMoveToElementClick(doClickSaveBtnOnEditSelectProductPage_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public OpportunitiesDetailPage addingMultipleProductsToRecentlyCreateOPP(String products,String quantity){

        try {
            driver.navigate().refresh();
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            opportunitiesDetailPage.setSetClickingOpportunityRelatedListTab();
            opportunitiesDetailPage.setClickingAddProductBtnOnOpportunityRelatedList();
            addingMultipleProducts(products);
            clickOnNextButtonOnProductAddPage();
            doClickAndEnterQtyToMultipleProducts(quantity);
            //doClickAndEnterQtyToMultipleProducts(quantity);
            doClickSaveBtn();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new OpportunitiesDetailPage(driver);

    }

    public OpportunitiesDetailPage addingSingleProductsToRecentlyCreateOPP(String pName1,String qty1){
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            selectingSingleProductToOpp(pName1);
            clickOnNextButtonOnProductAddPage();
            doClickAndEnterProductQty(qty1);
            doClickSaveBtn();
            javaScriptUtil.waitForPageLoad(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new OpportunitiesDetailPage(driver);

    }

    public void addingMultipleProducts(String products){
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            // Split comma-separated values
            String[] productArray = products.split(",");

            for (int i = 0; i < productArray.length; i++) {
                System.out.println("Value of the productArray[i].====>"+productArray[i]);
                searchingAndAddingProduct(productArray[i].trim());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void searchingAndAddingProduct(String product){
        try {
            Thread.sleep(10000);
            eleUtil.waitForElementsVisible(searchingProducts_Loc, 250);
            eleUtil.doMoveToElementClick(searchingProducts_Loc);
            eleUtil.doSendKeys(searchingProducts_Loc,product);
            eleUtil.waitForElementsVisible(searchingProductLink_Loc, 250);
            eleUtil.doMoveToElementClick(searchingProductLink_Loc);
            eleUtil.waitForElementsVisible(By.xpath("//a[text()='"+product+"']/ancestor::table[@role='grid']//tbody/tr//td[@role='gridcell']//input[@type='checkbox']"), 250);
            eleUtil.doMoveToElementClick(By.xpath("//a[text()='"+product+"']/ancestor::table[@role='grid']//tbody/tr//td[@role='gridcell']//input[@type='checkbox']"));
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void doClickAndEnterQtyToMultipleProducts(String pQty){

        try {
            //driver.navigate().refresh();
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);

            int recordCount = getListOfProducts().size();
            System.out.println("Value of the recordCount===========>"+recordCount);
            for(int i=0; i < recordCount;i++){
                List<WebElement> productList = getListOfProducts();
                WebElement productQtyBtnEle = productList.get(i);
                System.out.println("Value of the productQtyBtnEle=========>"+productQtyBtnEle);
                System.out.println("Value of the productQtyBtn name=========>"+productQtyBtnEle.getText().toString());
                actions.moveToElement(productQtyBtnEle).click().build().perform();
                }
            String[] quantityArray = pQty.split(",");
            for(int j = 0; j < quantityArray.length; j++) {
                System.out.println("Value of the quantityArray[i].====>" + quantityArray[j]);
                addingQtyToMultipleProducts(quantityArray[j].trim());
            }
            //opportunitiesDetailPage.setSetClickingOpportunityRelatedListTab();
            //opportunitiesDetailPage.setClickingAddProductBtnOnOpportunityRelatedList();
            /*
            String[] quantityArray = pQty.split(",");
            for (int j = 0; j < quantityArray.length; j++) {
                System.out.println("Value of the quantityArray[i].====>"+quantityArray[j]);
                addingQtyToMultipleProducts(quantityArray[j].trim());
            }

             */

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void addingQtyToMultipleProducts(String pQty){
        try {
            Thread.sleep(1000);
            //eleUtil.waitForElementsVisible(doClickQtyFieldBtn_Loc, 150);
            //eleUtil.doMoveToElementClick(doClickQtyFieldBtn_Loc);
            eleUtil.doSendKeys(doEnterQtyOfProduct_Loc, pQty);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
