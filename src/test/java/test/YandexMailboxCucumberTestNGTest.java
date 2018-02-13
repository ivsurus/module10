package test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import bo.database.EmailDataBase;
import core.driver.WebDriverSingleton;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import step.UserSteps;

@CucumberOptions(strict = true, plugin = { "json:target/cucumber-report.json", "html:target/cucumber-report" },
		tags = {"@folders_functionality, @login, @advance_actions"},
		features = "src/test/resources/cucumber_features/",
		glue = {"step"})

public class YandexMailboxCucumberTestNGTest extends AbstractTestNGCucumberTests {

	@BeforeMethod (description = "create new email data base for each test")
	private void setUp(){
		EmailDataBase.instatiateEmailDataBase();
	}

	@AfterMethod (description = "Log out and close all browsers")
	private void cleanUp(){
		new UserSteps().logOut();
		WebDriverSingleton.tearDown();
	}

}
