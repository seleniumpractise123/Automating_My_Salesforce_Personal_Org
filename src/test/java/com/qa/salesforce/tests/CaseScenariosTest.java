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

    }
}
