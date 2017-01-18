package com.cashkaro.uitests.tests;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cashkaro.uitests.base.BaseTest;
import com.cashkaro.uitests.pageobject.FBSignUpPage;
import com.cashkaro.uitests.pageobject.HomePage;
import com.cashkaro.uitests.pageobject.JoinFreeNowPage;
import com.cashkaro.uitests.pageobject.MainPage;
import com.cashkaro.uitests.utils.TestDataProvider;

public class JoinWithFBTest extends BaseTest {

	private HomePage homePage;
	private JoinFreeNowPage joinFreeNowPage;
	private FBSignUpPage fbsignUpPage;
	private MainPage mainPage;

	@BeforeMethod(alwaysRun = true)
	public void setUp() {

		homePage = PageFactory.initElements(driver, HomePage.class);
		fbsignUpPage = PageFactory.initElements(driver, FBSignUpPage.class);
		mainPage = PageFactory.initElements(driver, MainPage.class);
	}

	@Test(dataProvider = "testdataprovider", dataProviderClass = TestDataProvider.class)
	public void testJoinWithFB(String email, String password) {

		joinFreeNowPage = homePage.clickJoinFreeButton();

		String Parent_Window = driver.getWindowHandle();

		joinFreeNowPage.clickfbButton();

		// get all the window handles
		Set<String> s = driver.getWindowHandles();
		Iterator<String> ite = s.iterator();

		// Loop will window handles
		while (ite.hasNext()) {
			// get the current window handle name
			String currentHandle = ite.next().toString();

			// if current handle is not parent handle then switch
			if (!currentHandle.contains(Parent_Window)) {
				driver.switchTo().window(currentHandle);
				// Wait for child page to load completely
				try {
					new WebDriverWait(driver, 30)
							.until(ExpectedConditions.elementToBeClickable(fbsignUpPage.getFbLogo()));
				} catch (WebDriverException e) {

					e.printStackTrace();
					Assert.fail("Page is not loaded properly!");

				}

				// Performing actions on child window
				fbsignUpPage.typeEmail(email);

				fbsignUpPage.typePassword(password);

				fbsignUpPage.clickSubmitButton();

				// Switch to parent window again
				driver.switchTo().window(Parent_Window);
			}

		}

		boolean isLoggedIn = mainPage.isLogOutButtonDisplayed();

		Assert.assertTrue(isLoggedIn, "User not logged in! Please verify credentials");

	}
}
