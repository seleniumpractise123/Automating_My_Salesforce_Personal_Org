package com.qa.salesforce.pages;

import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.JavaScriptUtil;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class LeadsDetailPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    private JavaScriptUtil javaScriptUtil;
    private Actions actions;
    public LeadsDetailPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        actions = new Actions(this.driver);
    }

    private By clickingDetailTab_Loc = By.xpath("//lightning-tab-bar[@variant='standard']//ul[@role='tablist']//li[@title='Details']/a");
    private By fetchLeadSourceFieldValue_Loc = By.xpath("//span[text()='Lead Source']//ancestor::records-record-layout-item[@field-label='Lead Source']//lightning-formatted-text[@slot='outputField']");
    private By fetchIndustryFieldValue_Loc = By.xpath("//span[text()='Industry']//ancestor::records-record-layout-item[@field-label='Industry']//lightning-formatted-text[@slot='outputField']");
    private By fetchRatingFieldValue_Loc = By.xpath("//span[text()='Rating']//ancestor::records-record-layout-item[@field-label='Rating']//lightning-formatted-text[@slot='outputField']");
    private By fetchLeadIDFieldValue_Loc = By.xpath("//span[text()='Custom_Lead ID']//ancestor::records-record-layout-item[@field-label='Custom_Lead ID']//lightning-formatted-text[@slot='outputField']");
    private By moreBtn_Loc = By.xpath("(//div[contains(@class,'actionsContainer')]//ul[@class='slds-button-group-list' and @role='presentation'])[position()=1]/li[contains(@class,'slds-dropdown-trigger_clic')]//button");
    private By clickingInlineEditBtn_Loc = By.xpath("//records-record-layout-item[@field-label='Mobile']//div[@class='slds-form-element__control']/button[@title='Edit Mobile']");
    private By enteringMobilePhoneField_Loc = By.xpath("//label[text()='Mobile']/ancestor::lightning-input[@variant='label-inline']//input[@name='MobilePhone']");
    private By enteringWebsiteField_Loc = By.xpath("//label[text()='Website']/ancestor::lightning-input[@variant='label-inline']//input[@name='Website']");
    private By enteringSICCodeField_Loc = By.xpath("//label[text()='SIC Code']/ancestor::lightning-input[@variant='label-inline']//input[@name='SICCode__c']");
    private By enteringNumberOfLocationField_Loc = By.xpath("//label[text()='Number of Locations']/ancestor::lightning-input[@variant='label-inline']//input[@name='NumberofLocations__c']");
    private By enteringCurrentGeneratorField_Loc = By.xpath("//label[text()='Current Generator(s)']/ancestor::lightning-input[@variant='label-inline']//input[@name='CurrentGenerators__c']");
    private By clickingProductInterestField_Loc = By.xpath("//label[text()='Product Interest']//ancestor::records-record-picklist[@slot='inputField']//button[@aria-label='Product Interest']");
    private By clikcingPrimaryInterestField_Loc = By.xpath("//label[text()='Primary']//ancestor::records-record-picklist[@slot='inputField']//button[@aria-label='Primary']");
    private By clickingSaveBtnOnDetailTab_Loc = By.xpath("//records-form-footer[contains(@class,'stickyFooter')]//ul[@role='presentation']//runtime_platform_actions-action-renderer[@title='Save']//button[@name='SaveEdit']");
    private By clickingMarkStageBtn_Loc = By.xpath("//div[@class='runtime_sales_pathassistantCollapsibleDrawer']//button[contains(@class,'slds-path__mark-complete')]");
    private By fetchLeadStatusFieldValue_Loc = By.xpath("(//span[text()='Lead Status'])[last()]/ancestor::records-record-layout-item[@field-label='Lead Status']//lightning-formatted-text[@slot='outputField']");
    private By enteringOppNameOnCOnvertPage_Loc = By.xpath("//span[text()='Opportunity Name']//ancestor::div[@class='createPanelExpanded']//input[@type='text' and @class=' input']");
    private By clickingCOnvertBtn_Loc = By.xpath("(//div[@class='modal-footer slds-modal__footer']//button)[2]");
    private By clickingConvertedOpp_Loc = By.xpath("((//div[@class='containerBodyPadding']//div[@class='recordLayout'])[last()]//div[@class='bodyConvertedItem']//a)[position()=1]");
    private By clickingDetailTabAfterReportLoc = By.xpath("//a[text()='Details']");


    public void doClickDetailTab(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            //eleUtil.doMoveToElementClick(clickingDetailTab_Loc);
            javaScriptUtil.clickElementByJS(clickingDetailTab_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void clickDetailTabBtnThroughReports(){
        try {
            Thread.sleep(15000);
            driver.navigate().refresh();
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.drawBorder(driver.findElement(clickingDetailTabAfterReportLoc));
            eleUtil.doMoveToElementClick(clickingDetailTabAfterReportLoc);
            javaScriptUtil.waitForPageLoad(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getLeadSourceFieldValue(){
        eleUtil.waitForElementVisible(fetchLeadSourceFieldValue_Loc,150);
        String LeadSourceFieldvalue = eleUtil.doGetElementText(fetchLeadSourceFieldValue_Loc);
        System.out.println("Lead Source Field Value========>"+LeadSourceFieldvalue);
        return LeadSourceFieldvalue;
    }

    public String getIndustryFieldValue(){
        eleUtil.waitForElementVisible(fetchIndustryFieldValue_Loc,150);
        String IndustryFieldvalue = eleUtil.doGetElementText(fetchIndustryFieldValue_Loc);
        System.out.println("Industry Field Value========>"+IndustryFieldvalue);
        return IndustryFieldvalue;
    }

    public String getRatingFieldValue(){
        //eleUtil.waitForElementVisible(fetchRatingFieldValue_Loc,250);
        String RatingFieldvalue = null;
        try {
            Thread.sleep(10000);
            javaScriptUtil.waitForPageLoad(150);
            RatingFieldvalue = eleUtil.doGetElementText(fetchRatingFieldValue_Loc);
            System.out.println("Rating Field Value========>"+RatingFieldvalue);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return RatingFieldvalue;
    }

    public String getLeadCurrentURL(){
          String LeadURL = null;
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            LeadURL = driver.getCurrentUrl();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return LeadURL;
    }

    public String getLeadIDFieldValue(){
        //eleUtil.waitForElementVisible(fetchLeadIDFieldValue_Loc,150);
        String LeadIDFieldvalue = null;
        try {
            Thread.sleep(10000);
            LeadIDFieldvalue = eleUtil.doGetElementText(fetchLeadIDFieldValue_Loc);
            System.out.println("Lead ID Field Value========>"+LeadIDFieldvalue);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return LeadIDFieldvalue;
    }

    public LeadsPage clickEditBtnFromMoreBtn(){
        try {
            Thread.sleep(20000);
            javaScriptUtil.waitForPageLoad(30);
            javaScriptUtil.selectingDrpDownValuesDynamically(moreBtn_Loc,"//span[text()='Edit']/parent::a[@role='menuitem']");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new LeadsPage(driver);
    }

    public void doClickInlineEditBtn(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doMoveToElementClick(clickingInlineEditBtn_Loc);
            Thread.sleep(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setHomePhoneFieldValue(String hPhone){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringMobilePhoneField_Loc,hPhone);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setWebsiteFieldValue(String website){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringWebsiteField_Loc,website);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setProductInterestFieldValue(String productInterest){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.selectingDrpDownValuesDynamically(clickingProductInterestField_Loc,"//span[text()='"+productInterest+"']");
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public void setCurrentGeneratorFieldValue(String generator){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringCurrentGeneratorField_Loc,generator);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSICCodeFieldValue(String sicCode){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringSICCodeField_Loc,sicCode);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void setNumberOfLocationsFieldValue(String Locations){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doSendKeys(enteringNumberOfLocationField_Loc,Locations);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPrimaryFieldValue(String primary){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.selectingDrpDownValuesDynamically(clikcingPrimaryInterestField_Loc,"//span[text()='"+primary+"']");
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void doClickingSaveBtnOnDetailPage(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            eleUtil.doMoveToElementClick(clickingSaveBtnOnDetailTab_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void editingLeadDetailPageByClickingInlineEditProcess(String hPhone,String website,String productInterest,String sicCode,
                                                           String Locations,String generator,String primary){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            setHomePhoneFieldValue(hPhone);
            setWebsiteFieldValue(website);
            setProductInterestFieldValue(productInterest);
            setSICCodeFieldValue(sicCode);
            setNumberOfLocationsFieldValue(Locations);
            setCurrentGeneratorFieldValue(generator);
            setPrimaryFieldValue(primary);
            doClickingSaveBtnOnDetailPage();
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void doClickMarkStatusAsCompleteBtn(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(250);
            javaScriptUtil.drawBorder(driver.findElement(clickingMarkStageBtn_Loc));
            eleUtil.doMoveToElementClick(clickingMarkStageBtn_Loc);
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public String getLeadStatusFieldValue(){
        eleUtil.waitForElementVisible(fetchLeadStatusFieldValue_Loc,150);
        String leadStatusFieldvalue = eleUtil.doGetElementText(fetchLeadStatusFieldValue_Loc);
        System.out.println("Lead Status Field Value========>"+leadStatusFieldvalue);
        return leadStatusFieldvalue;
    }

    public void doClickConvertBtn(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.selectingDrpDownValuesDynamically(moreBtn_Loc,"//span[text()='Convert']/parent::a[@role='menuitem']");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void doSelectMatchingAccountOption(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            WebElement matchAccEle = driver.findElement(By.xpath("//legend[contains(text(),'Account Matches')]"));
            javaScriptUtil.drawBorder(driver.findElement(By.xpath("//legend[contains(text(),'Account Matches')]")));
            List<WebElement> dupLeadsList = eleUtil.getElements(By.xpath("//div[@class='duplistContainer']//div[@class='uiAbstractList']//div[contains(@class,'forceRecordLayout')]"));
            if(matchAccEle.isDisplayed() && dupLeadsList.size() > 0){
                javaScriptUtil.waitForPageLoad(250);
                //By selectingNewAccountOption_Loc = By.xpath("//span[text()=' Create New Account']//ancestor::div[@class='createPanel']//span[@class='slds-radio']");
                //javaScriptUtil.drawBorder(driver.findElement(selectingNewAccountOption_Loc));
                //eleUtil.doMoveToElementClick(selectingNewAccountOption_Loc);
                System.out.println("Driver came to if Part");
                javaScriptUtil.waitForPageLoad(250);
                By selectingExistingAccountOption_Loc = By.xpath("//span[text()='Choose Existing Account']//ancestor::div[@class='matchPanel']//span[@class='slds-radio']");
                javaScriptUtil.drawBorder(driver.findElement(selectingExistingAccountOption_Loc));
                eleUtil.doMoveToElementClick(selectingExistingAccountOption_Loc);
                //javaScriptUtil.waitForPageLoad(250);
                Thread.sleep(10000);
                By selectingExistingAccName_Loc = By.xpath("((//span[contains(text(),'Yc0AfBaymontwNGbd')]//ancestor::div[@class='duplistContainer']//div[@class='radioButton'])[1]//span)[1]");
                javaScriptUtil.drawBorder(driver.findElement(selectingExistingAccName_Loc));
                eleUtil.doMoveToElementClick(selectingExistingAccName_Loc);
                //javaScriptUtil.clickElementByJS(selectingExistingAccName_Loc);
                javaScriptUtil.waitForPageLoad(250);
            }
            else{
                System.out.println("No Matching accounts found, COnvert to new account");



            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void doEnterConvertedOppName(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            By pageHeader_Loc = By.xpath("//div[contains(@class,'slds-modal__container')]//h1");
            javaScriptUtil.drawBorder(driver.findElement(pageHeader_Loc));
            String pageHeader = eleUtil.doGetElementText(pageHeader_Loc);
            System.out.println("Value of the pageHeader======>"+pageHeader);
            if(pageHeader.contains("Convert Lead")){
                System.out.println("Driver Came to If Part");
                javaScriptUtil.scrollIntoView(driver.findElement(By.xpath("//div[contains(text(),'create an opportunity upon conversion')]")));
                Thread.sleep(20000);
                By clickingOppBtn_Loc = By.xpath("(//div[contains(@class,'createPanelDetail')])[last()]//button[@type='button']");
                Thread.sleep(15000);
                javaScriptUtil.drawBorder(driver.findElement(clickingOppBtn_Loc));
                eleUtil.doMoveToElementClick(clickingOppBtn_Loc);
                Thread.sleep(15000);
                javaScriptUtil.drawBorder(driver.findElement(enteringOppNameOnCOnvertPage_Loc));
                driver.findElement(enteringOppNameOnCOnvertPage_Loc).sendKeys("+Test");
                Thread.sleep(15000);
            }else{
                System.out.println("driver came to else part");
                javaScriptUtil.scrollIntoView(driver.findElement(By.xpath("//div[contains(text(),'create an opportunity upon conversion')]")));
                Thread.sleep(20000);
                By clickingOppBtn_Loc = By.xpath("(//div[contains(@class,'createPanelDetail')])[last()]//button[@type='button']");
                Thread.sleep(15000);
                javaScriptUtil.drawBorder(driver.findElement(clickingOppBtn_Loc));
                eleUtil.doMoveToElementClick(clickingOppBtn_Loc);
                Thread.sleep(15000);
                javaScriptUtil.drawBorder(driver.findElement(enteringOppNameOnCOnvertPage_Loc));
                driver.findElement(enteringOppNameOnCOnvertPage_Loc).sendKeys("+Test");

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void doClickConvertBtnOnConvertPage(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            javaScriptUtil.drawBorder(driver.findElement(clickingCOnvertBtn_Loc));
            eleUtil.doMoveToElementClick(clickingCOnvertBtn_Loc);
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    /*
    public void clickConvertBtnFromMoreBtn(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            doClickConvertBtn();
            doEnterConvertedOppName();
            doClickConvertBtnOnConvertPage();
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

     */

    public OpportunitiesDetailPage doClickConvertedOpp(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(250);
            eleUtil.doMoveToElementClick(clickingConvertedOpp_Loc);
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
      return new OpportunitiesDetailPage(driver);

    }

    public void doLeadConversionProcess(){
        try {
            Thread.sleep(15000);
            javaScriptUtil.waitForPageLoad(150);
            doClickConvertBtn();
            doSelectMatchingAccountOption();
            doEnterConvertedOppName();
            doClickConvertBtnOnConvertPage();
            javaScriptUtil.waitForPageLoad(150);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }








}
