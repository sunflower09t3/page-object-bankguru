package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import bankguru.NewCustomerPageUI;
import commons.AbstractPage;

public class NewCustomerPageObject extends AbstractPage {
	private WebDriver driver;

	public NewCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToCustomerNameTextbox(String value) {
		waitForElementVisible(driver, NewCustomerPageUI.CUSTOMER_NAME_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.CUSTOMER_NAME_TEXTBOX, value);
	}

	public void pressTabKey() {
		sendKeyBoardToActiveElement(driver, Keys.TAB);
	}

	public boolean isCustomerNameMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.CUSTOMER_NAME_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.CUSTOMER_NAME_MUST_NOT_BLANK_MESSAGE);
	}

	public boolean isCustomerNameMustNotContainNumberMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.CUSTOMER_NAME_MUST_NOT_CONTAIN_NUMBER_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.CUSTOMER_NAME_MUST_NOT_CONTAIN_NUMBER_MESSAGE);
	}

	public boolean isCustomerNameMustNotContainSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.CUSTOMER_NAME_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.CUSTOMER_NAME_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
	}

	public boolean isCustomerNameMustNotBeginWithSpaceMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.CUSTOMER_NAME_MUST_NOT_BEGIN_WITH_SPACE_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.CUSTOMER_NAME_MUST_NOT_BEGIN_WITH_SPACE_CHARACTER_MESSAGE);
	}

	public boolean isAddressMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.ADDRESS_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.ADDRESS_MUST_NOT_BLANK_MESSAGE);
	}

	public void inputToAddressTextbox(String value) {
		waitForElementVisible(driver, NewCustomerPageUI.ADDRESS_TEXT_AREA);
		sendKeyToElement(driver, NewCustomerPageUI.ADDRESS_TEXT_AREA, value);
	}

	public boolean isAddressMustNotBeginWithSpaceMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.ADDRESS_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.ADDRESS_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE);
	}

	public boolean isCityMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.CITY_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.CITY_MUST_NOT_BLANK_MESSAGE);
	}

	public void inputToCityTextbox(String value) {
		waitForElementVisible(driver, NewCustomerPageUI.CITY_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.CITY_TEXTBOX, value);
	}

	public boolean isCityMustNotContainNumberMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.CITY_MUST_NOT_CONTAIN_NUMBER_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.CITY_MUST_NOT_CONTAIN_NUMBER_MESSAGE);
	}

	public boolean isCityMustNotContainSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.CITY_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.CITY_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
	}

	public boolean isCityMustNotBeginWithSpaceMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.CITY_MUST_NOT_BEGIN_WITH_SPACE_NESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.CITY_MUST_NOT_BEGIN_WITH_SPACE_NESSAGE);
	}

	public boolean isStateMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.STATE_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.STATE_MUST_NOT_BLANK_MESSAGE);
	}

	public void inputToStateTextbox(String value) {
		waitForElementVisible(driver, NewCustomerPageUI.STATE_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.STATE_TEXTBOX, value);
	}

	public boolean isStateMustNotContainNumberMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.STATE_MUST_NOT_CONTAIN_NUMBER_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.STATE_MUST_NOT_CONTAIN_NUMBER_MESSAGE);
	}

	public boolean isStateMustNotContainSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.STATE_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.STATE_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
	}

	public boolean isStateMustNotBeginWithSpaceMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.STATE_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.STATE_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE);
	}

	public void inputToPinTextbox(String value) {
		waitForElementVisible(driver, NewCustomerPageUI.PIN_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.PIN_TEXTBOX, value);
	}

	public boolean isPinMustNotContainCharacterMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.PIN_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.PIN_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
	}

	public boolean isPinMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.PIN_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.PIN_MUST_NOT_BLANK_MESSAGE);
	}

	public boolean isPinMustHave6DigitsMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.PIN_MUST_HAVE_6_DIGITS_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.PIN_MUST_HAVE_6_DIGITS_MESSAGE);
	}

	public String getEnteredTextFromPinTextbox() {
		waitForElementVisible(driver, NewCustomerPageUI.PIN_TEXTBOX);
		return getEnteredTextFromTextbox(driver, NewCustomerPageUI.PIN_TEXTBOX);
	}

	public boolean isPinMustNotContainSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.PIN_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.PIN_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
	}

	public boolean isPinMustNotBeginWithSpaceMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.PIN_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.PIN_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE);
	}

	public boolean isTelephoneMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.TELEPHONE_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.TELEPHONE_MUST_NOT_BLANK_MESSAGE);
	}

	public void inputToTelephoneTextbox(String value) {
		waitForElementVisible(driver, NewCustomerPageUI.TELEPHONE_TEXBOX);
		sendKeyToElement(driver, NewCustomerPageUI.TELEPHONE_TEXBOX, value);
	}

	public boolean isTelephoneMustNotBeginWithSpaceMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.TELEPHONE_MUST_NOT_BEGIN_WITH_SPACE);
		return isControlDisplayed(driver, NewCustomerPageUI.TELEPHONE_MUST_NOT_BEGIN_WITH_SPACE);
	}

	public boolean isTelephoneMustNotContainCharacterMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.TELEPHONE_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.TELEPHONE_MUST_NOT_CONTAIN_CHARACTER_MESSAGE);
	}

	public boolean isTelephoneMustNotContainSpecialCharacterMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.TELEPHONE_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.TELEPHONE_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE);
	}

	public void inputToEmailTextbox(String value) {
		waitForElementVisible(driver, NewCustomerPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.EMAIL_TEXTBOX, value);
	}

	public boolean isEmailMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.EMAIL_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.EMAIL_MUST_NOT_BLANK_MESSAGE);
	}

	public boolean isEmailInvalidFormatMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.EMAIL_INVALID_FORMAT_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.EMAIL_INVALID_FORMAT_MESSAGE);
	}

	public boolean isEmailMustNotBeginWithSpaceMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.EMAIL_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.EMAIL_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE);
	}

}
