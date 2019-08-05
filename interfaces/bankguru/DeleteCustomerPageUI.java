package bankguru;

public class DeleteCustomerPageUI {
	public static final String CUSTOMER_ID_TEXTBOX = "//input[@name='cusid']";
	public static final String SUBMIT_BUTTON = "//input[@name='AccSubmit']";
	public static final String CUSTOMER_ID_MUST_NOT_BE_BLANK_MESSAGE = "//*[@id='message14' and text()='Customer ID is required']";
	public static final String CUSTOMER_ID_MUST_NOT_CONTAIN_CHARACTER_MESSAGE = "//*[@id='message14' and text()='Characters are not allowed']";
	public static final String CUSTOMER_ID_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE = "//*[@id='message14' and text()='Special characters are not allowed']";
	public static final String CUSTOMER_ID_MUST_NOT_BEGIN_WITH_SPACE_MESSAGE = "//*[@id='message14' and text()='First character can not have space']";
}
