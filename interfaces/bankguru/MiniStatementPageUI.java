package bankguru;

public class MiniStatementPageUI {
	public static final String ACCOUNT_NO_TEXTBOX = "//input[@name='accountno']";
	public static final String ACCOUNT_NO_MUST_NOT_BLANK_MESSAGE = "//*[@id='message2' and text()='Account Number must not be blank']";
	public static final String ACCOUNT_NO_MUST_NOT_CONTAIN_CHARACTER = "//*[@id='message2' and text()='Characters are not allowed']";
	public static final String ACCOUNT_NO_MUST_NOT_CONTAIN_SPECIAL_CHARACTER = "//*[@id='message2' and text()='Special characters are not allowed']";
}
