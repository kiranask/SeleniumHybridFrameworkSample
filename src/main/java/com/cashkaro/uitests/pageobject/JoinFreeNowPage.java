package com.cashkaro.uitests.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JoinFreeNowPage {

	private WebDriver driver;

	public JoinFreeNowPage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "firstname")
	private WebElement username;

	public void typeName(String name) {
		username.sendKeys(name);
	}

	@FindBy(id = "email")
	private WebElement useremail;

	public void typeEmail(String email) {
		useremail.sendKeys(email);
	}

	@FindBy(id = "con_email")
	private WebElement confirmEmail;

	public void typeConfirmEmail(String email) {
		confirmEmail.sendKeys(email);
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

	@FindBy(id = "security_image")
	private WebElement captchaImage;

	public WebElement getCaptchaImage() {
		return captchaImage;
	}

	@FindBy(id = "to_be_check")
	private WebElement captchaField;

	public void typeCaptcha(int captcha) {
		captchaField.sendKeys(Integer.toString(captcha));
	}

	@FindBy(id = "join_free_submit")
	private WebElement submit;

	public MainPage clickSubmit() {
		submit.click();
		return PageFactory.initElements(driver, MainPage.class);
	}

	@FindBy(id = "close_and_go_fb")
	private WebElement fbButton;

	public void clickfbButton() {
		fbButton.click();
	}

}
