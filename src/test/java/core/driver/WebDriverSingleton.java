package core.driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import core.logger.LoggerSingleton;

//Singleton pattern
//Factory method pattern

public class WebDriverSingleton {

	private static WebDriver driver;

	private WebDriverSingleton() {
	}

	public static WebDriver getWebDriverInstance() {
		if (driver == null) {
			String browserName = getBrowserNameFromProperties();
			driver = getDriverCreator(browserName).createDriver();
			driver = new CustomDriverDecorator(driver);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		return driver;
	}

	public static void tearDown(){
		driver.quit();
		driver = null;
	}

	private static String getBrowserNameFromProperties(){
		Properties properties = new Properties();
		try {
			InputStream input  = new FileInputStream("browser.properties");
			properties.load(input);
		} catch (IOException e) {
			LoggerSingleton.getLogger().error(e.getMessage());
		}
		LoggerSingleton.getLogger().info("Properties file is readed successfully");
		return properties.getProperty("browser");
	}

	private static WebDriverCreator getDriverCreator(String browserName) {
		WebDriverCreator creator;
		switch(browserName){
		case "edge":
			creator = new EdgeDriverCreator();
		default:
			creator = new ChromeDriverCreator();
		}
		return creator;
	}
}
