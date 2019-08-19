package bankguru;

public class EditAccountPageUI {
	// Textbox
	public static final String ACCOUNT_NO_TEXTBOX = "//input[@name='accountno']";
	
	// Button
	public static final String SUBMIT_BUTTON = "//input[@name='AccSubmit']";
	
	// Dropdown list
	public static final String ACCOUNT_TYPE_DROPDOWN = "//select[@name='a_type']";
	
	// Text
	public static final String ACCOUNT_UPDATE_RESULT = "//p[@class='heading3']";
	public static final String ACCOUNT_TYPE_TEXT_IN_TABLE = "//td[text()='Account Type']/following-sibling::td";
	public static final String EDIT_ACCOUNT_ENTRY_FORM_TEXT = "//p[@class='heading3' and text()='Edit Account Entry Form']";
}
