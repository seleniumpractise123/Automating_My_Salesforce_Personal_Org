package com.qa.salesforce.tests;

import com.qa.salesforce.base.BaseTest;
import com.qa.salesforce.utils.ExcelUtil;
import org.openqa.selenium.edge.AddHasCasting;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SalesE2EProcess extends BaseTest {

    @BeforeClass
    public void loginSetup(){
        qsalesHomePage = loginPage.switchingSalesApp(prop.getProperty("username"), prop.getProperty("password"),"Sales");
        System.out.println("Value of the qsalesHomepage====> " + qsalesHomePage);
    }

    @DataProvider(name = "salesE2EProcessTestData")
    public Object[][] getCampaignData(){
        Object[][] dataMap = null;
        try {
            dataMap = ExcelUtil.getTestData("SalesE2EProcessTestData");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataMap;
    }

    @Test(description = "Creating Sales E2E process and Execution",dataProvider = "salesE2EProcessTestData")
    public void SalesE2EProcess(String campName,String Status,String Type,String expRevune,String budgetCost,String Description,
                                String fName,String lName,String company,String source,
                                String industry,String phone,String rating,String desc,
                                String pName1,String qty1,String productCode) throws InterruptedException {
        String OpportunityStage = null;
        String actualOpportunityStage = null;
        String actualOpportunityClosedStage = null;
        /******* Creating Campaign and storing it's name*********/
        campaignsHomePage = qsalesHomePage.clickingCampaignsTab();
        campaignsPage = campaignsHomePage.doClickNewBtnFromCampaign();
        String ranCampName = elementUtil.generateRandomString(5) + campName;
        campaignsDetailPage = campaignsPage.creatingCampaignRecord(ranCampName, Status, Type, expRevune, budgetCost, Description);
        campaignsDetailPage.doClickDetailTab();
        String ActualcampType = campaignsDetailPage.fetchCampaignTypeFieldValue();
        System.out.println("Value of the ActualcampType====>" + ActualcampType);

        String actualcampStatus = campaignsDetailPage.fetchCampaignStatusFieldValue();
        System.out.println("Value of the actualcampStatus====>" + actualcampStatus);
        Assert.assertEquals(actualcampStatus, Status);

        String CampName = campaignsDetailPage.fetchCampaignNameFieldValue();
        System.out.println("Value of the CampName====>" + CampName);
        Assert.assertEquals(ActualcampType, Type);
        String CampCustomID = campaignsDetailPage.fetchCampaignCustomIDFieldValue();
        System.out.println("Value of the CampCustomID====>" + CampCustomID);

        String campUrl = campaignsDetailPage.getCampaignCurrentURL();
        System.out.println("Value of the campUrl====>" + campUrl);

        Thread.sleep(6000);
        /******* Creating Campaign and storing it's name Ended Here*********/

        /******* Creating Lead and Convering Process Starts Here********/
        leadsHomePage = qsalesHomePage.setClickingLeadsTab();
        leadsPage = leadsHomePage.doClickingLeadCreationBtn();
        String ranFName = elementUtil.generateRandomString(5) + fName;
        String ranLName = elementUtil.generateRandomString(5) + lName;
        String ranCompany = elementUtil.generateRandomString(5) + company + elementUtil.generateRandomString(5);
        leadsDetailPage = leadsPage.creatingNewLead(ranFName, ranLName, ranCompany, source, industry, phone, rating, desc);
        leadsDetailPage.doClickDetailTab();

        String actualLeadSourceFieldValue = leadsDetailPage.getLeadSourceFieldValue();
        Assert.assertEquals(actualLeadSourceFieldValue, source);

        String actualIndustryFieldValue = leadsDetailPage.getIndustryFieldValue();
        Assert.assertEquals(actualIndustryFieldValue, industry);

        String actualRatingFieldValue = leadsDetailPage.getRatingFieldValue();
        Assert.assertEquals(actualRatingFieldValue, rating);

        String LeadID = leadsDetailPage.getLeadIDFieldValue();
        String LeadURL = leadsDetailPage.getLeadCurrentURL();
        System.out.println("Value of the LeadURL=========>"+LeadURL);
        leadsDetailPage.addRecentlyCreatedCampaignToLead();
        leadsDetailPage.setCampaignFieldValue(CampName);
        leadsDetailPage.respondingToCampaign();
        leadsDetailPage.doClickMarkStatusAsCompleteBtn();
        String actualLeadStatus = leadsDetailPage.getLeadStatusFieldValue();
        Assert.assertEquals(actualLeadStatus, "Working - Contacted");

        leadsDetailPage.doClickMarkStatusAsCompleteBtn();
        leadsDetailPage.doLeadConversionProcess();
        opportunitiesDetailPage = leadsDetailPage.doClickConvertedOpp();
        String OppURL = opportunitiesDetailPage.getOpportunityCurrentURL();
        /******* Creating Lead and Convering Process Ends Here *********/

        /******* Adding single product and closing the opportunity start Here *********/
        String pName = pName1;
        opportunitiesDetailPage.setSetClickingOpportunityRelatedListTab();
        productsHomePage = opportunitiesDetailPage.setClickingAddProductBtnOnOpportunityRelatedList();
        opportunitiesDetailPage = productsHomePage.addingSingleProductsToRecentlyCreateOPP(pName1, qty1);
        opportunitiesDetailPage.setSetClickingOpportunityRelatedListTab();
        productDetailPage = opportunitiesDetailPage.clickingRecentlyCreatedProductRecord(pName);
        String actualProductCode = productDetailPage.setFetchProductCode();
        System.out.println("Value of the actualProductCode=============>" + actualProductCode);
        Assert.assertEquals(actualProductCode, productCode);
        opportunitiesDetailPage = qsalesHomePage.navigatingToOpportunityDetailPageByUsingUrl(OppURL);
        opportunitiesDetailPage.doClickDetailTab();
        OpportunityStage = opportunitiesDetailPage.setFetchOpportunityStagefieldValue();
        System.out.println("Value of the OpportunityStage==========>" + OpportunityStage);
        opportunitiesDetailPage.doClickMarkStatusAsCompleteBtn();
        actualOpportunityStage = opportunitiesDetailPage.setFetchOpportunityStagefieldValue();
        System.out.println("Value of the actualOpportunityStage==========>" + actualOpportunityStage);
        Assert.assertEquals(actualOpportunityStage, "Qualification", "Opportunity stage is changed from " + OpportunityStage + " to " + actualOpportunityStage + "");


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
        /******* Adding single product and closing the opportunity Ends Here *********/

    }
}
