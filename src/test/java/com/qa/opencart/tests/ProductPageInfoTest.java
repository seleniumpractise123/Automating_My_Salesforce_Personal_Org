package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductPageInfoTest extends BaseTest{

	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void productInfoTest() {
		resultsPage = accPage.doSearch("Macbook");
		productInfoPage = resultsPage.selectProduct("MacBook Pro");
		Map<String,String>productInfoMap = productInfoPage.getProductInfo();
		System.out.println(productInfoMap);
		//{Brand=Apple, Availability=Out Of Stock, extaxprice=$2,000.00, 
		//Product Code=Product 18, Reward Points=800, productprice=$2,000.00, productName=MacBook Pro} --- HashMap
		
		
		//{Brand=Apple, Product Code=Product 18, Reward Points=800, Availability=Out Of Stock, 
		//productprice=$2,000.00, extaxprice=$2,000.00, productName=MacBook Pro} --- LinkedHashMap
		
		//{Availability=Out Of Stock, Brand=Apple, Product Code=Product 18, Reward Points=800, 
		//extaxprice=$2,000.00, productName=MacBook Pro, productprice=$2,000.00} --- Tree Map
		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoMap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(productInfoMap.get("productName"), "MacBook Pro");
		softAssert.assertEquals(productInfoMap.get("productprice"), "$2,000.00");
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		softAssert.assertAll();
		
	}
}
