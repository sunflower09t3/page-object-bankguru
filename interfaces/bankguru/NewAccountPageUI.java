package bankguru;

public class NewAccountPageUI {
	// Textbox
	public static final String CUSTOMER_ID_TEXTBOX = "//input[@name='cusid']";
	public static final String INITIAL_DEPOSIT_TEXTBOX = "//input[@name='inideposit']";
	
	// Button
	public static final String SUBMIT_BUTTON = "//input[@name='button2']";
	
	// Dropdown list
	public static final String ACCOUNT_TYPE_DROPDOWN = "//select[@name='selaccount']";
	
	// Text
	public static final String ADD_ACCOUNT_RESULT = "//p[@class='heading3']";
	public static final String ACCOUNT_ID_TEXT_IN_TABLE = "//td[text()='Account ID']/following-sibling::td";
	public static final String CURRENT_AMOUNT_TEXT_IN_TABLE = "//td[text()='Current Amount']/following-sibling::td";
}