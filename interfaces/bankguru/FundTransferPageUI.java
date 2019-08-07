package bankguru;

public class FundTransferPageUI {
	public static final String PAYER_ACCOUNT_NO_TEXTBOX = "//input[@name='payersaccount']";
	public static final String PAYEE_ACCOUNT_NO_TEXTBOX = "//input[@name='payeeaccount']";
	public static final String AMOUNT_TEXTBOX = "//input[@name='ammount']";
	public static final String DESCRIPTION_TEXTBOX = "//input[@name='desc']";
	public static final String SUBMIT_BUTTON = "//input[@name='AccSubmit']";
	public static final String FUND_TRANSFER_DETAILS_MESSAGE = "//p[@class='heading3' and text()='Fund Transfer Details']";
	public static final String AMOUNT_TEXT_IN_TABLE = "//td[text()='Amount']/following-sibling::td";
	public static final String PAYER_ACCOUNT_NO_MUST_NOT_BLANK_MESSAGE = "//*[@id='message10' and text()='Payers Account Number must not be blank']";
	public static final String PAYER_ACCOUNT_NO_MUST_NOT_CONTAIN_CHARACTER_MESSAGE = "//*[@id='message10' and text()='Characters are not allowed']";
	public static final String PAYER_ACCOUNT_NO_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE = "//*[@id='message10' and text()='Special characters are not allowed']";
	public static final String PAYEE_ACCOUNT_NO_MUST_NOT_BLANK_MESSAGE = "//*[@id='message11' and text()='Payees Account Number must not be blank']";
	public static final String PAYEE_ACCOUNT_NO_MUST_NOT_CONTAIN_CHARACTER_MESSAGE = "//*[@id='message11' and text()='Characters are not allowed']";
	public static final String PAYEE_ACCOUNT_NO_MUST_NOT_CONTAIN_SPECIAL_CHARACTER_MESSAGE = "//*[@id='message11' and text()='Special characters are not allowed']";
	public static final String AMOUNT_MUST_NOT_BLANK_MESSAGE = "//*[@id='message1' and text()='Amount field must not be blank']";
	public static final String AMOUNT_MUST_NOT_CONTAIN_CHARACTER_MESSAGE = "//*[@id='message1' and text()='Characters are not allowed']";
	public static final String AMOUNT_MUST_NOT_CONTAIN_SPECIAL_CHARACTER = "//*[@id='message1' and text()='Special characters are not allowed']";
	public static final String DESCRIPTION_MUST_NOT_BLANK_MESSAGE = "//*[@id='message17' and text()='Description can not be blank']";
}
