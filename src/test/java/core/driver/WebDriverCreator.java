package core.driver;

import org.openqa.selenium.WebDriver;

public abstract class WebDriverCreator {
	protected WebDriver driver;

	public abstract WebDriver createDriver();
}
