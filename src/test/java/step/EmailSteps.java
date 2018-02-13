package step;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import bo.Email;
import bo.database.EmailDataBase;
import core.driver.WebDriverSingleton;
import core.logger.LoggerSingleton;
import core.util.RandomDataHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import page.DraftEmailPage;
import page.InboxListPage;
import page.NewEmailPage;

public class EmailSteps {

	private final static int EMAIL_INDEX = 0;
	private WebDriver driver;
	private FolderSteps folderSteps;

	public EmailSteps() {
		super();
		this.driver = WebDriverSingleton.getWebDriverInstance();
		folderSteps = new FolderSteps();
	}

	@When("^user creates (\\d+) and sends them$")
	public void createANumberOfEmailsWithRandomDataAndSendThem(int numberOfEmails){
		LoggerSingleton.getLogger().info(String.format("Create %s email(s) with random data", numberOfEmails));
		NewEmailPage newEmailPage = new NewEmailPage(driver);
		for (int i=0; i<numberOfEmails; i++){
			createNewEmailAndFillInData();
			newEmailPage.sendEmail();
		}
	}

	@When("^user creates an email and saves it to Draft folder$")
	public void createEmailWithRandomDataAndSaveToDrafts(){
		NewEmailPage newEmailPage = new NewEmailPage(driver);
		int numberEmailsInDrafts = folderSteps.getnumberEmailsInDrafts();
		LoggerSingleton.getLogger().info("Create email with random data");
		createNewEmailAndFillInData();
		newEmailPage.closeEmailAndSaveToDraft().toolbarComponent.openDraftsFolder().
		toolbarComponent.waitForChangeOfNumberOfEmailsInFolder(numberEmailsInDrafts+1);
	}

	@Then ("^correct an email adress is displayed$")
	public void getActualEmailAdress(){
		Email email = EmailDataBase.getEmailFromDataBase().get(EMAIL_INDEX);
		String actualEmailAdress = new DraftEmailPage(driver).getEmailAdress();
		LoggerSingleton.getLogger().info(String.format("Actual email adress is: %s", actualEmailAdress));
		Assert.assertEquals(actualEmailAdress, email.getAdress());
	}

	@And("^correct an email subject is displayed$")
	public void getActualEmailSubject(){
		Email email = EmailDataBase.getEmailFromDataBase().get(EMAIL_INDEX);
		String actualEmailSubject = new DraftEmailPage(driver).getSubject();
		LoggerSingleton.getLogger().info(String.format("Actual email subject is: %s", actualEmailSubject));
		Assert.assertEquals(actualEmailSubject, email.getSubject());
	}

	@And("^correct an email body is displayed$")
	public void getActualEmailBody(){
		Email email = EmailDataBase.getEmailFromDataBase().get(EMAIL_INDEX);
		String actualEmailBody = new DraftEmailPage(driver).getBody();
		LoggerSingleton.getLogger().info(String.format("Actual email body is: %s", actualEmailBody));
		Assert.assertEquals(actualEmailBody, email.getBody());
	}

	private Email createNewEmailAndFillInData(){
		InboxListPage inboxListPage = new InboxListPage(driver);
		Email email = new Email.EmailBuilder(RandomDataHelper.getRandomEmailAdress()).
				subject(RandomDataHelper.getRandomEmailSubject()).
				body(RandomDataHelper.getRandomEmailBody()).build();
		inboxListPage.toolbarComponent.createNewEmail().fillInEmailAdress(email.getAdress()).
		fillInEmailSubject(email.getSubject()).fillInEmailBody(email.getBody());
		LoggerSingleton.getLogger().info(email.toString());
		EmailDataBase.addEmailToDataBase(email);
		return email;
	}
}
