package bankguru;

public class NewCustomerPageUI {
	public static final String CUSTOMER_NAME_TEXTBOX = "//input[@name='name']";
	public static final String CUSTOMER_NAME_MUST_NOT_BLANK_MESSAGE = "//label[@id='message' and text()='Customer name must not be blank']";
	public static final String CUSTOMER_NAME_MUST_NOT_CONTAIN_NUMBER_MESSAGE = "//*[@id='message' and text()='Numbers are not allowed']";
	public static final String CUSTOMER_NAME_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE = "//*[@id='message' and text()='Special characters are not allowed']";
	public static final String CUSTOMER_NAME_MUST_NOT_BEGIN_WITH_SPACE_CHARACTER_MESSAGE = "//*[@id='message' and text()='First character can not have space']";
	public static final String ADDRESS_TEXT_AREA = "//textarea[@name='addr']";
	public static final String ADDRESS_MUST_NOT_BLANK_MESSAGE = "//*[@id='message3' and text()='Address Field must not be blank']";
	public static final String ADDRESS_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE = "//*[@id='message3' and text()='First character can not have space']";
	public static final String CITY_TEXTBOX = "//input[@name='city']";
	public static final String CITY_MUST_NOT_BLANK_MESSAGE = "//*[@id='message4' and text()='City Field must not be blank']";
	public static final String CITY_MUST_NOT_CONTAIN_NUMBER_MESSAGE = "//*[@id='message4' and text()='Numbers are not allowed']";
	public static final String CITY_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE = "//*[@id='message4' and text()='Special characters are not allowed']";
	public static final String CITY_MUST_NOT_BEGIN_WITH_SPACE_NESSAGE = "//*[@id='message4' and text()='First character can not have space']";
	public static final String STATE_TEXTBOX = "//input[@name='state']";
	public static final String STATE_MUST_NOT_BLANK_MESSAGE = "//*[@id='message5' and text()='State must not be blank']";
	public static final String STATE_MUST_NOT_CONTAIN_NUMBER_MESSAGE = "//*[@id='message5' and text()='Numbers are not allowed']";
	public static final String STATE_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE = "//*[@id='message5' and text()='Special characters are not allowed']";
	public static final String STATE_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE = "//*[@id='message5' and text()='First character can not have space']";
	public static final String PIN_TEXTBOX = "//input[@name='pinno']";
	public static final String PIN_MUST_NOT_CONTAIN_CHARACTER_MESSAGE = "//*[@id='message6' and text()='Characters are not allowed']";
	public static final String PIN_MUST_NOT_BLANK_MESSAGE = "//*[@id='message6' and text()='PIN Code must not be blank']";
	public static final String PIN_MUST_HAVE_6_DIGITS_MESSAGE = "//*[@id='message6' and text()='PIN Code must have 6 Digits']";
	public static final String PIN_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE = "//*[@id='message6' and text()='Special characters are not allowed']";
	public static final String PIN_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE = "//*[@id='message6' and text()='First character can not have space']";
	public static final String TELEPHONE_TEXBOX = "//input[@name='telephoneno']";
	public static final String TELEPHONE_MUST_NOT_BLANK_MESSAGE = "//*[@id='message7' and text()='Mobile no must not be blank']";
	public static final String TELEPHONE_MUST_NOT_BEGIN_WITH_SPACE = "//*[@id='message7' and text()='First character can not have space']";
	public static final String TELEPHONE_MUST_NOT_CONTAIN_CHARACTER_MESSAGE = "//*[@id='message7' and text()='Characters are not allowed']";
	public static final String TELEPHONE_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE = "//*[@id='message7' and text()='Special characters are not allowed']";
	public static final String EMAIL_TEXTBOX = "//input[@name='emailid']";
	public static final String EMAIL_MUST_NOT_BLANK_MESSAGE = "//*[@id='message9' and text()='Email-ID must not be blank']";
	public static final String EMAIL_INVALID_FORMAT_MESSAGE = "//*[@id='message9' and text()='Email-ID is not valid']";
	public static final String EMAIL_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE = "//*[@id='message9' and text()='First character can not have space']";
}
