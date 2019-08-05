package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import bankguru.NewAccountPageUI;
import commons.AbstractPage;

public class NewAccountPageObject extends AbstractPage{
	private WebDriver driver;

	public NewAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToCustomerIDTextbox(String customerID) {
		waitForElementVisible(driver, NewAccountPageUI.CUSTOMER_ID_TEXTBOX);
		sendKeyToElement(driver, NewAccountPageUI.CUSTOMER_ID_TEXTBOX, customerID);
	}

	public void selectAccountType(String accountType) {
		waitForElementVisible(driver, NewAccountPageUI.ACCOUNT_TYPE_DROPDOWN);
		selectItemInHtmlDropdownByVisibleTex(driver, NewAccountPageUI.ACCOUNT_TYPE_DROPDOWN, accountType);
	}

	public void inputToInitialDepositTextbox(String initialDeposit) {
		waitForElementVisible(driver, NewAccountPageUI.INITIAL_DEPOSIT_TEXTBOX);
		sendKeyToElement(driver, NewAccountPageUI.INITIAL_DEPOSIT_TEXTBOX, initialDeposit);
	}

	public void clickSubmitButton() {
		waitForElementVisible(driver, NewAccountPageUI.SUBMIT_BUTTON);
		clickToElement(driver, NewAccountPageUI.SUBMIT_BUTTON);
	}

	public boolean isAccountGeneratedSuccessfulMessageDisplayed() {
		waitForElementVisible(driver, NewAccountPageUI.ACCOUNT_GENERATED_SUCCESSFUL_MESSAGE);
		return isControlDisplayed(driver, NewAccountPageUI.ACCOUNT_GENERATED_SUCCESSFUL_MESSAGE);
	}

	public String getAccountID() {
		waitForElementVisible(driver, NewAccountPageUI.ACCOUNT_ID_TEXT_IN_TABLE);
		return getTextElement(driver, NewAccountPageUI.ACCOUNT_ID_TEXT_IN_TABLE);
	}

	public String getCurrentAmount() {
		waitForElementVisible(driver, NewAccountPageUI.CURRENT_AMOUNT_TEXT_IN_TABLE);
		return getTextElement(driver, NewAccountPageUI.CURRENT_AMOUNT_TEXT_IN_TABLE);
	}

	public void inputNothingToCustomerIDTextboxAndPressTabKey() {
		waitForElementVisible(driver, NewAccountPageUI.CUSTOMER_ID_TEXTBOX);
		sendKeyToElement(driver, NewAccountPageUI.CUSTOMER_ID_TEXTBOX, "");
		sendKeyBoardToElement(driver, NewAccountPageUI.CUSTOMER_ID_TEXTBOX, Keys.TAB);
	}

	public boolean isCustomerIDMustNoBlankMessageDisplayed() {
		waitForElementVisible(driver, NewAccountPageUI.CUSTOMER_ID_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, NewAccountPageUI.CUSTOMER_ID_MUST_NOT_BLANK_MESSAGE);
	}

	public boolean isCustomerIDMustNotContainCharacterMessageDisplayed() {
		waitForElementVisible(driver, NewAccountPageUI.CUSTOMER_ID_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, NewAccountPageUI.CUSTOMER_ID_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
	}

	public boolean isCustomerIDMustNotContainSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, NewAccountPageUI.CUSTOMER_ID_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, NewAccountPageUI.CUSTOMER_ID_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
	}

	public boolean isCustomerIDMustNotBeginWithSpaceMessageDisplayed() {
		waitForElementVisible(driver, NewAccountPageUI.CUSTOMER_ID_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE);
		return isControlDisplayed(driver, NewAccountPageUI.CUSTOMER_ID_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE);
	}

	public void inputNothingToInitialDepositTextboxAndPressTabKey() {
		waitForElementVisible(driver, NewAccountPageUI.INITIAL_DEPOSIT_TEXTBOX);
		sendKeyToElement(driver, NewAccountPageUI.INITIAL_DEPOSIT_TEXTBOX, "");
		sendKeyBoardToElement(driver, NewAccountPageUI.INITIAL_DEPOSIT_TEXTBOX, Keys.TAB);
	}

	public boolean isInitialDepositMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, NewAccountPageUI.INITIAL_DEPOSIT_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, NewAccountPageUI.INITIAL_DEPOSIT_MUST_NOT_BLANK_MESSAGE);
	}

	public boolean isInitialDepositMustNotContainCharacterMessageDisplayed() {
		waitForElementVisible(driver, NewAccountPageUI.INITIAL_DEPOSIT_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, NewAccountPageUI.INITIAL_DEPOSIT_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
	}

	public boolean isInitialDepositMustNotContainSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, NewAccountPageUI.INITIAL_DEPOSIT_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, NewAccountPageUI.INITIAL_DEPOSIT_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
	}

	public boolean isInitialDepositMustNotBeginWithSpaceMessageDisplayed() {
		waitForElementVisible(driver, NewAccountPageUI.INITIAL_DEPOSIT_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE);
		return isControlDisplayed(driver, NewAccountPageUI.INITIAL_DEPOSIT_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE);
	}
}
