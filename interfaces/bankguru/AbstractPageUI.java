package bankguru;

public class AbstractPageUI {
	public static final String DYNAMIC_MENU_LINK = "//a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX = "//input[@name='%s']";
	public static final String DYNAMIC_TEXTAREA = "//textarea[@name='%s']";
	public static final String DYNAMIC_BUTTON = "//input[@name='%s']";
	public static final String DYNAMIC_ERROR_MESSAGE_OF_FIELD = "//td[normalize-space(text())='%s']/following-sibling::td/label";
	public static final String DYNAMIC_RADIO_BUTTON = "//input[@value='%s']";
}
