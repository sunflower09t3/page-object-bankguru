package pageObjects;

import org.openqa.selenium.WebDriver;
import bankguru.EditCustomerPageUI;
import commons.AbstractPage;

public class EditCustomerPageObject extends AbstractPage {
	private WebDriver driver;

	public EditCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isRedirectedToEditCustomerEntryPage() {
		waitForElementVisible(driver, EditCustomerPageUI.EDIT_CUSTOMER_HEADING);
		return isControlDisplayed(driver, EditCustomerPageUI.EDIT_CUSTOMER_HEADING);
	}

	public String getResultMessage() {
		waitForElementVisible(driver, EditCustomerPageUI.CUSTOMER_UPDATE_RESULT);
		return getTextElement(driver, EditCustomerPageUI.CUSTOMER_UPDATE_RESULT);
	}

	public String getAlertMessage() {
		waitForAlertPresent(driver);
		return getTextAlert(driver);
	}

	public void acceptCustomerNotExistInformationAlert() {
		waitForAlertPresent(driver);
		acceptAlert(driver);
	}

}
