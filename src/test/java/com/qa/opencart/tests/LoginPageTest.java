package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;

public class LoginPageTest extends BaseTest{
	
	
	
	@Test
	public void loginPageTitleTest() {
		
	String ActualTitle = loginPage.getLoginPageTitle();
	Assert.assertEquals(ActualTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		
	}
	
	@Test
	public void forgetPasswordLinkExistTest() {
		
	Assert.assertTrue(loginPage.isForgetPwdLinkExist());
		
	}
	
	@Test
	public void loginPageUrlTest() {
		String actualUrl = loginPage.getLoginPageURL();
		Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
		
	}
	
	@Test
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());
		//Assert.assertTrue(accPage.getAccPageTitle().equals("My Account"));
	}
	
	

}
