package com.cashkaro.uitests.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	private WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@class='fl logo']")
	private WebElement Logo;

	public boolean isLogoDisplayed() {
		return Logo.isDisplayed();
	}

	@FindBy(xpath = "//a[contains(@class,'fl last')]")
	private WebElement JoinFreeButton;

	public JoinFreeNowPage clickJoinFreeButton() {
		JoinFreeButton.click();
		return PageFactory.initElements(driver, JoinFreeNowPage.class);
	}

	@FindBy(xpath = "//a[contains(@class,'fl signin_popupbox')]")
	private WebElement signInButton;

	public void clickSignInButton() {
		signInButton.click();
	}

	@FindBy(className = "cboxIframe")
	private WebElement loginPopUp;

	public WebElement getLoginPopUp() {
		return loginPopUp;
	}

	@FindBy(id = "uname")
	private WebElement useremail;

	public void typeEmail(String email) {
		useremail.sendKeys(email);
	}

	@FindBy(id = "pwd-txt")
	private WebElement password;

	public void clickPasswordField() {
		password.click();
	}

	@FindBy(id = "pwd")
	private WebElement passwordField;

	public void typePassword(String password) {
		passwordField.sendKeys(password);
	}

	@FindBy(id = "sign_in")
	private WebElement submitButton;

	public MainPage clickSubmitButton() {
		submitButton.click();
		return PageFactory.initElements(driver, MainPage.class);
	}

	@FindBy(linkText = "Forgot Password?")
	private WebElement forgotPasswordLink;

	public void clickForgotPasswordLink() {
		forgotPasswordLink.click();
	}

	@FindBy(id = "from_email")
	private WebElement forgotPasswordEmail;

	public void typeForgotPasswordEmail(String email) {
		forgotPasswordEmail.sendKeys(email);
		;
	}

	@FindBy(id = "submit_req")
	private WebElement submitForgotPasswordEmail;

	public void submitForgotPasswordEmail() {
		submitForgotPasswordEmail.click();
	}

}
