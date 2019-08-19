package bankguru;

public class BalanceEnquiryPageUI {
	// Textbox
	public static final String ACCOUNT_NO_TEXTBOX = "//input[@name='accountno']";
	
	// Button
	public static final String SUBMIT_BUTTON = "//input[@name='AccSubmit']";
	
	// Text
	public static final String BALANCE_DETAILS_MESSAGE = "//p[@class='heading3']";
	public static final String BALANCE_TEXT_IN_TABLE = "//td[text()='Balance']/following-sibling::td";
}
