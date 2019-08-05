package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import bankguru.DeleteCustomerPageUI;
import commons.AbstractPage;
import commons.PageGeneratorManager;

public class DeleteCustomerPageObject extends AbstractPage{
	private WebDriver driver;

	public DeleteCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToCustomerIDTextbox(String customerID) {
		waitForElementVisible(driver, DeleteCustomerPageUI.CUSTOMER_ID_TEXTBOX);
		sendKeyToElement(driver, DeleteCustomerPageUI.CUSTOMER_ID_TEXTBOX, customerID);
		
	}

	public void clickSubmitButton() {
		waitForElementVisible(driver, DeleteCustomerPageUI.SUBMIT_BUTTON);
		clickToElement(driver, DeleteCustomerPageUI.SUBMIT_BUTTON);
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

	public void inputNothingToCustomerIDTextboxAndPressTabKey() {
		waitForElementVisible(driver, DeleteCustomerPageUI.CUSTOMER_ID_TEXTBOX);
		sendKeyToElement(driver, DeleteCustomerPageUI.CUSTOMER_ID_TEXTBOX, "");
		sendKeyBoardToElement(driver, DeleteCustomerPageUI.CUSTOMER_ID_TEXTBOX, Keys.TAB);
	}

	public boolean isCustomerIDMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, DeleteCustomerPageUI.CUSTOMER_ID_MUST_NOT_BE_BLANK_MESSAGE);
		return isControlDisplayed(driver, DeleteCustomerPageUI.CUSTOMER_ID_MUST_NOT_BE_BLANK_MESSAGE);
	}

	public boolean isCustomerIDMustNotContainCharacterMessageDisplayed() {
		waitForElementVisible(driver, DeleteCustomerPageUI.CUSTOMER_ID_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, DeleteCustomerPageUI.CUSTOMER_ID_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
	}

	public boolean isCustomerIDMustNotContainSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, DeleteCustomerPageUI.CUSTOMER_ID_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, DeleteCustomerPageUI.CUSTOMER_ID_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
	}

	public boolean isCustomerIDMustNotBeginWithSpaceMessageDisplayed() {
		waitForElementVisible(driver, DeleteCustomerPageUI.CUSTOMER_ID_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE);
		return isControlDisplayed(driver, DeleteCustomerPageUI.CUSTOMER_ID_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE);
	}
}
