package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class LeadsPage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;
    public LeadsPage(WebDriver driver) {

        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }

    private By enteringfirstNameField_Loc = By.xpath("//label[text()='First Name']//ancestor::lightning-input[@data-field='firstName']//input[@name='firstName']");
    private By enteringlastNameField_Loc = By.xpath("//label[text()='Last Name']//ancestor::lightning-input[@data-field='lastName']//input[@name='lastName']");
    private By enteringCompanyField_Loc = By.xpath("//label[text()='Company']//ancestor::lightning-input[@variant='label-inline']//input[@name='Company']");
    private By clickingLeadSourceBtn_Loc = By.xpath("//label[text()='Lead Source']//ancestor::lightning-picklist//button[@aria-label='Lead Source']");
    private By clickingIndustryBtn_Loc = By.xpath("//label[text()='Industry']//ancestor::lightning-picklist//button[@aria-label='Industry']");
    private By clickingRatingBtn_Loc = By.xpath("//label[text()='Rating']//ancestor::lightning-picklist//button[@aria-label='Rating']");
    private By enteringEmailField_Loc = By.xpath("//label[text()='Email']//ancestor::lightning-input[@variant='label-inline']//input[@name='Email']");
    private By entringPhoneField_Loc = By.xpath("//label[text()='Phone']//ancestor::lightning-input[@variant='label-inline']//input[@name='Phone']");
    private By enteringdescriptionField_Loc = By.xpath("//label[text()='Description']//ancestor::span//textarea[@part='textarea']");
    private By clickingsavebuttonOnCreationPage_Loc = By.xpath("//records-form-footer//ul[@role='presentation']//runtime_platform_actions-action-renderer[@title='Save']//button[@name='SaveEdit']");
    private By enteringnoofEmployeesFieldValue_Loc = By.xpath("//label[text()='No. of Employees']//ancestor::lightning-input[@variant='label-inline']//input[@name='NumberOfEmployees']");
    private By enteringAddressStreetFieldValue_Loc = By.xpath("//label[text()='Street']//ancestor::lightning-input-address[@variant='label-stacked']//textarea[@name='street']");
    private By enteringAddressCityFieldValue_Loc = By.xpath("//label[text()='City']//ancestor::lightning-input[@data-field='city']//input[@name='city']");
    private By enteringAddressStateFieldValue_Loc = By.xpath("//label[text()='State/Province']//ancestor::lightning-input[@data-field='province']//input[@name='province']");
    private By enteringAddressZipCodeFieldValue_Loc = By.xpath("//label[text()='Zip/Postal Code']//ancestor::lightning-input[@data-field='postalCode']//input[@name='postalCode']");
    private By enteringAddressCountryFieldValue_Loc = By.xpath("//label[text()='Country']//ancestor::lightning-input[@data-field='country']//input[@name='country']");

    //Methods
    public void setfirstNameFieldValue(String fName){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringfirstNameField_Loc,fName);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setlastNameFieldValue(String lName){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringlastNameField_Loc,lName);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCompanyNameFieldValue(String company){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringCompanyField_Loc,company);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPhoneNumberFieldValue(String phone){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(entringPhoneField_Loc,phone);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEmailFieldValue(String fName,String lName){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            String email = fName + lName +"@gmail.com";
            eleUtil.doSendKeys(enteringEmailField_Loc,email);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDescriptionFieldValue(String desc){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringdescriptionField_Loc,desc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setLeadSourceFieldValue(String source){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.selectingDrpDownValuesDynamically(clickingLeadSourceBtn_Loc,"//span[text()='"+source+"']");
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void setIndustryFieldValue(String industry){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.selectingDrpDownValuesDynamically(clickingIndustryBtn_Loc,"//span[text()='"+industry+"']");
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void setRatingFieldValue(String rating){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.selectingDrpDownValuesDynamically(clickingRatingBtn_Loc,"//span[text()='"+rating+"']");
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void doClickSavebutton(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doMoveToElementClick(clickingsavebuttonOnCreationPage_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public LeadsDetailPage creatingNewLead(String fName,String lName,String company,String source,
                                String industry,String phone,String rating,String desc){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            setfirstNameFieldValue(fName);
            setlastNameFieldValue(lName);
            setCompanyNameFieldValue(company);
            setLeadSourceFieldValue(source);
            setIndustryFieldValue(industry);
            setPhoneNumberFieldValue(phone);
            setEmailFieldValue(fName,lName);
            setRatingFieldValue(rating);
            setDescriptionFieldValue(desc);
            doClickSavebutton();
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new LeadsDetailPage(driver);
    }

    public void setNoOfEmployeesFieldValue(String empNo){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringnoofEmployeesFieldValue_Loc,empNo);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setStreetFieldValue(String aStreet){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringAddressStreetFieldValue_Loc,aStreet);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void setCityFieldValue(String aCity){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringAddressCityFieldValue_Loc,aCity);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void setStateFieldValue(String aState){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringAddressStateFieldValue_Loc,aState);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPinCodeFieldValue(String aPincode){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringAddressZipCodeFieldValue_Loc,aPincode);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void setCountryFieldValue(String aCountry){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringAddressCountryFieldValue_Loc,aCountry);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public LeadsDetailPage editingLeadDetailPageByClickingEditBtn(String empNo,String aStreet,String aCity,String aState,String aPincode,String aCountry){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            setNoOfEmployeesFieldValue(empNo);
            setStreetFieldValue(aStreet);
            setCityFieldValue(aCity);
            setStateFieldValue(aState);
            setPinCodeFieldValue(aPincode);
            setCountryFieldValue(aCountry);
            doClickSavebutton();
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new LeadsDetailPage(driver);
    }





}
