package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProductDataProvider;

public class SearchTest extends BaseTest{

	@BeforeClass
	public void searchSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	
	@Test(priority = 1, dataProvider="productDataWithSearchKey",dataProviderClass=ProductDataProvider.class)
	public void searchProductResultsCountTest(String searchKey) {
		System.out.println("Value of the searchKey==========>" + searchKey);
		resultsPage = accPage.doSearch(searchKey);
		Assert.assertTrue(resultsPage.getProductResultsCount() > 0);
		
	}
	
	@Test(priority = 2, dataProvider="productDataWithSearchKey",dataProviderClass=ProductDataProvider.class)
	public void searchPageTitleTest(String searchKey) {
		System.out.println("driver came this method searchPageTitleTest");
		resultsPage = accPage.doSearch(searchKey);
		String actSerchTitle = resultsPage.getResultsPageTitle(searchKey);
		System.out.println("Search Title : " + actSerchTitle);
		Assert.assertEquals(actSerchTitle, "Search - "+searchKey);
	}
	
	
	
	@Test(priority = 3,dataProvider="productDataWithProductName",dataProviderClass=ProductDataProvider.class)
	public void selectProductTest(String srarchKey, String productName) {
		System.out.println("driver came this method selectProductTest");
		resultsPage = accPage.doSearch(srarchKey);
		productInfoPage = resultsPage.selectProduct(productName);
		String actproductHeaderName = productInfoPage.getProductHeaderName();
		System.out.println("Actual Product Name : " + actproductHeaderName);
		Assert.assertEquals(actproductHeaderName, productName);
	}
	
	
	
	@Test(priority = 4,dataProvider="productDataWithImage",dataProviderClass=ProductDataProvider.class)
	public void ProductImagesTest(String searchKey,String productName, int expImagesCount) {
		System.out.println("driver came this method ProductImagesTest");
		resultsPage = accPage.doSearch(searchKey);
		productInfoPage = resultsPage.selectProduct(productName);
		int actProductImagesCount = productInfoPage.getProductImagesCount();
		System.out.println("Actual Product Images Count : " + actProductImagesCount);
		Assert.assertEquals(actProductImagesCount, expImagesCount);
	}
}
