package com.qa.salesforce.tests;

import com.qa.salesforce.base.BaseTest;
import com.qa.salesforce.utils.ExcelUtil;
import com.qa.salesforce.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LeadSceanriosTest extends BaseTest {

    @BeforeClass
    public void loginSetup(){
        qsalesHomePage = loginPage.switchingSalesApp(prop.getProperty("username"), prop.getProperty("password"),"Sales");
        System.out.println("Value of the qsalesHomepage====> " + qsalesHomePage);
    }

    @DataProvider(name = "LeadCreationData")
    public Object[][] getLeadData(){
        Object[][] dataMap = null;
        try {
            dataMap = ExcelUtil.getTestData("Creating New Lead");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataMap;
    }

    @DataProvider(name = "LeadUpdateDataByClickingEditBtn")
    public Object[][] getLeadUpdateData(){
        Object[][] dataMap = null;
        try {
            dataMap = ExcelUtil.getTestData("EditingLeadByClickingEditBtn");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataMap;
    }

    @DataProvider(name = "LeadUpdateDataByClickingInlineBtn")
    public Object[][] getLeadInlineUpdateData(){
        Object[][] dataMap = null;
        try {
            dataMap = ExcelUtil.getTestData("EditingLeadByClickingInlineEditBtn");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataMap;
    }

    @Test(description = "Creating New Leads From Leads Tab",dataProvider = "LeadCreationData")
    public void creatingNewLeadFromTab(String fName,String lName,String company,String source,
                                       String industry,String phone,String rating,String desc){
    leadsHomePage = qsalesHomePage.setClickingLeadsTab();
    leadsPage = leadsHomePage.doClickingLeadCreationBtn();
    String ranFName = elementUtil.generateRandomString(5)+fName;
    String ranLName = elementUtil.generateRandomString(5)+lName;
    String ranCompany = elementUtil.generateRandomString(5)+company+elementUtil.generateRandomString(5);
    leadsDetailPage = leadsPage.creatingNewLead(ranFName,ranLName,ranCompany,source,industry,phone,rating,desc);
    leadsDetailPage.doClickDetailTab();

    String actualLeadSourceFieldValue = leadsDetailPage.getLeadSourceFieldValue();
    Assert.assertEquals(actualLeadSourceFieldValue,source);

    String actualIndustryFieldValue = leadsDetailPage.getIndustryFieldValue();
    Assert.assertEquals(actualIndustryFieldValue,industry);

    String actualRatingFieldValue = leadsDetailPage.getRatingFieldValue();
    Assert.assertEquals(actualRatingFieldValue,rating);

    String LeadID = leadsDetailPage.getLeadIDFieldValue();
    PropertyReader.writingDataIntoTextFile("LatestLeadURL",leadsDetailPage.getLeadCurrentURL());
    PropertyReader.writingDataIntoTextFile("LatestLeadID",LeadID);

    }

    @Test(description = "Editing Lead Detail Page By Clicking Edit button",dataProvider = "LeadUpdateDataByClickingEditBtn",dependsOnMethods = "creatingNewLeadFromTab")
    public void editingLeadDetailPageByClickingEditButton(String empNo,String aStreet,String aCity,String aState,String aPincode,String aCountry){
        String LeadURL = PropertyReader.readDataFromFile("LatestLeadURL");
        leadsDetailPage = qsalesHomePage.navigatingToLeadDetailPageByUsingUrl(LeadURL);
        leadsDetailPage.doClickDetailTab();
        leadsPage = leadsDetailPage.clickEditBtnFromMoreBtn();
        leadsDetailPage = leadsPage.editingLeadDetailPageByClickingEditBtn(empNo,aStreet,aCity,aState,aPincode,aCountry);
        leadsDetailPage.doClickDetailTab();
        String actualIndustryFieldValue = leadsDetailPage.getIndustryFieldValue();
        Assert.assertEquals(actualIndustryFieldValue,"Communications");

        String actualRatingFieldValue = leadsDetailPage.getRatingFieldValue();
        Assert.assertEquals(actualRatingFieldValue,"Warm");

    }

    @Test(description = "Editing Lead Detail Page through Inline edit",dataProvider = "LeadUpdateDataByClickingInlineBtn",dependsOnMethods = "editingLeadDetailPageByClickingEditButton")
    public void editingLeadDetailPageByInlineEditing(String hPhone,String website,String productInterest,String sicCode,
                                                     String Locations,String generator,String primary){
        String LeadURL = PropertyReader.readDataFromFile("LatestLeadURL");
        leadsDetailPage = qsalesHomePage.navigatingToLeadDetailPageByUsingUrl(LeadURL);
        leadsDetailPage.doClickDetailTab();
        leadsDetailPage.doClickInlineEditBtn();
        String ranSICCode = elementUtil.generateRandomString(5)+sicCode;
        leadsDetailPage.editingLeadDetailPageByClickingInlineEditProcess(hPhone,website,productInterest,ranSICCode,Locations,generator,primary);
        leadsDetailPage.doClickDetailTab();
        String actualRatingFieldValue = leadsDetailPage.getRatingFieldValue();
        Assert.assertEquals(actualRatingFieldValue,"Warm");

    }

    @Test(description = "Searching Lead Id globally and Open Lead Detail Page",dependsOnMethods = "editingLeadDetailPageByInlineEditing")
    public void searchingLeadIDGlobally() {
        String Lead_ID = PropertyReader.readDataFromFile("LatestLeadID");
        leadsDetailPage = qsalesHomePage.searchLeadIDGlobally(Lead_ID);
        leadsDetailPage.doClickDetailTab();
        String actualRatingFieldValue = leadsDetailPage.getRatingFieldValue();
        Assert.assertEquals(actualRatingFieldValue, "Warm");
    }

    @Test(description = "Opening Lead Detail Page through Reports",dependsOnMethods = "searchingLeadIDGlobally")
    public void OpeningLeadDetailPageThroughReports(){
        String Lead_ID = PropertyReader.readDataFromFile("LatestLeadID");
        leadsHomePage = qsalesHomePage.setClickingLeadsTab();
        String objectHeaderText = leadsHomePage.getLeadHeaderPage();
        System.out.println("Value of the objectHeaderText========>"+objectHeaderText);
        reportsHomePage = qsalesHomePage.clickReportsTab();
        reportsHomePage.clickingNewReportsBtn();
        reportBuilderPage = reportsHomePage.selectingAllReportOption("Leads");
        reportsDetailPage = reportBuilderPage.buildingReportObjectID(objectHeaderText,"Last Name","Company / Account",4,"Custom_Lead ID","Converted","Custom_Lead ID",Lead_ID,"Converted","All leads");
        leadsDetailPage = reportsDetailPage.openingLeadDetailPageByClickingLeadLink();
        leadsDetailPage.clickDetailTabBtnThroughReports();
        String actualRatingFieldValue = leadsDetailPage.getRatingFieldValue();
        Assert.assertEquals(actualRatingFieldValue,"Warm");


    }

    @Test(description = "Opening Lead Detail page Through List view")
    public void openingLeadDetailPageThroughListView(){
        String Lead_ID = PropertyReader.readDataFromFile("LatestLeadID");
        System.out.println("Value of the Lead Id ===> " + Lead_ID);
        leadsHomePage = qsalesHomePage.setClickingLeadsTab();
        leadsHomePage.checkingLeadIDFilterIsAvailableOrNot();
        leadsHomePage.searchingLeadIDThroughListview("Custom_Lead ID",Lead_ID);
        leadsDetailPage = leadsHomePage.clickingLeadDetailPageThroughListView();
        //leadsDetailPage.doClickDetailTab();
        //String actualRatingFieldValue = leadsDetailPage.getRatingFieldValue();
        //System.out.println("Value of the actualRatingFieldValue ===> " + actualRatingFieldValue);
        //leadsHomePage.clearningFilterConditionAfterCapturingThefieldValue();
        //Assert.assertEquals(actualRatingFieldValue,"Warm");
    }

    @Test(description = "Converting the recently created lead",dependsOnMethods = "openingLeadDetailPageThroughListView")
    public void convertingRecentlyCreatedLead(){
        String LeadURL = PropertyReader.readDataFromFile("LatestLeadURL");
        leadsDetailPage = qsalesHomePage.navigatingToLeadDetailPageByUsingUrl(LeadURL);
        leadsDetailPage.doClickDetailTab();
        leadsDetailPage.doClickMarkStatusAsCompleteBtn();
        String actualLeadStatus = leadsDetailPage.getLeadStatusFieldValue();
        Assert.assertEquals(actualLeadStatus, "Working - Contacted");

        leadsDetailPage.doClickMarkStatusAsCompleteBtn();
        //String actualLeadStatus1 = leadsDetailPage.getLeadStatusFieldValue();
        //Assert.assertEquals(actualLeadStatus1, "Closed - Not Converted");
        leadsDetailPage.doLeadConversionProcess();
        opportunitiesDetailPage = leadsDetailPage.doClickConvertedOpp();
        opportunitiesDetailPage.doClickDetailTab();
        String actualOpp_Stage = opportunitiesDetailPage.setFetchOpportunityStage();
        Assert.assertEquals(actualOpp_Stage, "Prospecting");
    }
}
