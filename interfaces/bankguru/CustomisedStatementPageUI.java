package bankguru;

public class CustomisedStatementPageUI {
	public static final String ACCOUNT_NO_TEXTBOX = "//input[@name='accountno']";
	public static final String MINIMUM_TRANSACTION_VALUE_TEXTBOX = "//input[@name='amountlowerlimit']";
	public static final String NUMBER_OF_TRANSACTION_TEXTBOX = "//input[@name='numtransaction']";
	public static final String ACCOUNT_NO_MUST_NOT_BLANK_MESSAGE = "//*[@id='message2' and text()='Account Number must not be blank']";
	public static final String ACCOUNT_NO_MUST_NOT_CONTAIN_CHARACTER_MESSAGE = "//*[@id='message2' and text()='Characters are not allowed']";
	public static final String ACCOUNT_NO_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE = "//*[@id='message2' and text()='Special characters are not allowed']";
	public static final String MINIMUM_TRANSACTION_VALUE_MUST_NOT_CONTAIN_CHARACTER_MESSAGE = "//*[@id='message12' and text()='Characters are not allowed']";
	public static final String MINIMUM_TRANSACTION_VALUE_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE = "//*[@id='message12' and text()='Special characters are not allowed']";
	public static final String NUMBER_OF_TRANSACTION_MUST_NOT_CONTAIN_CHARACTER_MESSAGE = "//*[@id='message13' and text()='Characters are not allowed']";
	public static final String NUMBER_OF_TRANSACTION_MUST_NOT_CONTAIN_SPECIAL_CHARACTER = "//*[@id='message13' and text()='Special characters are not allowed']";
}
