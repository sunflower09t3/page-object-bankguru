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

	public void inputToCustomerNameTextbox(String customerName) {
		waitForElementVisible(driver, NewCustomerPageUI.CUSTOMER_NAME_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.CUSTOMER_NAME_TEXTBOX, customerName);
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

	public void inputToAddressTextarea(String address) {
		waitForElementVisible(driver, NewCustomerPageUI.ADDRESS_TEXT_AREA);
		sendKeyToElement(driver, NewCustomerPageUI.ADDRESS_TEXT_AREA, address);
	}

	public boolean isAddressMustNotBeginWithSpaceMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.ADDRESS_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.ADDRESS_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE);
	}

	public boolean isCityMustNotBlankMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.CITY_MUST_NOT_BLANK_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.CITY_MUST_NOT_BLANK_MESSAGE);
	}

	public void inputToCityTextbox(String city) {
		waitForElementVisible(driver, NewCustomerPageUI.CITY_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.CITY_TEXTBOX, city);
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

	public void inputToStateTextbox(String state) {
		waitForElementVisible(driver, NewCustomerPageUI.STATE_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.STATE_TEXTBOX, state);
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

	public void inputToPinTextbox(String pin) {
		waitForElementVisible(driver, NewCustomerPageUI.PIN_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.PIN_TEXTBOX, pin);
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

	public void inputToTelephoneTextbox(String telephone) {
		waitForElementVisible(driver, NewCustomerPageUI.TELEPHONE_TEXBOX);
		sendKeyToElement(driver, NewCustomerPageUI.TELEPHONE_TEXBOX, telephone);
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

	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, NewCustomerPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.EMAIL_TEXTBOX, email);
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

	public void inputNothingToCustomerNameTextboxAndPressTabKey() {
		waitForElementVisible(driver, NewCustomerPageUI.CUSTOMER_NAME_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.CUSTOMER_NAME_TEXTBOX, "");
		sendKeyBoardToElement(driver, NewCustomerPageUI.CUSTOMER_NAME_TEXTBOX, Keys.TAB);
	}

	public void inputNothingToCityTextboxAndPressTabKey() {
		waitForElementVisible(driver, NewCustomerPageUI.CITY_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.CITY_TEXTBOX, "");
		sendKeyBoardToElement(driver, NewCustomerPageUI.CITY_TEXTBOX, Keys.TAB);
	}

	public void inputNothingToStateTextboxAndPressTabKey() {
		waitForElementVisible(driver, NewCustomerPageUI.STATE_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.STATE_TEXTBOX, "");
		sendKeyBoardToElement(driver, NewCustomerPageUI.STATE_TEXTBOX, Keys.TAB);
	}

	public void inputNothingToPinTextboxAndPressTabKey() {
		waitForElementVisible(driver, NewCustomerPageUI.PIN_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.PIN_TEXTBOX, "");
		sendKeyBoardToElement(driver, NewCustomerPageUI.PIN_TEXTBOX, Keys.TAB);
	}

	public void inputNothingToTelephoneTextboxAndPressTabKey() {
		waitForElementVisible(driver, NewCustomerPageUI.TELEPHONE_TEXBOX);
		sendKeyToElement(driver, NewCustomerPageUI.TELEPHONE_TEXBOX, "");
		sendKeyBoardToElement(driver, NewCustomerPageUI.TELEPHONE_TEXBOX, Keys.TAB);
	}

	public void inputNothingToAddressTextareaAndPressTabKey() {
		waitForElementVisible(driver, NewCustomerPageUI.ADDRESS_TEXT_AREA);
		sendKeyToElement(driver, NewCustomerPageUI.ADDRESS_TEXT_AREA, "");
		sendKeyBoardToElement(driver, NewCustomerPageUI.ADDRESS_TEXT_AREA, Keys.TAB);
	}

	public void inputNothingToEmailTextboxAndPressTabKey() {
		waitForElementVisible(driver, NewCustomerPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.EMAIL_TEXTBOX, "");
		sendKeyBoardToElement(driver, NewCustomerPageUI.EMAIL_TEXTBOX, Keys.TAB);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, NewCustomerPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.PASSWORD_TEXTBOX, password);
	}

	public void inputToDateOfBirthTextbox(String text) {
		waitForElementVisible(driver, NewCustomerPageUI.DATE_OF_BIRTH_TEXTBOX);
		sendKeyToElement(driver, NewCustomerPageUI.DATE_OF_BIRTH_TEXTBOX, text);
	}

	public void selectMaleGenderRadioButton() {
		waitForElementVisible(driver, NewCustomerPageUI.MALE_GENDER_RADIO_BUTTON);
		if(!isControlSelected(driver, NewCustomerPageUI.MALE_GENDER_RADIO_BUTTON))
			clickToElement(driver, NewCustomerPageUI.MALE_GENDER_RADIO_BUTTON);
	}

	public void selectFemaleGenderRadioButton() {
		waitForElementVisible(driver, NewCustomerPageUI.FEMALE_GENDER_RADIO_BUTTON);
		if(!isControlSelected(driver, NewCustomerPageUI.FEMALE_GENDER_RADIO_BUTTON))
			clickToElement(driver, NewCustomerPageUI.FEMALE_GENDER_RADIO_BUTTON);
	}

	public void clickSubmitButton() {
		waitForElementVisible(driver, NewCustomerPageUI.SUBMIT_BUTTON);
		clickToElement(driver, NewCustomerPageUI.SUBMIT_BUTTON);
	}

	public boolean isCustomerRegisteredSuccessfulMessageDisplayed() {
		waitForElementVisible(driver, NewCustomerPageUI.CUSTOMER_REGISTERED_SUCCESSFUL_MESSAGE);
		return isControlDisplayed(driver, NewCustomerPageUI.CUSTOMER_REGISTERED_SUCCESSFUL_MESSAGE);
	}

	public String getCustomerID() {
		waitForElementVisible(driver, NewCustomerPageUI.CUSTOMER_ID_TEXT_IN_TABLE);
		return getTextElement(driver, NewCustomerPageUI.CUSTOMER_ID_TEXT_IN_TABLE);
	}

}
