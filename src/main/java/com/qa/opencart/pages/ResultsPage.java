package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {
	
      private WebDriver driver;
      private ElementUtil eleUtil;
	
	//1.Const. of the page class
	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//By Locators
	private By resultsProduct = By.cssSelector("div.product-layout.product-grid");
	
	//Page actions:
	public String getResultsPageTitle(String searchKey) {
		return eleUtil.waitForTitleIsAndCapture(searchKey, AppConstants.MEDIUM_DEFAULT_WAIT);
	}
	
	public int getProductResultsCount() {
		int resultCount = eleUtil.waitForElementsVisible(resultsProduct, AppConstants.MEDIUM_DEFAULT_WAIT).size();
		System.out.println("Product search result count======> " + resultCount);
		return resultCount;
		
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Value of the productName====>" +productName);
		By productNameLocator = By.linkText(productName);
		eleUtil.doClick(productNameLocator);
		return new ProductInfoPage(driver);
	}
	
	


}
