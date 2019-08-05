package bankguru;

public class EditCustomerPageUI {
	public static final String CUSTOMER_ID_TEXTBOX = "//input[@name='cusid']";
	public static final String ACC_SUBMIT_BUTTON = "//input[@name='AccSubmit']";
	public static final String SUBMIT_BUTTON = "//input[@name='sub']";
	public static final String CUSTOMER_ID_MUST_NOT_BLANK_MESSAGE = "//*[@id='message14' and text()='Customer ID is required']";
	public static final String CUSTOMER_ID_MUST_NOT_CONTAIN_CHARACTER_MESSAGE = "//*[@id='message14' and text()='Characters are not allowed']";
	public static final String CUSTOMER_ID_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE = "//*[@id='message14' and text()='Special characters are not allowed']";
	public static final String EDIT_CUSTOMER_HEADING = "//p[@class='heading3' and text()='Edit Customer']";
	public static final String ADDRESS_TEXTAREA = "//textarea[@name='addr']";
	public static final String ADDRESS_MUST_NOT_BLANK_MESSAGE = "//*[@id='message3' and text()='Address Field must not be blank']";
	public static final String CITY_TEXTBOX = "//input[@name='city']";
	public static final String CITY_MUST_NOT_BLANK_MESSAGE = "//*[@id='message4' and text()='City Field must not be blank']";
	public static final String CITY_MUST_NOT_CONTAIN_NUMBER_MESSAGE = "//*[@id='message4' and text()='Numbers are not allowed']";
	public static final String CITY_MUST_NOT_CONTAIN_SPECIAL_CHARACTER = "//*[@id='message4' and text()='Special characters are not allowed']";
	public static final String STATE_TEXTBOX = "//input[@name='state']";
	public static final String STATE_MUST_NOT_BLANK_MESSAGE = "//*[@id='message5' and text()='State must not be blank']";
	public static final String STATE_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE = "//*[@id='message5' and text()='Special characters are not allowed']";
	public static final String STATE_MUST_NOT_CONTAIN_NUMBER_MESSAGE = "//*[@id='message5' and text()='Numbers are not allowed']";
	public static final String PIN_TEXTBOX = "//input[@name='pinno']";
	public static final String PIN_MUST_NOT_CONTAIN_CHARACTER_MESSAGE = "//*[@id ='message6' and text()='Characters are not allowed']";
	public static final String PIN_MUST_NOT_BLANK = "//*[@id='message6' and text()='PIN Code must not be blank']";
	public static final String PIN_MUST_BE_6_DIGITS_MESSAGE = "//*[@id='message6' and text()='PIN Code must have 6 Digits']";
	public static final String PIN_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE = "//*[@id='message6' and text()='Special characters are not allowed']";
	public static final String TELEPHONE_TEXTBOX = "//input[@name='telephoneno']";
	public static final String TELEPHONE_MUST_NOT_BLANK = "//*[@id='message7' and text()='Mobile no must not be blank']";
	public static final String TELEPHONE_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE = "//*[@id='message7' and text()='Special characters are not allowed']";
	public static final String EMAIL_TEXTBOX = "//input[@name='emailid']";
	public static final String EMAIL_MUST_NOT_BLANK = "//*[@id='message9' and text()='Email-ID must not be blank']";
	public static final String EMAIL_INVALID_FORMAT_MESSAGE = "//*[@id='message9' and text()='Email-ID is not valid']";
	public static final String CUSTOMER_UPDATED_SUCCESSFUL_MESSAGE = "//P[@class='heading3' and text()='Customer details updated Successfully!!!']";
}
