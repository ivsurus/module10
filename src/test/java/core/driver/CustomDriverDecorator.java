package core.driver;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import core.logger.LoggerSingleton;

//Decorator pattern
public class CustomDriverDecorator implements WebDriver, JavascriptExecutor {

	protected WebDriver driver;


	public CustomDriverDecorator(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public void get(String url) {
		LoggerSingleton.getLogger().info("Navigate to URL: " + url);
		driver.get(url);
	}
	@Override
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	@Override
	public String getTitle() {
		return driver.getTitle();
	}
	@Override
	public List<WebElement> findElements(By by) {
		LoggerSingleton.getLogger().debug(String.format("Finding element: %s, current URL: '%s'", by.toString(), driver.getCurrentUrl()),
				true);
		return driver.findElements(by);
	}
	@Override
	public WebElement findElement(By by) {
		LoggerSingleton.getLogger().debug(String.format("Finding element: %s, current URL: '%s'", by.toString(), driver.getCurrentUrl()),
				true);
		return driver.findElement(by);
	}
	@Override
	public String getPageSource() {
		return driver.getPageSource();
	}
	@Override
	public void close() {
		driver.close();
	}
	@Override
	public void quit() {
		LoggerSingleton.getLogger().info("Browser will be closed now...");
		driver.quit();
	}
	@Override
	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}
	@Override
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}
	@Override
	public TargetLocator switchTo() {
		return driver.switchTo();
	}
	@Override
	public Navigation navigate() {
		return driver.navigate();
	}
	@Override
	public Options manage() {
		return driver.manage();
	}

	@Override
	public Object executeScript(String script, Object... args) {
		return ((JavascriptExecutor)driver).executeScript(script, args);
	}

	@Override
	public Object executeAsyncScript(String script, Object... args) {
		return ((JavascriptExecutor)driver).executeAsyncScript(script, args);
	}

	public void selectAllByShiftPlusMouseClick(WebElement lastElementToSelect){
		new Actions(driver).sendKeys(Keys.LEFT_SHIFT).click(lastElementToSelect).build().perform();
	}

	public void dragAndDrop(WebElement source, WebElement target){
		new Actions(driver).dragAndDrop(source, target).build().perform();
	}

	public File makeScreenshot(){
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}

}
