package com.cashkaro.uitests.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

	private WebDriver driver;

	public MainPage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "sign_in")
	private WebElement logOutButton;

	public boolean isLogOutButtonDisplayed() {
		return logOutButton.isDisplayed();
	}
}
