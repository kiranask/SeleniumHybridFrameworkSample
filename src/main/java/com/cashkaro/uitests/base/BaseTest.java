package com.cashkaro.uitests.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.cashkaro.uitests.config.ProjectConfig;

public class BaseTest {

	public WebDriver driver;

	@BeforeClass
	public void createDriver() {

		System.setProperty("webdriver.chrome.driver", ProjectConfig.CHROME_DRIVER_PATH);

		driver = new ChromeDriver();

		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(ProjectConfig.URL);

		// Wait for main page to be displayed
		try {

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='fl logo']")));

		} catch (WebDriverException e) {

			e.printStackTrace();
			Assert.fail("Page is not loaded properly. Skipping the test!");

		}

	}

	@AfterClass
	public void quitDriver() {

		if (driver != null) {
			driver.quit();
		}

	}

}
