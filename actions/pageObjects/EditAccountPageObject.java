package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class EditAccountPageObject extends AbstractPage {
	private WebDriver driver;

	public EditAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getAlertMessage() {
		waitForAlertPresent(driver);
		return getTextAlert(driver);
	}

	public void acceptAccountNotExistInformationAlert() {
		waitForAlertPresent(driver);
		acceptAlert(driver);
	}

	public String getEditAccountPageURL() {
		return getCurrentPageURL(driver);
	}

}
