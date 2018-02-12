package step;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import bo.database.EmailDataBase;
import core.driver.WebDriverSingleton;
import core.logger.LoggerSingleton;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import page.DraftEmailPage;
import page.DraftsListPage;
import page.SentListPage;
import page.component.ToolbarComponent;

public class FolderSteps {

	private final static String DRAFTS = "Drafts";
	private final static String SENT = "Sent";
	private WebDriver driver;

	public FolderSteps() {
		super();
		this.driver = WebDriverSingleton.getWebDriverInstance();
	}

	@Then("^Send folder is empty$")
	public boolean isSentFolderEmpty(){
		boolean isSentFolderEmpty = new SentListPage(driver).isSentFolderEmpty();
		LoggerSingleton.getLogger().info(String.format("The 'Sent' folder is empty: %s", isSentFolderEmpty));
		return isSentFolderEmpty;
	}

	@And("^moves all emails to from Sent folder to trash by drag and drop$")
	public void moveAllEmailsInSentIntoTrashAndHighlightSuccessMessage(){
		new SentListPage(driver).toolbarComponent.openSentFolder().selectAllEmailsByAdvanceActions().
		moveEmailsIntoTrashByDragAndDrop().highlightElementeAndTakeScreenshot();;
	}

	public int getnumberEmailsInDrafts(){
		int numberEmailsInDrafts = new DraftsListPage(driver).toolbarComponent.openDraftsFolder().toolbarComponent.
				getNumberOfEmailsInFolder(DRAFTS);
		LoggerSingleton.getLogger().info(String.format("The number of emails in 'Draft' is: %s", numberEmailsInDrafts));
		return numberEmailsInDrafts;
	}

	public int getnumberEmailsInSent(){
		int numberEmailsInSent = new SentListPage(driver).toolbarComponent.openSentFolder().toolbarComponent.
				getNumberOfEmailsInFolder(SENT);
		LoggerSingleton.getLogger().info(String.format("The number of emails in 'Sent' is: %s", numberEmailsInSent));
		return numberEmailsInSent;
	}

	@Then("^an email is (?:presented|not presented) in Drafts (\\w+)$")
	public void isEmailPresentInDraftsList(String emailShouldPresentInDraftsList){
		boolean isEmailShouldPresentInDraftsList = Boolean.valueOf(emailShouldPresentInDraftsList);
		String emailSubject = EmailDataBase.getEmailFromDataBase().get(0).getSubject();
		boolean isEmailPresentInDraftsList = new DraftsListPage(driver).isEmailPresentInDraftsList(emailSubject);
		LoggerSingleton.getLogger().info(String.format
				("The email with subject '%s' is present in 'Drafts' folder: %s", emailSubject, isEmailPresentInDraftsList));
		if (isEmailShouldPresentInDraftsList){
			assertTrue(isEmailPresentInDraftsList);
		} else assertFalse(isEmailPresentInDraftsList);
	}

	@When("^user opens an email by subject$")
	public void openEmailBySubject(){
		String emailSubject = EmailDataBase.getEmailFromDataBase().get(0).getSubject();
		LoggerSingleton.getLogger().info(String.format
				("Open an email with subject: %s", emailSubject));
		new DraftsListPage(driver).openEmailBySubject(emailSubject);
	}

	@When("^user sends an email with specified subject from Draft folder$")
	public void sendEmailFromDraft(){
		int numberEmailsInDrafts = new DraftsListPage(driver).toolbarComponent.openDraftsFolder().toolbarComponent.
				getNumberOfEmailsInFolder(DRAFTS);
		openEmailBySubject();
		LoggerSingleton.getLogger().info("Send an email from 'Drafts'");
		new DraftEmailPage(driver).sendDraftEmail().toolbarComponent.openDraftsFolder().toolbarComponent.
		waitForChangeOfNumberOfEmailsInFolder(numberEmailsInDrafts-1);
	}

	@Then("^an email is presented in Sent$")
	public void isEmailPresentInSentList(){
		String emailSubject = EmailDataBase.getEmailFromDataBase().get(0).getSubject();
		boolean isEmailPresentInSentList = new SentListPage(driver).isEmailPresentInSentList(emailSubject);
		LoggerSingleton.getLogger().info(String.format
				("The email with subject '%s' is present in 'Sent' folder: %s", emailSubject, isEmailPresentInSentList));
		Assert.assertTrue(isEmailPresentInSentList);
	}

	@When ("^user opens Sent folder$")
	public void openSentFolder(){
		LoggerSingleton.getLogger().info("Open 'Sent' folder");
		new ToolbarComponent(driver).openSentFolder().toolbarComponent.waitForChangeOfNumberOfEmailsInFolder(4);;
	}

}


















