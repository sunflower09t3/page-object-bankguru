package pageObjects;

import org.openqa.selenium.WebDriver;
import bankguru.EditAccountPageUI;
import commons.AbstractPage;

public class EditAccountPageObject extends AbstractPage {
	private WebDriver driver;

	public EditAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectAccountType(String accountType) {
		waitForElementVisible(driver, EditAccountPageUI.ACCOUNT_TYPE_DROPDOWN);
		selectItemInHtmlDropdownByVisibleTex(driver, EditAccountPageUI.ACCOUNT_TYPE_DROPDOWN, accountType);
	}

	public String getResultMessage() {
		waitForElementVisible(driver, EditAccountPageUI.ACCOUNT_UPDATE_RESULT);
		return getTextElement(driver, EditAccountPageUI.ACCOUNT_UPDATE_RESULT);
	}

	public String getAccountType() {
		waitForElementVisible(driver, EditAccountPageUI.ACCOUNT_TYPE_TEXT_IN_TABLE);
		return getTextElement(driver, EditAccountPageUI.ACCOUNT_TYPE_TEXT_IN_TABLE);
	}

	public String getAlertMessage() {
		waitForAlertPresent(driver);
		return getTextAlert(driver);
	}

	public void acceptAccountNotExistInformationAlert() {
		waitForAlertPresent(driver);
		acceptAlert(driver);
	}

	public boolean isEditAccountEntryFormDisplayed() {
		waitForElementVisible(driver, EditAccountPageUI.EDIT_ACCOUNT_ENTRY_FORM_TEXT);
		return isControlDisplayed(driver, EditAccountPageUI.EDIT_ACCOUNT_ENTRY_FORM_TEXT);
	}

}
