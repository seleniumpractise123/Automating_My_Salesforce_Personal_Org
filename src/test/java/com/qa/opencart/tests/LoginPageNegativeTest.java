package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageNegativeTest extends BaseTest{
	
	@DataProvider
	public Object[][] incorrectLoginTestData() {
	   return new Object[][] {
		   {"auto123@gmail.com","123456"},
		   {"test@@gamil.com","123456"},
		   {"auto","test"},
		   {"@#@##@#","@!@!@!@"}
	   };
	}

	@Test(dataProvider="incorrectLoginTestData")
	public void loginWithWrongCredentialsTest(String userName,String password) {
		Assert.assertTrue(loginPage.doLoginWithWrongCredentials(userName,password));
	}
}
