package core.driver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import core.logger.LoggerSingleton;

public class EdgeDriverCreator extends WebDriverCreator {
	@Override
	public WebDriver createDriver() {
		System.setProperty("webdriver.edge.driver" ,"src/test/resources/driver/MicrosoftWebDriver.exe");
		EdgeOptions options = new EdgeOptions();
		driver = new EdgeDriver(options);
		LoggerSingleton.getLogger().info("Edge browser is set");
		return driver;
	}
}
