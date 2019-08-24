package pageObjects;

import org.openqa.selenium.WebDriver;
import commons.AbstractPage;
import commons.PageGeneratorManager;

public class DeleteAccountPageObject extends AbstractPage {
	private WebDriver driver;

	public DeleteAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void acceptDeleteAccountConfirmationAlert() {
		waitForAlertPresent(driver);
		acceptAlert(driver);
	}

	public HomePageObject acceptAccountDeletedSuccessfullyAlert() {
		waitForAlertPresent(driver);
		acceptAlert(driver);
		return PageGeneratorManager.getHomePage(driver);
	}

	public String getDeleteAccountPageURL() {
		return getCurrentPageURL(driver);
	}

}
