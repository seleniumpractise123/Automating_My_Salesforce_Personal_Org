package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class Cases {
    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;
    public Cases(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }

    private By accountName_Loc = By.xpath("//label[text()='Account Name']/ancestor::lightning-lookup-desktop//input[contains(@placeholder,'Search Accounts...')]");
    private By clickingSearchIcon = By.xpath("(//lightning-base-combobox-item[@data-value='actionAdvancedSearch' and @role='option']//span)[2]");
    private By selectingAccountValue_loc = By.xpath("//div[@class='dt-outer-container']//table[contains(@class,'slds-table_resizable-cols')]//tbody/tr[@class='slds-hint-parent']//td[@role='gridcell']//span[@class='slds-radio']/input[@type='radio']");
    private By clickingSelectBtnOnLookupPage_Loc = By.xpath("//lightning-modal-footer[@class='lightning-lookup-advanced-modal__footer']//button[@kx-scope='button-brand']");
    private By clickingSaveBtn_Loc = By.xpath("(//records-form-footer[contains(@class,'slds-docked-form-footer')]//ul[@role='presentation']//li)[position()=3]//button[@name='SaveEdit']");
    private By doClickTypeFieldBtn_Loc = By.xpath("//label[text()='Type']//ancestor::span[contains(@class,'test-id__field-value')]//button[@role='combobox' and @aria-haspopup='listbox']");
    private By doClickCaseReasonFieldBtn_Loc = By.xpath("//label[text()='Case Reason']//ancestor::span[contains(@class,'test-id__field-value')]//button[@role='combobox' and @aria-haspopup='listbox']");
    private By doClickCaseOriginFieldBtn_Loc = By.xpath("//label[text()='Case Origin']//ancestor::span[contains(@class,'test-id__field-value')]//button[@role='combobox' and @aria-haspopup='listbox']");
    private By enterSubjectField_Loc = By.xpath("//label[text()='Subject']//ancestor::span[contains(@class,'test-id__field-value')]//input[@name='Subject']");
    private By enterDescriptionField_Loc = By.xpath("//label[text()='Description']//ancestor::span[contains(@class,'test-id__field-value')]//textarea[@part='textarea']");
    private By contactName_Loc = By.xpath("//label[text()='Contact Name']/ancestor::lightning-lookup-desktop//input[contains(@placeholder,'Search Contacts...')]");
    private By selectintingContactFieldValue_loc = By.xpath("//div[@class='dt-outer-container']//table[contains(@class,'slds-table_resizable-cols')]//tbody/tr[@class='slds-hint-parent']//td[@role='gridcell']//span[@class='slds-radio']/input[@type='radio']");
    private By enteringWebEmailField_Loc = By.xpath("//label[text()='Web Email']//ancestor::lightning-input[@slot='inputField']//input[@name='SuppliedEmail']");
    private By eneringWebNameField_Loc = By.xpath("//label[text()='Web Name']//ancestor::lightning-input[@variant='label-inline']//input[@name='SuppliedName']");
    private By enteringWebCompanyField_Loc = By.xpath("//label[text()='Web Company']//ancestor::lightning-input[@variant='label-inline']//input[@name='SuppliedCompany']");
    private By enteringWebPhoneCompanyField_Loc = By.xpath("//label[text()='Web Phone']//ancestor::lightning-input[@variant='label-inline']//input[@name='SuppliedPhone']");
    private By clickingProductBtnField_Loc = By.xpath("//label[text()='Product']//ancestor::lightning-picklist//button[@type='button' and @aria-label='Product']");

    public void setAccountNameFieldValue(String custID){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.drawBorder(driver.findElement(accountName_Loc));
            //eleUtil.doClick(accountName_Loc);
            javaScriptUtil.clickElementByJS(accountName_Loc);
            eleUtil.doSendKeys(accountName_Loc,custID);
            actions.moveToElement(driver.findElement(accountName_Loc)).sendKeys(Keys.ENTER).build().perform();
            Thread.sleep(10000);
            javaScriptUtil.drawBorder(driver.findElement(clickingSearchIcon));
            //eleUtil.doClick(clickingSearchIcon);
            //eleUtil.doMoveToElementClick(clickingSearchIcon);
            javaScriptUtil.clickElementByJS(clickingSearchIcon);
            Thread.sleep(10000);
            javaScriptUtil.drawBorder(driver.findElement(selectingAccountValue_loc));
            javaScriptUtil.clickElementByJS(selectingAccountValue_loc);
            Thread.sleep(10000);
            javaScriptUtil.drawBorder(driver.findElement(clickingSelectBtnOnLookupPage_Loc));
            javaScriptUtil.clickElementByJS(clickingSelectBtnOnLookupPage_Loc);
            //eleUtil.doMoveToElementClick(clickingSelectBtnOnLookupPage_Loc);
            //eleUtil.doClick(clickingSelectBtnOnLookupPage_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setContactNameFieldValue(String contactID){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.drawBorder(driver.findElement(contactName_Loc));
            eleUtil.doClick(contactName_Loc);
            eleUtil.doSendKeys(contactName_Loc,contactID);
            actions.moveToElement(driver.findElement(contactName_Loc)).sendKeys(Keys.ENTER).build().perform();
            Thread.sleep(10000);
            javaScriptUtil.drawBorder(driver.findElement(clickingSearchIcon));
            eleUtil.doMoveToElementClick(clickingSearchIcon);
            Thread.sleep(10000);
            javaScriptUtil.drawBorder(driver.findElement(selectintingContactFieldValue_loc));
            javaScriptUtil.clickElementByJS(selectintingContactFieldValue_loc);
            Thread.sleep(10000);
            javaScriptUtil.drawBorder(driver.findElement(clickingSelectBtnOnLookupPage_Loc));
            javaScriptUtil.clickElementByJS(clickingSelectBtnOnLookupPage_Loc);
            //eleUtil.doMoveToElementClick(clickingSelectBtnOnLookupPage_Loc);
            //eleUtil.doClick(clickingSelectBtnOnLookupPage_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void doClickAndSetTypePicklistValue(String typePickValue){
        eleUtil.checkElementClickable(doClickTypeFieldBtn_Loc, 200);
        javaScriptUtil.selectingDrpDownValuesDynamically(doClickTypeFieldBtn_Loc,"//span[text()='"+typePickValue+"']");
    }

    public void doClickAndSetCaseReasonPicklistValue(String reasonPickValue){
        eleUtil.checkElementClickable(doClickCaseReasonFieldBtn_Loc, 200);
        javaScriptUtil.selectingDrpDownValuesDynamically(doClickCaseReasonFieldBtn_Loc,"//span[text()='"+reasonPickValue+"']");
    }

    public void doClickAndSetCaseOriginPicklistValue(String originPickValue){
        eleUtil.checkElementClickable(doClickCaseOriginFieldBtn_Loc, 200);
        javaScriptUtil.selectingDrpDownValuesDynamically(doClickCaseOriginFieldBtn_Loc,"//span[text()='"+originPickValue+"']");
    }

    public void setEnterSubjectFieldValue(String subj){
        eleUtil.waitForElementsVisible(enterSubjectField_Loc, 150);
        eleUtil.doSendKeys(enterSubjectField_Loc, subj);
        javaScriptUtil.waitForPageLoad(150);
    }

    public void setEnterDescriptionFieldValue(String desc){
        eleUtil.waitForElementsVisible(enterDescriptionField_Loc, 150);
        eleUtil.doSendKeys(enterDescriptionField_Loc, desc);
        javaScriptUtil.waitForPageLoad(150);
    }

    public void doClickSaveBtn(){
        eleUtil.waitForElementVisible(clickingSaveBtn_Loc, 200);
        javaScriptUtil.drawBorder(driver.findElement(clickingSaveBtn_Loc));
        eleUtil.doClick(clickingSaveBtn_Loc);
        javaScriptUtil.waitForPageLoad(200);

    }

    public CasesDetailPage doCreateNewCase(String contactID,String custID,String typePickValue,String reasonPickValue,
                                           String originPickValue,String subj,String desc){

        boolean flag = false;
        try {
            Thread.sleep(10000);
            while(true){
                setAccountNameFieldValue(custID);
                setContactNameFieldValue(contactID);
                doClickAndSetTypePicklistValue(typePickValue);
                doClickAndSetCaseReasonPicklistValue(reasonPickValue);
                doClickAndSetCaseOriginPicklistValue(originPickValue);
                setEnterSubjectFieldValue(subj);
                setEnterDescriptionFieldValue(desc);
                javaScriptUtil.waitForPageLoad(200);
                doClickSaveBtn();
                Thread.sleep(4000);
                break;

            }
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new CasesDetailPage(driver);

    }

    public AccountDetailPage doCreateNewCaseFromAccountRelatedList(String contactID,String typePickValue,String reasonPickValue,
                                           String originPickValue,String subj,String desc){

        boolean flag = false;
        try {
            Thread.sleep(10000);
            while(true){
                setContactNameFieldValue(contactID);
                doClickAndSetTypePicklistValue(typePickValue);
                doClickAndSetCaseReasonPicklistValue(reasonPickValue);
                doClickAndSetCaseOriginPicklistValue(originPickValue);
                setEnterSubjectFieldValue(subj);
                setEnterDescriptionFieldValue(desc);
                javaScriptUtil.waitForPageLoad(200);
                doClickSaveBtn();
                Thread.sleep(4000);
                break;

            }
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new AccountDetailPage(driver);

    }

    public ContactDetailPage doCreateNewCaseFromContactRelatedList(String typePickValue,String reasonPickValue,
                                                                   String originPickValue,String subj,String desc){

        boolean flag = false;
        try {
            Thread.sleep(10000);
            while(true){
                doClickAndSetTypePicklistValue(typePickValue);
                doClickAndSetCaseReasonPicklistValue(reasonPickValue);
                doClickAndSetCaseOriginPicklistValue(originPickValue);
                setEnterSubjectFieldValue(subj);
                setEnterDescriptionFieldValue(desc);
                javaScriptUtil.waitForPageLoad(200);
                doClickSaveBtn();
                Thread.sleep(4000);
                break;

            }
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new ContactDetailPage(driver);

    }

    public void doEnterWebEmailFieldValue(String email){
        eleUtil.waitForElementVisible(enteringWebEmailField_Loc, 200);
        eleUtil.doSendKeys(enteringWebEmailField_Loc, email);
        javaScriptUtil.waitForPageLoad(150);
    }

    public void doEnterWebNameFieldValue(String name){
        eleUtil.waitForElementVisible(eneringWebNameField_Loc, 200);
        eleUtil.doSendKeys(eneringWebNameField_Loc,name);
        javaScriptUtil.waitForPageLoad(150);
    }

    public void doEnterWebCompanyFieldValue(String company){
        eleUtil.waitForElementVisible(enteringWebCompanyField_Loc, 200);
        eleUtil.doSendKeys(enteringWebCompanyField_Loc,company);
        javaScriptUtil.waitForPageLoad(150);
    }

    public void doEnterWebPhoneFieldValue(String phone){
        eleUtil.waitForElementVisible(enteringWebPhoneCompanyField_Loc, 200);
        eleUtil.doSendKeys(enteringWebPhoneCompanyField_Loc,phone);
        javaScriptUtil.waitForPageLoad(150);
    }

    public void doEnterProductFieldValue(String pName){
        eleUtil.waitForElementVisible(clickingProductBtnField_Loc, 200);
        javaScriptUtil.selectingDrpDownValuesDynamically(clickingProductBtnField_Loc, "//span[text()='GC1040']");
        javaScriptUtil.waitForPageLoad(150);
    }

    public CasesDetailPage editingRecentlyCreatedCaseByUsingEditBtn(String email,String name,String company,String phone,String pName){
        boolean flag = false;
        try {
            Thread.sleep(10000);
            while(true){
                doEnterWebEmailFieldValue(email);
                doEnterWebNameFieldValue(name);
                doEnterWebCompanyFieldValue(company);
                doEnterWebPhoneFieldValue(phone);
                doEnterProductFieldValue(pName);
                javaScriptUtil.waitForPageLoad(200);
                doClickSaveBtn();
                Thread.sleep(4000);
                break;

            }
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new CasesDetailPage(driver);
    }
}
