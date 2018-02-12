package core.driver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import core.logger.LoggerSingleton;

public class ChromeDriverCreator extends WebDriverCreator {
	@Override
	public WebDriver createDriver() {
		System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chrome-driver-2.35.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		driver = new ChromeDriver(options);
		LoggerSingleton.getLogger().info("Chrome browser is set");
		return driver;
	}
}
