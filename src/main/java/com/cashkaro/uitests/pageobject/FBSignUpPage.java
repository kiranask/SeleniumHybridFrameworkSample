package com.cashkaro.uitests.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FBSignUpPage {

	private WebDriver driver;

	public FBSignUpPage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "homelink")
	private WebElement fbLogo;

	public WebElement getFbLogo() {
		return fbLogo;
	}

	@FindBy(id = "email")
	private WebElement emailField;

	public void typeEmail(String email) {
		emailField.sendKeys(email);
	}

	@FindBy(id = "pass")
	private WebElement passwordField;

	public void typePassword(String password) {
		passwordField.sendKeys(password);
	}

	@FindBy(id = "u_0_2")
	private WebElement submitButton;

	public void clickSubmitButton() {
		submitButton.click();
	}
}
