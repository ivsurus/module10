package core.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.driver.CustomDriverDecorator;

public class ScreenshotHelper {

	public static void highlightElementAndMakeScreenshot(WebDriver driver, WebElement element) {
		String backgound = element.getCssValue("backgroundColor");
		JavascriptExecutor js = ((CustomDriverDecorator) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + "yellow" + "'", element);
		makeScreenshot(driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + backgound + "'", element);
	}

	public static void makeScreenshot(WebDriver driver) {
		File screenshot = ((CustomDriverDecorator) driver).makeScreenshot();
		try {
			FileUtils.copyFileToDirectory(screenshot, new File("src\\test\\resources\\screenshots\\"));
		} catch (IOException e){
		}
	}
}


