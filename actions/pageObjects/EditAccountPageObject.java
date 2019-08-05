package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.EditAccountPageUI;
import commons.AbstractPage;

public class EditAccountPageObject extends AbstractPage{
	private WebDriver driver;

	public EditAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToAccountNoTextbox(String accountNo) {
		waitForElementVisible(driver, EditAccountPageUI.ACCOUNT_NO_TEXTBOX);
		sendKeyToElement(driver, EditAccountPageUI.ACCOUNT_NO_TEXTBOX, accountNo);
	}

	public void clickSubmitButton() {
		waitForElementVisible(driver, EditAccountPageUI.SUBMIT_BUTTON);
		clickToElement(driver, EditAccountPageUI.SUBMIT_BUTTON);
	}

	public void selectAccountType(String accountType) {
		waitForElementVisible(driver, EditAccountPageUI.ACCOUNT_TYPE_DROPDOWN);
		selectItemInHtmlDropdownByVisibleTex(driver, EditAccountPageUI.ACCOUNT_TYPE_DROPDOWN, accountType);
	}

	public boolean isAccountDetailsUpdatedSuccessfulMessageDisplayed() {
		waitForElementVisible(driver, EditAccountPageUI.ACCOUNT_DETAILS_UPDATED_SUCCESSFUL_MESSAGE);
		return isControlDisplayed(driver, EditAccountPageUI.ACCOUNT_DETAILS_UPDATED_SUCCESSFUL_MESSAGE);
	}

	public String getAccountType() {
		waitForElementVisible(driver, EditAccountPageUI.ACCOUNT_TYPE_TEXT);
		return getTextElement(driver, EditAccountPageUI.ACCOUNT_TYPE_TEXT);
	}

	public String getAlertMessage() {
		waitForAlertPresent(driver);
		return getTextAlert(driver);
	}

	public void acceptAccountNotExistInformationAlert() {
		waitForAlertPresent(driver);
		acceptAlert(driver);
	}
}
