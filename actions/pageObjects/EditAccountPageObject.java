package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class EditAccountPageObject extends AbstractPage {
	private WebDriver driver;

	public EditAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void acceptAccountNotExistAlert() {
		waitForAlertPresent(driver);
		acceptAlert(driver);
	}

	public String getEditAccountPageURL() {
		return getCurrentPageURL(driver);
	}

}
