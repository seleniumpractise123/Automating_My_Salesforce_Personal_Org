package com.qa.salesforce.tests;

import com.qa.salesforce.base.BaseTest;
import com.qa.salesforce.utils.ExcelUtil;
import com.qa.salesforce.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OpportunitiesScenariosTest extends BaseTest {

    @BeforeClass
    public void loginSetup(){
        qsalesHomePage = loginPage.switchingSalesApp(prop.getProperty("username"), prop.getProperty("password"),"Sales");
        System.out.println("Value of the qsalesHomepage====> " + qsalesHomePage);
    }

    @DataProvider(name = "OpportunityCreationData")
    public Object[][] getOpportunityData(){
        Object[][] dataMap = null;
        try {
            dataMap = ExcelUtil.getTestData("CreateNewOpportunity");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataMap;
    }

    @DataProvider(name = "OpportunityEditedData")
    public Object[][] getOpportunityEditedData(){
        Object[][] dataMap = null;
        try {
            dataMap = ExcelUtil.getTestData("EditingOpportunityByClickingEditBtn");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataMap;
    }

    @DataProvider(name = "AddingSingleProductsToCreatedOpp")
    public Object[][] getSingleProductData(){
        Object[][] dataMap = null;
        try {
            dataMap = ExcelUtil.getTestData("AddingSingleProductsToOpp");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataMap;
    }

    @DataProvider(name = "AddingMultipleProductsToRecentlyCreatedOpp")
    public Object[][] getMultipleProductData(){
        Object[][] dataMap = null;
        try {
            dataMap = ExcelUtil.getTestData("AddingMultipleProductsToOpp");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataMap;
    }

    @Test(description = "Creating New Opportunity From Tab",dataProvider = "OpportunityCreationData")
    public void createNewOpportunityFromTab(String oppName,String stage,String desc){
        String customerID = qsalesHomePage.getCustID(PropertyReader.readDataFromFile("LatestCustomerID"));
        System.out.println("Value of the customerID ===> " + customerID);
        opportunitiesHomePage = qsalesHomePage.clickingOpportunitiesTab();
        opportunitiesPage = opportunitiesHomePage.setClickingNewBtn_Loc();
        String randomOppName = elementUtil.generateRandomString(5)+oppName;
        opportunitiesDetailPage = opportunitiesPage.createNewOpportunity(randomOppName,customerID,stage,desc);
        opportunitiesDetailPage.doClickDetailTab();
        String ActualoppStageValue = opportunitiesDetailPage.setFetchOpportunityStage();
        Assert.assertEquals(ActualoppStageValue, stage,"Stage Matched Successfully");
        String OppCustomId = opportunitiesDetailPage.fetchOpportunityID();
        System.out.println("Value of the OppCustomId=====>"+OppCustomId);
        PropertyReader.writingDataIntoTextFile("LatestOpportunityURLFromOpportunityTab",opportunitiesDetailPage.getOpportunityCurrentURL());
        PropertyReader.writingDataIntoTextFile("LatestOpportunityIDFromOpportunityTab",OppCustomId);
    }

    @Test(description = "Creating New Opportunity From Account Related List",dataProvider = "OpportunityCreationData",dependsOnMethods = "createNewOpportunityFromTab")
    public void createNewOpportunityFromAccountRelatedList(String oppName,String stage,String desc){
        accountDetailPage = qsalesHomePage.navigatingToAccountDetailPageByUsingUrl(PropertyReader.readDataFromFile("LatestAccountURL"));
        accountDetailPage.setClickingNewBtnFromAccountRelatedList();
        String randomOppName = elementUtil.generateRandomString(5)+oppName;
        accountDetailPage = opportunitiesPage.createNewOpportunityFromAccountRelatedList(randomOppName,stage,desc);
        accountDetailPage.setClickingOpportunitiesHeaderLinkFromAccountRelatedList();
        opportunitiesDetailPage = opportunitiesHomePage.setClickingNewlyCreatedOpportunityLinkFromAccountRelatedList();
        opportunitiesDetailPage.doClickDetailTab();
        String ActualoppStageValue = opportunitiesDetailPage.setFetchOpportunityStage();
        Assert.assertEquals(ActualoppStageValue, stage,"Stage Matched Successfully");
        String OppCustomId = opportunitiesDetailPage.fetchOpportunityID();
        System.out.println("Value of the OppCustomId=====>"+OppCustomId);
        PropertyReader.writingDataIntoTextFile("LatestOpportunityURLFromAccountRelatedList",opportunitiesDetailPage.getOpportunityCurrentURL());
        PropertyReader.writingDataIntoTextFile("LatestOpportunityIDFromAccountRelatedList",OppCustomId);


    }

    @Test(description = "Creating New Opportunity From Contact Related List",dataProvider = "OpportunityCreationData",dependsOnMethods = "createNewOpportunityFromAccountRelatedList")
    public void createNewOpportunityFromContactRelatedList(String oppName,String stage,String desc){
        contactDetailPage = qsalesHomePage.navigatingToContactDetailPageByUsingUrl(PropertyReader.readDataFromFile("LatestContactURL"));
        contactDetailPage.setClickingCreateNewOpportunityBtnfromContactRelatedList();
        String randomOppName = elementUtil.generateRandomString(5)+oppName;
        contactDetailPage = opportunitiesPage.createNewOpportunityFromContactRelatedList(randomOppName,stage,desc);
        opportunitiesHomePage = contactDetailPage.setClickingOpportunitiesHeader();
        opportunitiesDetailPage = opportunitiesHomePage.setClickingNewlyCreatedOpportunityLinkFromAccountRelatedList();
        opportunitiesDetailPage.doClickDetailTab();
        String ActualoppStageValue = opportunitiesDetailPage.setFetchOpportunityStage();
        Assert.assertEquals(ActualoppStageValue, stage,"Stage Matched Successfully");
        String OppCustomId1 = opportunitiesDetailPage.fetchOpportunityID();
        System.out.println("Value of the OppCustomId=====>"+OppCustomId1);
        PropertyReader.writingDataIntoTextFile("LatestOpportunityURLFromContactRelatedList",opportunitiesDetailPage.getOpportunityCurrentURL());
        PropertyReader.writingDataIntoTextFile("LatestOpportunityIDFromContactRelatedList",OppCustomId1);

    }

    @Test(description = "Editing Recently created Opportunity by clicking edit button",dataProvider = "OpportunityEditedData",dependsOnMethods ="createNewOpportunityFromContactRelatedList")
    public void editingRecentlyCreateOpportunityByUsingEditButton(String type,String source){
        String OppUrl = PropertyReader.readDataFromFile("LatestOpportunityURLFromAccountRelatedList");
        System.out.println("Value of the Latest Opportunity URL===>"+OppUrl);
        opportunitiesDetailPage = qsalesHomePage.navigatingToOpportunityDetailPageByUsingUrl(OppUrl);
        opportunitiesDetailPage.doClickDetailTab();
        opportunitiesPage = opportunitiesDetailPage.clickEditBtnFromMoreBtn("Edit");
        opportunitiesDetailPage = opportunitiesPage.EditingRecentlyCreatedOpportunityByUsingEditButton(type,source);
        opportunitiesDetailPage.doClickDetailTab();
        String actualTypePicklistValue = opportunitiesDetailPage.setGetTypePicklistFieldValue();
        Assert.assertEquals(actualTypePicklistValue, type,"Both Actual and Expected values are matched");

        String actualLeadSourcePicklistValue = opportunitiesDetailPage.setGetLeadSourcePicklistFieldValue();
        Assert.assertEquals(actualLeadSourcePicklistValue, source,"Both Actual and Expected values are matched");
    }

    @Test(description = "Adding Single Products to recently created Opportunity",dataProvider = "AddingSingleProductsToCreatedOpp",dependsOnMethods = "editingRecentlyCreateOpportunityByUsingEditButton")
    public void AddingSingleProductsToRecentlyCreatedOpportunity(String pName1,String qty1,String productCode){
        String pName =  pName1;
        String OppUrl = PropertyReader.readDataFromFile("LatestOpportunityURLFromAccountRelatedList");
        System.out.println("Value of the Latest Opportunity URL===>"+OppUrl);
        opportunitiesDetailPage = qsalesHomePage.navigatingToOpportunityDetailPageByUsingUrl(OppUrl);
        opportunitiesDetailPage.setSetClickingOpportunityRelatedListTab();
        productsHomePage = opportunitiesDetailPage.setClickingAddProductBtnOnOpportunityRelatedList();
        opportunitiesDetailPage = productsHomePage.addingSingleProductsToRecentlyCreateOPP(pName1,qty1);
        opportunitiesDetailPage.setSetClickingOpportunityRelatedListTab();
        productDetailPage = opportunitiesDetailPage.clickingRecentlyCreatedProductRecord(pName);
        String actualProductCode = productDetailPage.setFetchProductCode();
        System.out.println("Value of the actualProductCode=============>"+actualProductCode);
        Assert.assertEquals(actualProductCode, productCode);
    }

    /*
    @Test(description = "Adding Multiple Products to Recently Created Opportunity",dataProvider = "AddingMultipleProductsToRecentlyCreatedOpp")
    public void addingMultipleProductsToRecentlyCreatedOpportunity(String productName, String pQty){
        String OppUrl = PropertyReader.readDataFromFile("LatestOpportunityURLFromContactRelatedList");
        System.out.println("Value of the Latest Opportunity URL===>"+OppUrl);
        opportunitiesDetailPage = qsalesHomePage.navigatingToOpportunityDetailPageByUsingUrl(OppUrl);
        opportunitiesDetailPage.setSetClickingOpportunityRelatedListTab();
        productsHomePage = opportunitiesDetailPage.setClickingAddProductBtnOnOpportunityRelatedList();
        productsHomePage.addingMultipleProductsToRecentlyCreateOPP(productName,pQty);


    }

     */

    @Test(description = "Updating Opportunity Stage field values and checking stage is updating correctly",dependsOnMethods = "AddingSingleProductsToRecentlyCreatedOpportunity")
    public void updatingStageFieldValue(){
        String OpportunityStage = null;
        String actualOpportunityStage = null;
        String actualOpportunityClosedStage = null;
        String OppUrl = PropertyReader.readDataFromFile("LatestOpportunityURLFromAccountRelatedList");
        System.out.println("Value of the Latest Opportunity URL===>"+OppUrl);
        opportunitiesDetailPage = qsalesHomePage.navigatingToOpportunityDetailPageByUsingUrl(OppUrl);
        opportunitiesDetailPage.doClickDetailTab();
        OpportunityStage = opportunitiesDetailPage.setFetchOpportunityStagefieldValue();
        System.out.println("Value of the OpportunityStage==========>"+OpportunityStage);
        opportunitiesDetailPage.doClickMarkStatusAsCompleteBtn();
        actualOpportunityStage = opportunitiesDetailPage.setFetchOpportunityStagefieldValue();
        System.out.println("Value of the actualOpportunityStage==========>"+actualOpportunityStage);
        Assert.assertEquals(actualOpportunityStage, "Qualification","Opportunity stage is changed from "+OpportunityStage+" to "+actualOpportunityStage+"");

        //Changing stage from Qualification to Needs Analysis

        OpportunityStage = opportunitiesDetailPage.setFetchOpportunityStagefieldValue();
        System.out.println("Value of the OpportunityStage==========>"+OpportunityStage);
        opportunitiesDetailPage.doClickMarkStatusAsCompleteBtn();
        actualOpportunityStage = opportunitiesDetailPage.setFetchOpportunityStagefieldValue();
        System.out.println("Value of the actualOpportunityStage==========>"+actualOpportunityStage);
        Assert.assertEquals(actualOpportunityStage, "Needs Analysis","Opportunity stage is changed from "+OpportunityStage+" to "+actualOpportunityStage+"");

        //Changing stage from Needs Analysis to Value Proposition

        OpportunityStage = opportunitiesDetailPage.setFetchOpportunityStagefieldValue();
        System.out.println("Value of the OpportunityStage==========>"+OpportunityStage);
        opportunitiesDetailPage.doClickMarkStatusAsCompleteBtn();
        actualOpportunityStage = opportunitiesDetailPage.setFetchOpportunityStagefieldValue();
        System.out.println("Value of the actualOpportunityStage==========>"+actualOpportunityStage);
        Assert.assertEquals(actualOpportunityStage, "Value Proposition","Opportunity stage is changed from "+OpportunityStage+" to "+actualOpportunityStage+"");

        //Changing stage from Value Proposition to Id. Decision Makers

        OpportunityStage = opportunitiesDetailPage.setFetchOpportunityStagefieldValue();
        System.out.println("Value of the OpportunityStage==========>"+OpportunityStage);
        opportunitiesDetailPage.doClickMarkStatusAsCompleteBtn();
        actualOpportunityStage = opportunitiesDetailPage.setFetchOpportunityStagefieldValue();
        System.out.println("Value of the actualOpportunityStage==========>"+actualOpportunityStage);
        Assert.assertEquals(actualOpportunityStage, "Id. Decision Makers","Opportunity stage is changed from "+OpportunityStage+" to "+actualOpportunityStage+"");

        //Changing stage from Id. Decision Makers to Perception Analysis

        OpportunityStage = opportunitiesDetailPage.setFetchOpportunityStagefieldValue();
        System.out.println("Value of the OpportunityStage==========>"+OpportunityStage);
        opportunitiesDetailPage.doClickMarkStatusAsCompleteBtn();
        actualOpportunityStage = opportunitiesDetailPage.setFetchOpportunityStagefieldValue();
        System.out.println("Value of the actualOpportunityStage==========>"+actualOpportunityStage);
        Assert.assertEquals(actualOpportunityStage, "Perception Analysis","Opportunity stage is changed from "+OpportunityStage+" to "+actualOpportunityStage+"");

        //Changing stage from Perception Analysis to Proposal/Price Quote

        OpportunityStage = opportunitiesDetailPage.setFetchOpportunityStagefieldValue();
        System.out.println("Value of the OpportunityStage==========>"+OpportunityStage);
        opportunitiesDetailPage.doClickMarkStatusAsCompleteBtn();
        actualOpportunityStage = opportunitiesDetailPage.setFetchOpportunityStagefieldValue();
        System.out.println("Value of the actualOpportunityStage==========>"+actualOpportunityStage);
        Assert.assertEquals(actualOpportunityStage, "Proposal/Price Quote","Opportunity stage is changed from "+OpportunityStage+" to "+actualOpportunityStage+"");

        //Changing stage from Proposal/Price Quote to Negotiation/Review

        OpportunityStage = opportunitiesDetailPage.setFetchOpportunityStagefieldValue();
        System.out.println("Value of the OpportunityStage==========>"+OpportunityStage);
        opportunitiesDetailPage.doClickMarkStatusAsCompleteBtn();
        actualOpportunityStage = opportunitiesDetailPage.setFetchOpportunityStagefieldValue();
        System.out.println("Value of the actualOpportunityStage==========>"+actualOpportunityStage);
        Assert.assertEquals(actualOpportunityStage, "Negotiation/Review","Opportunity stage is changed from "+OpportunityStage+" to "+actualOpportunityStage+"");

        //Changing stage from Negotiation/Review Quote to Closed Won

        OpportunityStage = opportunitiesDetailPage.setFetchOpportunityStagefieldValue();
        System.out.println("Value of the OpportunityStage==========>"+OpportunityStage);
        opportunitiesDetailPage.doClickMarkStatusAsCompleteBtn();
        actualOpportunityClosedStage = opportunitiesDetailPage.changingTheStageToClosedWon("Closed Won");
        System.out.println("Value of the actualOpportunityClosedStage==========>"+actualOpportunityClosedStage);
        Assert.assertEquals(actualOpportunityClosedStage, "Closed Won","Opportunity stage is changed from "+OpportunityStage+" to "+actualOpportunityClosedStage+"");
    }

    @Test(description = "Searching Recently Created Opportunity ID Globally",dependsOnMethods = "updatingStageFieldValue")
    public void searchingOpportunityIDGlobally(){
        String Opportunity_ID = PropertyReader.readDataFromFile("LatestOpportunityIDFromOpportunityTab");
        opportunitiesDetailPage = qsalesHomePage.searchOpportunityIDGlobally(Opportunity_ID);
        opportunitiesDetailPage.doClickDetailTab();
        String ActualOpportunityStage = opportunitiesDetailPage.setFetchOpportunityStagefieldValue();
        System.out.println("Value of the ActualOpportunityStage==========>"+ActualOpportunityStage);
        Assert.assertEquals(ActualOpportunityStage, "Prospecting");
    }

    @Test(description = "Opening Recently Created Opportunity through Report",dependsOnMethods = "searchingOpportunityIDGlobally")
    public void openingRecentlyOpportunityFromReports(){
        String Opportunity_ID = PropertyReader.readDataFromFile("LatestOpportunityIDFromOpportunityTab");
        System.out.println("Value of the Opportunity_ID========>"+Opportunity_ID);
        opportunitiesHomePage = qsalesHomePage.clickingOpportunitiesTab();
        String objectHeader = opportunitiesHomePage.fetchObjecHeader();;
        System.out.println("Value of the objectHeader========>"+objectHeader);
        reportsHomePage = qsalesHomePage.clickReportsTab();
        reportsHomePage.clickingNewReportsBtn();
        reportBuilderPage = reportsHomePage.selectingAllReportOption("Opportunities");
        reportsDetailPage =
                reportBuilderPage.buildingReportObjectID(objectHeader,"Account Name","Opportunity Name",9,"Custom_Opportunity ID","test","Custom_Opportunity ID",Opportunity_ID,"test","All Opportunities");
        opportunitiesDetailPage = reportsDetailPage.openingOpportunityDetailPageByClickingLeadLink();
        opportunitiesDetailPage.doClickDetailTab();
        String ActualOpportunityStage = opportunitiesDetailPage.setFetchOpportunityStagefieldValue();
        System.out.println("Value of the ActualOpportunityStage==========>"+ActualOpportunityStage);
        Assert.assertEquals(ActualOpportunityStage, "Prospecting");
    }

    @Test(description = "Opening Opportunity Detail Page Through List view",dependsOnMethods = "openingRecentlyOpportunityFromReports")
    public void openingOpportunityDetailPageThroughListView(){
        String Opportunity_ID = PropertyReader.readDataFromFile("LatestOpportunityIDFromOpportunityTab");
        System.out.println("Value of the Opportunity_ID========>"+Opportunity_ID);
        opportunitiesHomePage = qsalesHomePage.clickingOpportunitiesTab();
        opportunitiesHomePage.clearFilterConditionIfPresent();
        opportunitiesHomePage.searchingContactIDThroughListview("Custom_Opportunity ID",Opportunity_ID);
        opportunitiesDetailPage = opportunitiesHomePage.clickingOpportunityDetailPageThroughListView();
        opportunitiesDetailPage.doClickDetailTab();
        String ActualOpportunityStage = opportunitiesDetailPage.setFetchOpportunityStagefieldValue();
        System.out.println("Value of the ActualOpportunityStage==========>"+ActualOpportunityStage);
        opportunitiesHomePage = opportunitiesDetailPage.navigateToOpportunitiesHomePage();
        opportunitiesHomePage.clearningFilterConditionAfterCapturingTheData();
        Assert.assertEquals(ActualOpportunityStage, "Prospecting");
    }

    @Test(description = "Deleting Recently created Opportunity",dependsOnMethods = "openingOpportunityDetailPageThroughListView")
    public void deletingRecentlyCreatedOpportunity(){
        String OppUrl = PropertyReader.readDataFromFile("LatestOpportunityURLFromAccountRelatedList");
        System.out.println("Value of the Latest Opportunity URL===>"+OppUrl);
        opportunitiesDetailPage = qsalesHomePage.navigatingToOpportunityDetailPageByUsingUrl(OppUrl);
        opportunitiesPage = opportunitiesDetailPage.clickEditBtnFromMoreBtn("Delete");
        opportunitiesHomePage = opportunitiesDetailPage.clickingConfirmBtn();
        String actualObjectHeader = opportunitiesHomePage.fetchObjecHeader();;
        System.out.println("Value of the objectHeader========>"+actualObjectHeader);
        Assert.assertEquals(actualObjectHeader, "Opportunities");


    }
}
