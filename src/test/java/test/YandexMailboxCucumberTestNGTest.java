package test;

import org.testng.annotations.AfterMethod;

import core.driver.WebDriverSingleton;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import step.UserSteps;

@CucumberOptions(strict = true, plugin = { "json:target/cucumber-report.json", "html:target/cucumber-report" },
		tags = {"@folders_functionality, @login, @advance_actions"},
		features = "src/test/resources/cucumber_features/yandexmail.feature",
		glue = {"step"})

public class YandexMailboxCucumberTestNGTest extends AbstractTestNGCucumberTests {

	@AfterMethod (description = "Log out and close all browsers")
	private void cleanUp(){
		new UserSteps().logOut();
		WebDriverSingleton.tearDown();
	}

}
