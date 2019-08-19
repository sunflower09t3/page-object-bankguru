package bankguru;

public class DepositPageUI {
	// Textbox
	public static final String ACCOUNT_NO_TEXTBOX = "//input[@name='accountno']";
	public static final String AMOUNT_TEXTBOX = "//input[@name='ammount']";
	public static final String DESCRIPTION_TEXTBOX = "//input[@name='desc']";
	
	// Button
	public static final String SUBMIT_BUTTON = "//input[@name='AccSubmit']";
	
	// Text
	public static final String TRANSACTION_RESULT_MESSAGE = "//p[@class='heading3']";
	public static final String CURRENT_BALANCE_TEXT_IN_TABLE = "//td[text()='Current Balance']/following-sibling::td";
}
