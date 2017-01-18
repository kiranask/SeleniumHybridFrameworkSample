package com.cashkaro.uitests.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.cashkaro.uitests.pageobject.HomePage;
import com.cashkaro.uitests.pageobject.MainPage;

public class CommonActions {

	private WebDriver driver;
	private HomePage homePage;
	private MainPage mainPage;

	public CommonActions(WebDriver driver) {
		this.driver = driver;
		homePage = PageFactory.initElements(driver, HomePage.class);
	}

	public void performLoginAction(String email, String password) {

		homePage.clickSignInButton();

		// switch to iframe with name
		driver.switchTo().frame(homePage.getLoginPopUp());

		homePage.typeEmail(email);

		homePage.clickPasswordField();
		
		homePage.typePassword(password);

		mainPage = homePage.clickSubmitButton();
		
		boolean isLoggedIn = mainPage.isLogOutButtonDisplayed();
		
		Assert.assertTrue(isLoggedIn, "User not logged in! Please verify credentials");
	}

}
