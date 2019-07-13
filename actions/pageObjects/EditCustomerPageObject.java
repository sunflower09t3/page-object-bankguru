package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import bankguru.EditCustomerPageUI;
import commons.AbstractPage;

public class EditCustomerPageObject extends AbstractPage {
	WebDriver driver;

	public EditCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToCustomerIDTextbox(String value) {
		waitForElementVisible(driver, EditCustomerPageUI.CUSTOMER_ID_TEXTBOX);
		sendKeyToElement(driver, EditCustomerPageUI.CUSTOMER_ID_TEXTBOX, value);
	}

	public void clickSubmitButton() {
		waitForElementVisible(driver, EditCustomerPageUI.SUBMIT_BUTTON);
		clickToElement(driver, EditCustomerPageUI.SUBMIT_BUTTON);
	}

	public void pressTabKey() {
		sendKeyBoardToActiveElement(driver, Keys.TAB);
	}

	public boolean isCustomerIDMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, EditCustomerPageUI.CUSTOMER_ID_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, EditCustomerPageUI.CUSTOMER_ID_MUST_NOT_BLANK_MESSAGE);
	}

	public boolean isCustomerIDMustNotContainCharacterDisplayed() {
		waitForElementVisible(driver, EditCustomerPageUI.CUSTOMER_ID_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, EditCustomerPageUI.CUSTOMER_ID_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
	}

	public boolean isCustomerIDMustNotContainSpecialCharacterMessgaeDisplayed() {
		waitForElementVisible(driver, EditCustomerPageUI.CUSTOMER_ID_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, EditCustomerPageUI.CUSTOMER_ID_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
	}

	public boolean isRedirectedToEditCustomerEntryPage() {
		waitForElementVisible(driver, EditCustomerPageUI.EDIT_CUSTOMER_HEADING);
		return isControlDisplayed(driver, EditCustomerPageUI.EDIT_CUSTOMER_HEADING);
	}

	public void inpuToAddressTextarea(String value) {
		waitForElementVisible(driver, EditCustomerPageUI.ADDRESS_TEXTAREA);
		sendKeyToElement(driver, EditCustomerPageUI.ADDRESS_TEXTAREA, value);
	}

	public boolean isAddressMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, EditCustomerPageUI.ADDRESS_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, EditCustomerPageUI.ADDRESS_MUST_NOT_BLANK_MESSAGE);
	}

	public boolean isCityMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, EditCustomerPageUI.CITY_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, EditCustomerPageUI.CITY_MUST_NOT_BLANK_MESSAGE);
	}

	public void inputToCityTextbox(String value) {
		waitForElementVisible(driver, EditCustomerPageUI.CITY_TEXTBOX);
		sendKeyToElement(driver, EditCustomerPageUI.CITY_TEXTBOX, value);
	}

	public boolean isCityMustNotContainNumberMessageDisplayed() {
		waitForElementVisible(driver, EditCustomerPageUI.CITY_MUST_NOT_CONTAIN_NUMBER_MESSAGE);
		return isControlDisplayed(driver, EditCustomerPageUI.CITY_MUST_NOT_CONTAIN_NUMBER_MESSAGE);
	}

	public boolean isCityMustNotContainSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, EditCustomerPageUI.CITY_MUST_NOT_CONTAIN_SPECIAL_CHARACTER);
		return isControlDisplayed(driver, EditCustomerPageUI.CITY_MUST_NOT_CONTAIN_SPECIAL_CHARACTER);
	}

	public void inputToStateTextbox(String value) {
		waitForElementVisible(driver, EditCustomerPageUI.STATE_TEXTBOX);
		sendKeyToElement(driver, EditCustomerPageUI.STATE_TEXTBOX, value);
	}

	public boolean isStateMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, EditCustomerPageUI.STATE_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, EditCustomerPageUI.STATE_MUST_NOT_BLANK_MESSAGE);
	}

	public boolean isStateMustNotContainSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, EditCustomerPageUI.STATE_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, EditCustomerPageUI.STATE_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
	}

	public void inputToPinTextbox(String value) {
		waitForElementVisible(driver, EditCustomerPageUI.PIN_TEXTBOX);
		sendKeyToElement(driver, EditCustomerPageUI.PIN_TEXTBOX, value);
	}

	public boolean isPinMustNotContainCharacterMessageDisplayed() {
		waitForElementVisible(driver, EditCustomerPageUI.PIN_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, EditCustomerPageUI.PIN_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
	}

	public boolean isPinMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, EditCustomerPageUI.PIN_MUST_NOT_BLANK);
		return isControlDisplayed(driver, EditCustomerPageUI.PIN_MUST_NOT_BLANK);
	}

	public boolean isPinMustBe6DigitsMessageDisplayed() {
		waitForElementVisible(driver, EditCustomerPageUI.PIN_MUST_BE_6_DIGITS_MESSAGE);
		return isControlDisplayed(driver, EditCustomerPageUI.PIN_MUST_BE_6_DIGITS_MESSAGE);
	}

	public String getEnteredTextFromPinTextbox() {
		return getEnteredTextFromTextbox(driver, EditCustomerPageUI.PIN_TEXTBOX);
	}

	public boolean isPinMustNotHaveSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, EditCustomerPageUI.PIN_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, EditCustomerPageUI.PIN_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
	}

	public boolean isStateMustNotContainNumberMessageDisplayed() {
		waitForElementVisible(driver, EditCustomerPageUI.STATE_MUST_NOT_CONTAIN_NUMBER_MESSAGE);
		return isControlDisplayed(driver, EditCustomerPageUI.STATE_MUST_NOT_CONTAIN_NUMBER_MESSAGE);
	}

	public void inputToTelephoneTextbox(String value) {
		waitForElementVisible(driver, EditCustomerPageUI.TELEPHONE_TEXTBOX);
		sendKeyToElement(driver, EditCustomerPageUI.TELEPHONE_TEXTBOX, value);
	}

	public boolean isTelephoneMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, EditCustomerPageUI.TELEPHONE_MUST_NOT_BLANK);
		return isControlDisplayed(driver, EditCustomerPageUI.TELEPHONE_MUST_NOT_BLANK);
	}

	public boolean isTelephoneMustNotContainSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, EditCustomerPageUI.TELEPHONE_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, EditCustomerPageUI.TELEPHONE_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
	}

	public boolean isEmailMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, EditCustomerPageUI.EMAIL_MUST_NOT_BLANK);
		return isControlDisplayed(driver, EditCustomerPageUI.EMAIL_MUST_NOT_BLANK);
	}

	public boolean isEmailInvalidFormatMessageDisplayed() {
		waitForElementVisible(driver, EditCustomerPageUI.EMAIL_INVALID_FORMAT_MESSAGE);
		return isControlDisplayed(driver, EditCustomerPageUI.EMAIL_INVALID_FORMAT_MESSAGE);
	}

	public void inputToEmailTextbox(String value) {
		waitForElementVisible(driver, EditCustomerPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, EditCustomerPageUI.EMAIL_TEXTBOX, value);
	}

}
