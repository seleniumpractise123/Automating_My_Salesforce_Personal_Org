package com.qa.salesforce.tests;

import com.qa.salesforce.base.BaseTest;
import com.qa.salesforce.utils.ExcelUtil;
import com.qa.salesforce.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CaseScenariosTest extends BaseTest {

    @BeforeClass
    public void loginSetup(){
        qsalesHomePage = loginPage.switchingSalesApp(prop.getProperty("username"), prop.getProperty("password"),"Sales");
        System.out.println("Value of the qsalesHomepage====> " + qsalesHomePage);
    }

    @DataProvider(name = "CaseCreationData")
    public Object[][] getCaseData(){
        Object[][] dataMap = null;
        try {
            dataMap = ExcelUtil.getTestData("newCaseCreationTest");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataMap;
    }

    @DataProvider(name = "CaseUpdationDataByUsingEditBtn")
    public Object[][] getCaseEditData(){
        Object[][] dataMap = null;
        try {
            dataMap = ExcelUtil.getTestData("EditingNewlyCreatedCaseByUsingEditBtn");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataMap;
    }

    @DataProvider(name = "CaseUpdationDataByUsingInlineEdit")
    public Object[][] getCaseInlineData(){
        Object[][] dataMap = null;
        try {
            dataMap = ExcelUtil.getTestData("EditingNewlyCreatedCaseByUsingInlineEdit");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataMap;
    }

    @Test(description = "Creating New Case From Case Tab",dataProvider = "CaseCreationData")
    public void newCaseCreationFromCasesTab(String typePickValue,String reasonPickValue,
                                            String originPickValue,String subj,String desc){
        String custID = qsalesHomePage.getCustID(PropertyReader.readDataFromFile("LatestCustomerID"));
        System.out.println("Value of the CUstID ===> " + custID);

        String contactID = qsalesHomePage.getContactID(PropertyReader.readDataFromFile("LatestContactID"));
        System.out.println("Value of the contactID ===> " + contactID);
        casesHomePage = qsalesHomePage.setClickingCasesTab();
        cases = casesHomePage.doClickingNewCaseCreationBtn();
        casesDetailPage = cases.doCreateNewCase(contactID,custID,typePickValue,reasonPickValue,originPickValue,subj,desc);
        String actualCaseStatus = casesDetailPage.fetchCaseStatusFieldValue();
        System.out.println("Value of the actualCaseStatus============>"+actualCaseStatus);
        Assert.assertEquals(actualCaseStatus, "New");
        PropertyReader.writingDataIntoTextFile("LatestCaseURLFromCasesTab",casesDetailPage.getCaseCurrentURL());
        PropertyReader.writingDataIntoTextFile("LatestCaseIDFromCasesTab",casesDetailPage.setFetchCustomCaseID());
    }

    @Test(description = "Creating Case from Account Related List",dataProvider = "CaseCreationData",dependsOnMethods = "newCaseCreationFromCasesTab")
    public void creatingCaseFromAccountRelatedList(String typePickValue,String reasonPickValue,
                                                   String originPickValue,String subj,String desc){
        String contactID = qsalesHomePage.getContactID(PropertyReader.readDataFromFile("LatestContactID"));
        System.out.println("Value of the contactID ===> " + contactID);
        accountDetailPage = qsalesHomePage.navigatingToAccountDetailPageByUsingUrl(PropertyReader.readDataFromFile("LatestAccountURL"));
        cases = accountDetailPage.doClickingNewCaseCreationBtn();
        accountDetailPage = cases.doCreateNewCaseFromAccountRelatedList(contactID,typePickValue,reasonPickValue,originPickValue,subj,desc);
        casesHomePage = accountDetailPage.doClickingCasesHeaderFromAccountRelatedList();
        casesDetailPage = casesHomePage.doClickRecentlyCase();
        String actualCaseStatus = casesDetailPage.fetchCaseStatusFieldValue();
        System.out.println("Value of the actualCaseStatus============>"+actualCaseStatus);
        Assert.assertEquals(actualCaseStatus, "New");
        PropertyReader.writingDataIntoTextFile("LatestCaseURLFromAccountRelatedList",casesDetailPage.getCaseCurrentURL());
        PropertyReader.writingDataIntoTextFile("LatestCaseIDFromAccountRelatedList",casesDetailPage.setFetchCustomCaseID());
    }

    @Test(description = "Creating New Case From Contact Related List",dataProvider = "CaseCreationData",dependsOnMethods = "creatingCaseFromAccountRelatedList")
    public void creatingCaseFromContactRelatedList(String typePickValue,String reasonPickValue,
                                                   String originPickValue,String subj,String desc){
        contactDetailPage = qsalesHomePage.navigatingToContactDetailPageByUsingUrl(PropertyReader.readDataFromFile("LatestContactURL"));
        cases = contactDetailPage.doClickingNewCaseCreationBtn();
        contactDetailPage = cases.doCreateNewCaseFromContactRelatedList(typePickValue,reasonPickValue,originPickValue,subj,desc);
        casesHomePage = contactDetailPage.doClickingCasesHeaderFromContactRelatedList();
        casesDetailPage = casesHomePage.doClickRecentlyCase();
        String actualCaseStatus = casesDetailPage.fetchCaseStatusFieldValue();
        System.out.println("Value of the actualCaseStatus============>"+actualCaseStatus);
        Assert.assertEquals(actualCaseStatus, "New");
        PropertyReader.writingDataIntoTextFile("LatestCaseURLFromContactRelatedList",casesDetailPage.getCaseCurrentURL());
        PropertyReader.writingDataIntoTextFile("LatestCaseIDFromContactRelatedList",casesDetailPage.setFetchCustomCaseID());
    }

    @Test(description = "Editing Recently Created By Using Edit button",dataProvider = "CaseUpdationDataByUsingEditBtn",dependsOnMethods = "creatingCaseFromContactRelatedList")
    public void editingRecentlyCreatedCaseByClickingEditBtn(String email,String name,String company,String phone,String pName){
        casesDetailPage = qsalesHomePage.navigatingToCaseDetailPageByUsingUrl(PropertyReader.readDataFromFile("LatestCaseURLFromCasesTab"));
        cases = casesDetailPage.doClickEditBtnFromCaseDetailPage();
        casesDetailPage = cases.editingRecentlyCreatedCaseByUsingEditBtn(email,name,company,phone,pName);
        String actualProduct = casesDetailPage.fetchProductFieldValue();
        System.out.println("Value of the actualProduct============>"+actualProduct);
        Assert.assertEquals(actualProduct, pName);
    }

    @Test(description = "Editing Recently Case by Using Inline Edit process",dataProvider = "CaseUpdationDataByUsingInlineEdit",dependsOnMethods = "editingRecentlyCreatedCaseByClickingEditBtn")
    public void editingRecentlyCreatedCaseByUsingInlineEdit(String pickValue,String engReqValue,String SLApickValue,String comments){
        casesDetailPage = qsalesHomePage.navigatingToCaseDetailPageByUsingUrl(PropertyReader.readDataFromFile("LatestCaseURLFromCasesTab"));
        casesDetailPage.doClickInlineEditBtn();
        casesDetailPage.editingRecentlyCaseByUsingInlineEdiProcess(pickValue,engReqValue,SLApickValue,comments);
        String actualCaseStatus = casesDetailPage.fetchCaseStatusFieldValue();
        System.out.println("Value of the actualCaseStatus============>"+actualCaseStatus);
        Assert.assertEquals(actualCaseStatus, "New");

    }

    @Test(description = "Searching Recently Created Case ID Globally",dependsOnMethods = "editingRecentlyCreatedCaseByUsingInlineEdit")
    public void searchingCaseIDGlobally(){
        String Case_ID = PropertyReader.readDataFromFile("LatestCaseIDFromAccountRelatedList");
        casesDetailPage = qsalesHomePage.searchCaseIDGlobally(Case_ID);
        String actualCaseStatus = casesDetailPage.fetchCaseStatusFieldValue();
        System.out.println("Value of the actualCaseStatus============>"+actualCaseStatus);
        Assert.assertEquals(actualCaseStatus, "New");
    }
/*
    @Test(description = "Opening Case Detail Page Through List view",dependsOnMethods = "searchingCaseIDGlobally")
    public void openingCaseDetailPageThroughListView(){
        String Case_ID = PropertyReader.readDataFromFile("LatestCaseIDFromAccountRelatedList");
        System.out.println("Value of the Case_ID========>"+Case_ID);
        casesHomePage = qsalesHomePage.setClickingCasesTab();
        casesHomePage.clearFilterConditionIfPresent();
        casesHomePage.searchingContactIDThroughListview("Custom Case ID",Case_ID);
        casesDetailPage = casesHomePage.clickingCasePageThroughListView();
        casesHomePage.clearningFilterConditionAfterCapturingTheData();
        String actualCaseStatus = casesDetailPage.fetchCaseStatusFieldValue();
        System.out.println("Value of the actualCaseStatus============>"+actualCaseStatus);
        Assert.assertEquals(actualCaseStatus, "New");
    }

    @Test(description = "Opening Recently Created Case through Report",dependsOnMethods = "openingCaseDetailPageThroughListView")
    public void openingRecentlyCaseFromReports(){
        String Case_ID = PropertyReader.readDataFromFile("LatestCaseIDFromCasesTa");
        System.out.println("Value of the Case_ID========>"+Case_ID);
        casesHomePage = qsalesHomePage.setClickingCasesTab();
        String objectHeader = casesHomePage.fetchObjecHeader();
        System.out.println("Value of the objectHeader========>"+objectHeader);
        reportsHomePage = qsalesHomePage.clickReportsTab();
        reportsHomePage.clickingNewReportsBtn();
        reportBuilderPage = reportsHomePage.selectingAllReportOption("Cases");
        reportsDetailPage =
                reportBuilderPage.buildingReportObjectID(objectHeader,"Account Name","Subject",4,"Case Number","Custom Case ID","Custom Case ID",Case_ID,"test","All cases");
        casesDetailPage = reportsDetailPage.openingCaseDetailPageByClickingLeadLink();
        String actualCaseProduct = casesDetailPage.fetchCaseProductFieldValue();
        System.out.println("Value of the actualCaseProduct============>"+actualCaseProduct);
        Assert.assertEquals(actualCaseProduct, "GC1040");
    }

 */
    @Test(description = "Updating the Status through Chevorn",dependsOnMethods = "searchingCaseIDGlobally")
    public void updatingTheCaseStatusThroughChevorn(){
        String beforeChangingTheCaseStatus = null;
        String afterChangingTheCaseStatus = null;
        casesDetailPage = qsalesHomePage.navigatingToCaseDetailPageByUsingUrl(PropertyReader.readDataFromFile("LatestCaseURLFromAccountRelatedList"));
        beforeChangingTheCaseStatus = casesDetailPage.fetchCaseStatusFieldValue();
        System.out.println("Value of the beforeChangingTheCaseStatus============>"+beforeChangingTheCaseStatus);
        casesDetailPage.doClickMarkStatusAsCompleteBtn();
        afterChangingTheCaseStatus = casesDetailPage.fetchCaseStatusFieldValue();
        System.out.println("Value of the beforeChangingTheCaseStatus============>"+beforeChangingTheCaseStatus);
        Assert.assertEquals(afterChangingTheCaseStatus, "Working","Case status is changed from "+beforeChangingTheCaseStatus+" to "+afterChangingTheCaseStatus+"");

        beforeChangingTheCaseStatus = casesDetailPage.fetchCaseStatusFieldValue();
        System.out.println("Value of the beforeChangingTheCaseStatus============>"+beforeChangingTheCaseStatus);
        casesDetailPage.doClickMarkStatusAsCompleteBtn();
        afterChangingTheCaseStatus = casesDetailPage.fetchCaseStatusFieldValue();
        System.out.println("Value of the beforeChangingTheCaseStatus============>"+beforeChangingTheCaseStatus);
        Assert.assertEquals(afterChangingTheCaseStatus, "Escalated","Case status is changed from "+beforeChangingTheCaseStatus+" to "+afterChangingTheCaseStatus+"");

        beforeChangingTheCaseStatus = casesDetailPage.fetchCaseStatusFieldValue();
        System.out.println("Value of the beforeChangingTheCaseStatus============>"+beforeChangingTheCaseStatus);
        casesDetailPage.doClickMarkStatusAsCompleteBtn();
        afterChangingTheCaseStatus = casesDetailPage.fetchCaseStatusFieldValue();
        System.out.println("Value of the beforeChangingTheCaseStatus============>"+beforeChangingTheCaseStatus);
        Assert.assertEquals(afterChangingTheCaseStatus, "Closed","Case status is changed from "+beforeChangingTheCaseStatus+" to "+afterChangingTheCaseStatus+"");


    }
    @Test(description = "Deleting Recently Created Case",dependsOnMethods = "updatingTheCaseStatusThroughChevorn")
    public void deleteRecentlyCreatedCase(){
        casesDetailPage = qsalesHomePage.navigatingToCaseDetailPageByUsingUrl(PropertyReader.readDataFromFile("LatestCaseURLFromAccountRelatedList"));
        casesDetailPage.doClickDeleteBtn();
        casesHomePage = casesDetailPage.doClickConfirmDeleteBtn();
        casesHomePage = qsalesHomePage.setClickingCasesTab();
        String actualObjectHeader = casesHomePage.fetchObjecHeader();;
        System.out.println("Value of the objectHeader========>"+actualObjectHeader);
        Assert.assertEquals(actualObjectHeader, "Cases");

    }
}
