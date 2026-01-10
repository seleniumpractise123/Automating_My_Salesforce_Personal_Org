package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountHomePage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;

    //1.Const. of the page class
    public AccountHomePage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(this.driver);
    }

    //2.Private By locators.
    private By NewBtnLoc = By.xpath("//div[@class='slds-align_absolute-center']//a[@title='New']");

    public Accounts creatingNewAccountCreationBtn(){
        boolean flag = false;
        while(true){
            eleUtil.doClick(NewBtnLoc, 10);
            break;
        }

        return new Accounts(driver);
    }
}