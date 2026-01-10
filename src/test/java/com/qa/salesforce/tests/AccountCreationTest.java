package com.qa.salesforce.tests;

import com.qa.salesforce.base.BaseTest;
import com.qa.salesforce.pages.AccountHomePage;
import com.qa.salesforce.utils.ElementUtil;
import com.qa.salesforce.utils.ExcelUtil;
import com.qa.salesforce.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.*;

public class AccountCreationTest extends BaseTest {
    
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

        PropertyReader.writingDataIntoTextFile("AccountURL::::: = ",accountDetailPage.getCurrentURL());

    }

    @Test(description = "Editing Recently created Account record By clicking Edit button", dataProvider = "AccDataForEdit")
    public void editingAccountPageByClickingEditButton(String bStreet,
                                                       String bState,String bCity, String bCounty,String bPostalCode){

      accountDetailPage = qsalesHomePage.navigatingToAccountDetailPageByUsingUrl(PropertyReader.readDataFromFile());
      accounts = accountDetailPage.clickEditBtnFromMoreBtn();
      accountDetailPage = accounts.updateBillingAddressByClickingEditbutton(bStreet,bState,bCity,bCounty,bPostalCode);

    }
}
