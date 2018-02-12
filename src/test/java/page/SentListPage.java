package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.driver.CustomDriverDecorator;
import core.util.ScreenshotHelper;

public class SentListPage extends AbstractPage {

	private WebDriver driver;
	private By allSubjects = By.xpath("//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_subject']/span");
	private String emailCheckBox ="(//span[@class ='_nb-checkbox-flag _nb-checkbox-normal-flag'])[%s]";

	@FindBy(xpath = "//button//span[text()='More']")
	private WebElement moreButton;

	@FindBy(className = "ns-view-messages-item-wrap")
	private List<WebElement> allEmails;

	@FindBy(xpath = "span._nb-checkbox-flag._nb-checkbox-normal-flag")
	private List<WebElement> allCheckboxes;

	@FindBy(css =".ns-view-toolbar-button-delete")
	private WebElement deleteLink;

	@FindBy(className = "mail-MessageSnippet-FromText")
	private WebElement firstEmail;

	@FindBy(css = ".ns-view-toolbar-button-main-select-all span.checkbox_view")
	private WebElement selectAllCheckBox;

	@FindBy(css = ".js-messages-count")
	private WebElement countElementsMessage;

	@FindBy(css = "div.ns-view-messages-pager-load-more button")
	private WebElement loadMoreButton;

	@FindBy(xpath = "//div[text()='There are no messages in “Sent”.']")
	private WebElement emptyFolderMessage;

	public SentListPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isEmailPresentInSentList(String subject){
		List<WebElement> allSubjectsInDrafts = driver.findElements(allSubjects);
		for(WebElement subjectElement: allSubjectsInDrafts){
			if(subjectElement.getText().equals(subject)){
				return true;
			}
		}
		return false;
	}

	public boolean isSentFolderEmpty(){
		return emptyFolderMessage.isDisplayed();
	}

	public SentListPage selectAllEmailsByAdvanceActions(){
		int numberOfEmails = allEmails.size();
		By lastEmailCheckBoxBy = By.xpath(String.format(emailCheckBox, numberOfEmails));
		((CustomDriverDecorator) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		WebElement lastEmailCheckBox = driver.findElement(lastEmailCheckBoxBy);
		((CustomDriverDecorator) driver).selectAllByShiftPlusMouseClick(lastEmailCheckBox);
    	((CustomDriverDecorator) driver).executeScript("window.scrollTo(0, 0)");
		return this;
	}

	public SentListPage moveEmailsIntoTrashByDragAndDrop(){
		((CustomDriverDecorator) driver).dragAndDrop(firstEmail, deleteLink);
		return this;
	}

	public SentListPage highlightElementeAndTakeScreenshot(){
		ScreenshotHelper.highlightElementAndMakeScreenshot(driver, emptyFolderMessage);
		return this;
	}
}
