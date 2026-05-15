package com.qa.salesforce.tests;

import com.qa.salesforce.base.BaseTest;
import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.ExcelUtil;
import com.qa.salesforce.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ContactsScenarioTest extends BaseTest {

    @BeforeClass
    public void loginSetup(){
        qsalesHomePage = loginPage.switchingSalesApp(prop.getProperty("username"), prop.getProperty("password"),"Sales");
        System.out.println("Value of the qsalesHomepage====> " + qsalesHomePage);
    }

    @DataProvider(name = "ContactCreationData")
    public Object[][] getContactData(){
        Object[][] dataMap = null;
        try {
            dataMap = ExcelUtil.getTestData("Creating New Contact");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataMap;
    }

    @DataProvider(name = "EditingContactByClickingEditBtn")
    public Object[][] getContactDataForEditByClickingEditBtn(){
        Object[][] dataMap = null;
        try {
            dataMap = ExcelUtil.getTestData("EditingContactByClickingEditBtn");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataMap;
    }

    @DataProvider(name = "EditingContactByClickingInlineBtn")
    public Object[][] getContactDataForEditByClickingInlineBtn(){
        Object[][] dataMap = null;
        try {
            dataMap = ExcelUtil.getTestData("Editing Contact By Clicking Inline Btn");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataMap;
    }

    @Test(description = "Creating New Contact by clicking New button from Contact Tab",dataProvider = "ContactCreationData")
    public void creatingNewContactByClickingNewBtnOnContactTab(String fName,String lName,String department,String phone,String description,String leadSource){
        String custID = qsalesHomePage.getCustID(PropertyReader.readDataFromFile("LatestCustomerID"));
        System.out.println("Value of the CUstID ===> " + custID);
        contactsHomePage = qsalesHomePage.setClickingContactsTab();
        contacts = contactsHomePage.doClickingNewBtn();
        String ranfName = ElementUtil.generateRandomString(7)+fName;
        String ranlName = ElementUtil.generateRandomString(7)+lName;
        contactDetailPage = contacts.creatingContactThroughContactsTab(ranfName,ranlName,custID,department,phone,description,leadSource);
        contactDetailPage.clickDetailTabBtn();

        String actualLeadSourceFieldValue = contactDetailPage.getLeadSourceFieldValue();
        Assert.assertEquals(actualLeadSourceFieldValue, leadSource);

        String actualDepartmentFieldValue = contactDetailPage.getDepartmentFieldValue();
        Assert.assertEquals(actualDepartmentFieldValue, department);

        PropertyReader.writingDataIntoTextFile("LatestContactURL",contactDetailPage.getCurrentURL());
        PropertyReader.writingDataIntoTextFile("LatestContactID", contactDetailPage.getContactID());
    }
    @Test(description = "Creating Contact from Account Related List",dataProvider = "ContactCreationData",dependsOnMethods = "creatingNewContactByClickingNewBtnOnContactTab")
    public void creatingContactFromAccountRelatedList(String fName,String lName,String department,String phone,String description,String leadSource){
        accountDetailPage = qsalesHomePage.navigatingToAccountDetailPageByUsingUrl(PropertyReader.readDataFromFile("LatestAccountURL"));
        accountDetailPage.clickNewContctBtnFromAccountRelatedList();
        String ranfName = ElementUtil.generateRandomString(7)+fName;
        String ranlName = ElementUtil.generateRandomString(7)+lName;
        contactDetailPage = contacts.creatingContactThroughAccountRelatedList(ranfName,ranlName,department,phone,description,leadSource);
        contactsHomePage = accountDetailPage.clickingContactHeaderLinkFromAccountPage();
        contactDetailPage = contactsHomePage.clickingContactLinkFromContactHomePage();
        contactDetailPage.clickDetailTabBtnThroughReports();

        String actualLeadSourceFieldValue = contactDetailPage.getLeadSourceFieldValue();
        System.out.println("Value of the actualLeadSourceFieldValue====>"+actualLeadSourceFieldValue);
        Assert.assertEquals(actualLeadSourceFieldValue, leadSource);

        String actualDepartmentFieldValue = contactDetailPage.getDepartmentFieldValue();
        Assert.assertEquals(actualDepartmentFieldValue, department);

        PropertyReader.writingDataIntoTextFile("LatestContactURL1",contactDetailPage.getCurrentURL());
        PropertyReader.writingDataIntoTextFile("LatestContactID1", contactDetailPage.getContactID());
    }

    @Test(description = "searching contact id globally once record is find then open the record detail page",dependsOnMethods = "creatingNewContactByClickingNewBtnOnContactTab")
    public void searchingContactIDGloballyandOpeningContactDetailPage(){
        String contactID = qsalesHomePage.getContactID(PropertyReader.readDataFromFile("LatestContactID"));
        System.out.println("Value of the contactID ===> " + contactID);
        contactDetailPage = qsalesHomePage.searchContactIDGlobally(contactID);
        contactDetailPage.clickDetailTabBtn();
        String actualLeadSourceFieldValue = contactDetailPage.getLeadSourceFieldValue();
        Assert.assertEquals(actualLeadSourceFieldValue, "Phone Inquiry");
    }


    @Test(description = "Editing Contact Detail Page By Clicking Edit button",dataProvider = "EditingContactByClickingEditBtn",dependsOnMethods = "creatingNewContactByClickingNewBtnOnContactTab")
    public void editingContactDetailPageByUsingEditbutton(String mailAddress,String mailCity,String mailState,String mailCountry
            ,String mailPostalCode,String lang,String level){
        contactDetailPage = qsalesHomePage.navigatingToContactDetailPageByUsingUrl(PropertyReader.readDataFromFile("LatestContactURL"));
        contacts = contactDetailPage.clickEditBtnFromMoreBtn();
        contactDetailPage.clickDetailTabBtn();
        contactDetailPage = contacts.doClickingEditButtonOnContactDetailPage(mailAddress,mailCity,mailState,mailCountry,mailPostalCode,lang,level);
        contactDetailPage.clickDetailTabBtn();

        String actualLeveFieldValue = contactDetailPage.getLevelFieldValue();
        Assert.assertEquals(actualLeveFieldValue, level);
    }

    @Test(description = "Editing Contact Detail Page By using Inline Editing",dataProvider = "EditingContactByClickingInlineBtn",dependsOnMethods = "editingContactDetailPageByUsingEditbutton")
    public void editingContactDetailPageByUsingInlineEditButton(String homePhone,String OtherPhone,String assistName,String asstPhone){
         contactDetailPage = qsalesHomePage.navigatingToContactDetailPageByUsingUrl(PropertyReader.readDataFromFile("LatestContactURL"));
        contactDetailPage.clickDetailTabBtn();
        contactDetailPage.doClickInlineEditBtn();
        String ranAssis = ElementUtil.generateRandomString(6)+assistName;
        contactDetailPage.editingContactDetailPageByClickingInlineEditing(homePhone,OtherPhone,ranAssis,asstPhone);
        contactDetailPage.doClickInlineEditBtn();

        String actualHomePhoneNumber = contactDetailPage.getHomePhoneFieldValue();
        Assert.assertEquals(actualHomePhoneNumber, homePhone);


    }
/*
    @Test(description = "Opening Contact Detail page Through Reports",dependsOnMethods = "editingContactDetailPageByUsingInlineEditButton")
    public void clickingContactDetailPageThroughReport(){
        String contactID = qsalesHomePage.getContactID(PropertyReader.readDataFromFile("LatestContactID"));
        System.out.println("Value of the contactID ===> " + contactID);
        contactsHomePage = qsalesHomePage.setClickingContactsTab();
        String Object_Header = contactsHomePage.getContactHeader();
        System.out.println("Value of the Object_Header ===> " + Object_Header);
        reportsHomePage = qsalesHomePage.clickReportsTab();
        reportsHomePage.clickingNewReportsBtn();
        reportBuilderPage = reportsHomePage.selectingAllReportOption("Contacts & Accounts");
        reportsDetailPage =
                reportBuilderPage.buildingReportObjectID(Object_Header,"Last Name","Account Name",8,"Custom_Contact ID","Lead Source","Custom_Contact ID",contactID,"Lead Source","All accounts");
        contactDetailPage = reportsDetailPage.openingAccountDetailPageByClickingContactLink();
        contactDetailPage.clickDetailTabBtnThroughReports();

        String actualLeadSourceFieldValue = contactDetailPage.getLeadSourceFieldValue();
        Assert.assertEquals(actualLeadSourceFieldValue, "Phone Inquiry");

    }

 */



    @Test(description = "Opening Contact Detail Page Through List views",dependsOnMethods = "editingContactDetailPageByUsingInlineEditButton")
    public void openingContactDetailPageThroughListView(){
        String contactID = qsalesHomePage.getContactID(PropertyReader.readDataFromFile("LatestContactID"));
        System.out.println("Value of the contactID ===> " + contactID);
        contactsHomePage = qsalesHomePage.setClickingContactsTab();
        contactsHomePage.clearFilterConditionIfPresent();
        contactsHomePage.searchingContactIDThroughListview("Custom_Contact ID",contactID);
        contactDetailPage = contactsHomePage.clickingContactDetailPageThroughListView();
        contactDetailPage.clickDetailTabBtn();
        String actualLeadSourceFieldValue = contactDetailPage.getLeadSourceFieldValue();
        System.out.println("Value of the actualLeadSourceFieldValue====>"+actualLeadSourceFieldValue);
        contactsHomePage = contactDetailPage.navigateBackToAccountsHome();
        contactsHomePage.clearningFilterConditionAfterCapturingTheData();
        Assert.assertEquals(actualLeadSourceFieldValue, "Phone Inquiry");


    }

    @Test(description = "Deleting Recently Created Contact",dependsOnMethods = "openingContactDetailPageThroughListView")
    public void deletingRecentlyCreatedContact(){
        contactDetailPage = qsalesHomePage.navigatingToContactDetailPageByUsingUrl(PropertyReader.readDataFromFile("LatestContactURL1"));
        contactDetailPage.clickDetailTabBtn();
        contactDetailPage.clickDeleteBtnFromMoreBtn();
        contactsHomePage = contactDetailPage.setClickingConfirmingDeleteBtn();
        String actualContactHeader = contactsHomePage.getContactHeader();
        Assert.assertEquals(actualContactHeader,"Contacts");

    }



}
