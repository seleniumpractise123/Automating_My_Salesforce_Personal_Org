package com.qa.salesforce.tests;

import com.qa.salesforce.base.BaseTest;
import com.qa.salesforce.pages.AccountHomePage;
import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.ExcelUtil;
import com.qa.salesforce.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.*;

public class AccountScenarioTest extends BaseTest {
    
    @BeforeClass
    public void loginSetup(){
        qsalesHomePage = loginPage.switchingSalesApp(prop.getProperty("username"), prop.getProperty("password"),"Sales");
        System.out.println("Value of the qsalesHomepage====> " + qsalesHomePage);
    }

    @DataProvider(name = "AccCreationData")
    public Object[][] getAccData(){
        Object[][] dataMap = null;
        try {
            dataMap = ExcelUtil.getTestData("Creating New Account");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataMap;
    }

    @DataProvider(name = "AccDataForEdit")
    public Object[][] getAccDataForEdit(){
        Object[][] dataMap = null;
        try {
            dataMap = ExcelUtil.getTestData("Editing Account");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataMap;
    }

    @DataProvider(name = "AccDataForInlineEdit")
    public Object[][] getAccDataForInlineEdit(){
        Object[][] dataMap = null;
        try {
            dataMap = ExcelUtil.getTestData("Editing Account By Clicking Inline Btn");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataMap;
    }

    @Test(description = "Creating New Account in My Personal Salesforce Org", dataProvider = "AccCreationData")
    void Creating_New_Account(String accName,String accNumber,String description,String sStreet,
                              String sState,String sCity, String sCounty,String sPostalCode){

        accountHomePage = qsalesHomePage.clickingAccountsTab();


        accounts = accountHomePage.creatingNewAccountCreationBtn();


        String ranaccName = ElementUtil.generateRandomString(5)+accName;
        accountDetailPage = accounts.CreateNewAccount(ranaccName,accNumber,description,sStreet,sState,sCity,sCounty,sPostalCode);


        accountDetailPage.clickingDetailTab();


        String ActualratingFieldValue = accountDetailPage.getRatingFieldValue();
        Assert.assertEquals(ActualratingFieldValue,"Warm");

        String ActualTypeFieldValue = accountDetailPage.getTypeFieldValue();
        Assert.assertEquals(ActualTypeFieldValue,"Customer - Direct");

        String ActualIndustryFieldValue = accountDetailPage.getIndustryFieldValue();
        Assert.assertEquals(ActualIndustryFieldValue,"Communications");

        String custID = accountDetailPage.getCustomerIDFieldValue();
        PropertyReader.writingDataIntoTextFile("LatestAccountURL",accountDetailPage.getCurrentURL());
        PropertyReader.writingDataIntoTextFile("LatestCustomerID",custID);




    }

    @Test(description = "Editing Recently created Account record By clicking Edit button", dataProvider = "AccDataForEdit",dependsOnMethods = "Creating_New_Account")
    public void editingAccountPageByClickingEditButton(String bStreet,
                                                       String bState,String bCity, String bCounty,String bPostalCode){

      accountDetailPage = qsalesHomePage.navigatingToAccountDetailPageByUsingUrl(PropertyReader.readDataFromFile("LatestAccountURL"));
      accounts = accountDetailPage.clickEditBtnFromMoreBtn();
      accountDetailPage = accounts.updateBillingAddressByClickingEditbutton(bStreet,bState,bCity,bCounty,bPostalCode);
      //
    }

    @Test(description = "Editing Account through Inline Editor", dataProvider = "AccDataForInlineEdit",dependsOnMethods = "editingAccountPageByClickingEditButton")
    public void editingAccountPageByClickingInlineEditing(String slaNumber,String nLocations){
        accountDetailPage = qsalesHomePage.navigatingToAccountDetailPageByUsingUrl(PropertyReader.readDataFromFile("LatestAccountURL"));
        accountDetailPage.clickingDetailTab();
        accountDetailPage.setClickingInlineEditBtn();
        accountDetailPage.editingAccountPageByClickingInlineEditing(slaNumber,nLocations);

        String ActualOwnerShipFieldValue = accountDetailPage.getOwnerShipFieldValue();
        Assert.assertEquals(ActualOwnerShipFieldValue,"Public");

        String ActualCustomerPriorityFieldValue = accountDetailPage.getCustomerPriorityFieldValue();
        Assert.assertEquals(ActualCustomerPriorityFieldValue,"Medium");

        String ActualSLAFieldValue = accountDetailPage.getSLAFieldValue();
        Assert.assertEquals(ActualSLAFieldValue,"Silver");

        String ActualUpsellOpportunityFieldValue = accountDetailPage.getUpsellOpportunityFieldValue();
        Assert.assertEquals(ActualUpsellOpportunityFieldValue,"Maybe");

        String ActualActiveFieldValue = accountDetailPage.getActiveFieldValue();
        Assert.assertEquals(ActualActiveFieldValue,"No");
    }
/*
    @Test(description = "Fetching Account SFDC ID though SOQL," +
            "Pasting that ID in URL and Navigating to Account Detail Page")
    public void navigatingAccountDetailPageThroughSFDCID(){
        String custID = qsalesHomePage.getCustID(PropertyReader.readDataFromFile());
        System.out.println("Value of the CUstID ===> " + custID);
        String AccountSFDCID = qsalesHomePage.setClickingGearIcon_Loc(custID);
        accountDetailPage = qsalesHomePage.navigateToAccountDetailPageThroughSFDCID(AccountSFDCID);
        accountDetailPage.clickingDetailTab();
        String ActualTypeFieldValue = accountDetailPage.getTypeFieldValue();
        Assert.assertEquals(ActualTypeFieldValue,"Customer - Direct");

        String ActualIndustryFieldValue = accountDetailPage.getIndustryFieldValue();
        Assert.assertEquals(ActualIndustryFieldValue,"Communications");

    }

 */
    @Test(description = "Searching recently created Account globally by using customer ID",dependsOnMethods = "editingAccountPageByClickingInlineEditing")
    public void searchingAccountCustomerIDThroguhGlobalSearch(){
        String custID = qsalesHomePage.getCustID(PropertyReader.readDataFromFile("LatestCustomerID"));
        System.out.println("Value of the CUstID ===> " + custID);

        accountDetailPage = qsalesHomePage.searchCustIDGlobally(custID);
        accountDetailPage.clickingDetailTab();
        String ActualTypeFieldValue = accountDetailPage.getTypeFieldValue();
        Assert.assertEquals(ActualTypeFieldValue,"Customer - Direct");


    }

    @Test(description = "Searching recently created Account though List view using customer ID",dependsOnMethods = "searchingAccountCustomerIDThroguhGlobalSearch")
    public void searchingAccountCustomerIDThroughListView(){
        String custID = qsalesHomePage.getCustID(PropertyReader.readDataFromFile("LatestCustomerID"));
        System.out.println("Value of the CUstID ===> " + custID);
        accountHomePage = qsalesHomePage.clickingAccountsTab();
        accountHomePage.clearFilterConditionIfPresent();
        accountHomePage.searchingCustomerIDThroughListview("Customer ID",custID);
        accountDetailPage = accountHomePage.clickingAccountName();
        accountDetailPage.clickingDetailTab();
        String ActualratingFieldValue = accountDetailPage.getRatingFieldValue();
        System.out.println("Value of the ActualratingFieldValue====>"+ ActualratingFieldValue);

        String ActualTypeFieldValue = accountDetailPage.getTypeFieldValue();
        System.out.println("Value of the ActualTypeFieldValue====>"+ ActualTypeFieldValue);
        accountHomePage = accountDetailPage.navigateBackToAccountsHome();
        accountHomePage.clearningFilterConditionAfterCapturingTheData();
        Assert.assertEquals(ActualratingFieldValue,"Warm");
        Assert.assertEquals(ActualTypeFieldValue,"Customer - Direct");
    }

    @Test(description = "Fetching Customer Id through Reports and Opening Account Detail Page")
    public void openingAccountDetailPageThroughReports(){
        String custID = qsalesHomePage.getCustID("Cust-0080");
        System.out.println("Value of the CUstID ===> " + custID);
        reportsHomePage = qsalesHomePage.clickReportsTab();
        reportsHomePage.clickingNewReportsBtn();
        reportBuilderPage = reportsHomePage.selectingAllReportOption("Accounts");
        reportsDetailPage =
                reportBuilderPage.buildingReportByUsingCustomerID("Account Name","Customer ID","Customer ID",custID);
        accountDetailPage = reportsDetailPage.openingAccountDetailPageByClickingAccountLink();
        accountDetailPage.clickingDetailTabAfterOpeningAccountFromReport();
        //String ActualratingFieldValue = accountDetailPage.getRatingFieldValue();
        //System.out.println("Value of the ActualratingFieldValue====>"+ ActualratingFieldValue);
        //Assert.assertEquals(ActualratingFieldValue,"Warm");


    }

    @Test(description = "Deleting Recently Created Account by clicking Delete button",dependsOnMethods = "openingAccountDetailPageThroughReports")
    public void deletingRecentlyCreatedAccount(){
        accountDetailPage = qsalesHomePage.navigatingToAccountDetailPageByUsingUrl(PropertyReader.readDataFromFile("LatestAccountURL"));
        accountDetailPage.clickDeleteBtnFromMoreBtn();
        accountDetailPage.setClickingConfirmingDeleteBtn();
        String actualAccountHeader = accountHomePage.getAccountHeader();
        Assert.assertEquals(actualAccountHeader,"Accounts");



    }

}
