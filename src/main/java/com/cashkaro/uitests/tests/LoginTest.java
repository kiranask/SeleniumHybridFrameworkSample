package com.cashkaro.uitests.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cashkaro.uitests.actions.CommonActions;
import com.cashkaro.uitests.base.BaseTest;
import com.cashkaro.uitests.utils.TestDataProvider;

public class LoginTest extends BaseTest {

	private CommonActions commonActions;

	@BeforeMethod(alwaysRun = true)
	public void setUp() {

		commonActions = PageFactory.initElements(driver, CommonActions.class);
	}

	@Test(dataProvider = "testdataprovider", dataProviderClass = TestDataProvider.class)
	public void testLogin(String email, String password) {

		commonActions.performLoginAction(email, password);
	}
}
