package bankguru;

public class FundTransferPageUI {
	// Textbox
	public static final String PAYER_ACCOUNT_NO_TEXTBOX = "//input[@name='payersaccount']";
	public static final String PAYEE_ACCOUNT_NO_TEXTBOX = "//input[@name='payeeaccount']";
	public static final String AMOUNT_TEXTBOX = "//input[@name='ammount']";
	public static final String DESCRIPTION_TEXTBOX = "//input[@name='desc']";
	
	// Button
	public static final String SUBMIT_BUTTON = "//input[@name='AccSubmit']";
	
	// Text
	public static final String FUND_TRANSFER_RESULT = "//p[@class='heading3']";
	public static final String AMOUNT_TEXT_IN_TABLE = "//td[text()='Amount']/following-sibling::td";
}
