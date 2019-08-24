package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class EditCustomerPageObject extends AbstractPage {
	private WebDriver driver;

	public EditCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void acceptCustomerNotExistAlert() {
		waitForAlertPresent(driver);
		acceptAlert(driver);
	}

	public String getEditCustomerPageURL() {
		return getCurrentPageURL(driver);
	}

}
