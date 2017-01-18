package com.cashkaro.uitests.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cashkaro.uitests.base.BaseTest;
import com.cashkaro.uitests.pageobject.HomePage;
import com.cashkaro.uitests.pageobject.JoinFreeNowPage;
import com.cashkaro.uitests.pageobject.MainPage;
import com.cashkaro.uitests.utils.CaptchaUtils;
import com.cashkaro.uitests.utils.TestDataProvider;

public class JoinWithEmailTest extends BaseTest {

	private HomePage homePage;
	private JoinFreeNowPage joinFreeNowPage;
	private CaptchaUtils captchaUtils;
	private MainPage mainPage;

	@BeforeMethod(alwaysRun = true)
	public void setUp() {

		homePage = PageFactory.initElements(driver, HomePage.class);
		joinFreeNowPage = PageFactory.initElements(driver, JoinFreeNowPage.class);
		captchaUtils = PageFactory.initElements(driver, CaptchaUtils.class);
	}

	@Test(dataProvider = "testdataprovider", dataProviderClass = TestDataProvider.class)
	public void testJoinWithEmail(String name, String email, String password) {

		joinFreeNowPage = homePage.clickJoinFreeButton();

		joinFreeNowPage.typeName(name);

		joinFreeNowPage.typeEmail(email);

		joinFreeNowPage.typeConfirmEmail(email);

		joinFreeNowPage.clickPasswordField();

		joinFreeNowPage.typePassword(password);

		WebElement captchaImage = joinFreeNowPage.getCaptchaImage();

		String captcha = captchaUtils.getExtractedTextFromImage(captchaImage);

		System.out.println(captcha);
		int captchaValue = captchaUtils.getCaptchaValue(captcha);

		joinFreeNowPage.typeCaptcha(captchaValue);

		mainPage = joinFreeNowPage.clickSubmit();

		boolean isLoggedIn = mainPage.isLogOutButtonDisplayed();

		Assert.assertTrue(isLoggedIn, "User not logged in! Please verify credentials");

	}

	@AfterMethod
	public void teardown() {

	}

}
