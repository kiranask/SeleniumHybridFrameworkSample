package com.cashkaro.uitests.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.internal.WrapsElement;
import org.testng.Assert;

import com.cashkaro.uitests.config.ProjectConfig;
import com.google.common.base.CharMatcher;

import net.sourceforge.tess4j.Tesseract;

public class CaptchaUtils {

	public String getExtractedTextFromImage(WebElement image) {

		String extractedText = null;

		try {
			// capture Element picture method will get image of the given
			// element
			File imageFile = captureElementPicture(image);

			Tesseract instance = new Tesseract();
			instance.setDatapath(ProjectConfig.TESSERACT_DATA_PATH);

			// the doOCR method of Tesseract will retrive the text from captured
			// image
			extractedText = instance.doOCR(imageFile);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Something went wrong while reading text from image");

		}

		return extractedText;
	}

	public File captureElementPicture(WebElement element) throws Exception {

		// get the WrapsDriver of the WebElement
		WrapsDriver wrapsDriver;

		if (WrapsElement.class.isAssignableFrom(element.getClass())) {
			wrapsDriver = ((WrapsDriver) ((WrapsElement) element).getWrappedElement());
		} else {
			wrapsDriver = (WrapsDriver) element;
		}

		// get the entire screenshot from the driver of passed WebElement
		File screen = ((TakesScreenshot) wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);

		// create an instance of buffered image from captured screenshot
		BufferedImage img = ImageIO.read(screen);

		// get the width and height of the WebElement using getSize()
		int width = element.getSize().getWidth();
		int height = element.getSize().getHeight();

		// create a rectangle using width and height
		Rectangle rect = new Rectangle(width, height);

		// get the location of WebElement in a Point.
		// this will provide X & Y co-ordinates of the WebElement
		Point p = element.getLocation();

		// create image for element using its location and size.
		// this will give image data specific to the WebElement
		BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);

		// write back the image data for element in File object
		ImageIO.write(dest, "png", screen);

		// return the File object containing image data
		return screen;
	}

	// Retrieve integers from the string and returns sum of integer
	public int getCaptchaValue(String extractedText) {

		// Retrieve integers from the string
		String str = CharMatcher.DIGIT.retainFrom(extractedText);

		int numbers = Integer.parseInt(str);

		int sum = 0;

		while (numbers > 0) {

			sum = sum + numbers % 10;
			numbers = numbers / 10;

		}

		return sum;
	}
}