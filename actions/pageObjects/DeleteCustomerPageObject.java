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

	public HomePageObject acceptCustomerDeletedSuccessfullyAlert() {
		waitForAlertPresent(driver);
		acceptAlert(driver);
		return PageGeneratorManager.getHomePage(driver);
	}

	public String getDeleteCustomerPageURL() {
		return getCurrentPageURL(driver);
	}

}