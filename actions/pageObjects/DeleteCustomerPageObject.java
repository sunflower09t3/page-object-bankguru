package pageObjects;

import org.openqa.selenium.WebDriver;
import commons.AbstractPage;
import commons.PageGeneratorManager;

public class DeleteCustomerPageObject extends AbstractPage {
	private WebDriver driver;

	public DeleteCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void acceptDeleteCustomerConfirmationAlert() {
		waitForAlertPresent(driver);
		acceptAlert(driver);
	}

	public String getAlertMessage() {
		waitForAlertPresent(driver);
		return getTextAlert(driver);
	}

	public HomePageObject acceptCustomerDeletedInformationAlert() {
		waitForAlertPresent(driver);
		acceptAlert(driver);
		return PageGeneratorManager.getHomePage(driver);
	}

	public String getDeleteCustomerPageURL() {
		return getCurrentPageURL(driver);
	}

}