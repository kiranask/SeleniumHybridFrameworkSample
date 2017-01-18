package com.cashkaro.uitests.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cashkaro.uitests.base.BaseTest;
import com.cashkaro.uitests.pageobject.HomePage;
import com.cashkaro.uitests.utils.EmailUtils;
import com.cashkaro.uitests.utils.TestDataProvider;

public class ForgotPasswordTest extends BaseTest {

	private HomePage homePage;
	private EmailUtils emailUtils;

	@BeforeMethod(alwaysRun = true)
	public void setUp() {

		homePage = PageFactory.initElements(driver, HomePage.class);
		emailUtils = PageFactory.initElements(driver, EmailUtils.class);
	}

	@Test(dataProvider = "testdataprovider", dataProviderClass = TestDataProvider.class)
	public void testForgotPassword(String email, String password, String subject) {

		homePage.clickSignInButton();

		// switch to iframe with name
		driver.switchTo().frame(homePage.getLoginPopUp());

		homePage.clickForgotPasswordLink();

		homePage.typeForgotPasswordEmail(email);

		homePage.submitForgotPasswordEmail();

		String mail = emailUtils.getMail(email, password, subject);

		/*
		 * TODO for some reason I'm not getting forgot password mail. Next step
		 * is to parse the password from the mail stored in string variable and
		 * retry to login
		 */

	}
}
