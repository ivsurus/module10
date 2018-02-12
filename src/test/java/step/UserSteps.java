package step;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import bo.User;
import bo.factory.UserFactory;
import core.driver.WebDriverSingleton;
import core.logger.LoggerSingleton;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import page.InboxListPage;
import page.LoginPage;
import page.component.ToolbarComponent;

public class UserSteps {

	private final static String URL = "http://yandex.com";
	private WebDriver driver;

	public UserSteps() {
		super();
		this.driver = WebDriverSingleton.getWebDriverInstance();
	}

	public void logOut(){
		new ToolbarComponent(driver).expandUserMenu().clickLogOutFromUserMenu();
	}

	@Given("^user navigates to yandex home page$")
	public void navigateToHomePage(){
		driver.get(URL);
	}

	@And("^user performs login in$")
	public InboxListPage logIn(){
		User user = UserFactory.getModule5testmailboxUser();
		return new LoginPage(driver).fillInUserLogin(user.getLogin()).
				fillInPassword(user.getPassword()).clickLoginButton();
	}

	@Then("^user login name is displayed at home page$")
	public void getActualUserName(){
		User user = UserFactory.getModule5testmailboxUser();
		String actualUserName = new ToolbarComponent(driver).getUserName();
		LoggerSingleton.getLogger().info(String.format("Actual user name is: %s", actualUserName));
		Assert.assertEquals(actualUserName, user.getLogin());
	}
}
