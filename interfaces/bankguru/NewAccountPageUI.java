package bankguru;

public class NewAccountPageUI {
	public static final String CUSTOMER_ID_TEXTBOX = "//input[@name='cusid']";
	public static final String ACCOUNT_TYPE_DROPDOWN = "//select[@name='selaccount']";
	public static final String INITIAL_DEPOSIT_TEXTBOX = "//input[@name='inideposit']";
	public static final String SUBMIT_BUTTON = "//input[@name='button2']";
	public static final String ACCOUNT_GENERATED_SUCCESSFUL_MESSAGE = "//p[@class='heading3' and text()='Account Generated Successfully!!!']";
	public static final String ACCOUNT_ID_TEXT_IN_TABLE = "//td[text()='Account ID']/following-sibling::td";
	public static final String CURRENT_AMOUNT_TEXT_IN_TABLE = "//td[text()='Current Amount']/following-sibling::td";
	public static final String CUSTOMER_ID_MUST_NOT_BLANK_MESSAGE = "//*[@id='message14' and text()='Customer ID is required']";
	public static final String CUSTOMER_ID_MUST_NOT_CONTAIN_CHARACTER_MESSAGE = "//*[@id='message14' and text()='Characters are not allowed']";
	public static final String CUSTOMER_ID_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE = "//*[@id='message14' and text()='Special characters are not allowed']";
	public static final String CUSTOMER_ID_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE = "//*[@id='message14' and text()='First character can not have space']";
	public static final String INITIAL_DEPOSIT_MUST_NOT_BLANK_MESSAGE = "//*[@id='message19' and text()='Initial Deposit must not be blank']";
	public static final String INITIAL_DEPOSIT_MUST_NOT_CONTAIN_CHARACTER_MESSAGE = "//*[@id='message19' and text()='Characters are not allowed']";
	public static final String INITIAL_DEPOSIT_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE = "//*[@id='message19' and text()='Special characters are not allowed']";
	public static final String INITIAL_DEPOSIT_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE = "//*[@id='message19' and text()='First character can not have space']";
}
