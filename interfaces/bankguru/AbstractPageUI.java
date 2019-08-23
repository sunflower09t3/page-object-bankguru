package bankguru;

public class AbstractPageUI {
	public static final String DYNAMIC_MENU_LINK = "//a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX = "//input[@name='%s']";
	public static final String DYNAMIC_TEXTAREA = "//textarea[@name='%s']";
	public static final String DYNAMIC_BUTTON = "//input[@name='%s']";
	public static final String DYNAMIC_ERROR_MESSAGE_OF_FIELD = "//td[normalize-space(text())='%s']/following-sibling::td/label";
	public static final String DYNAMIC_RADIO_BUTTON = "//input[@value='%s']";
	public static final String DYNAMIC_PAGE_NAME_OR_TABLE_HEADER_MESSAGE = "//p[@class='heading3' and text()='%s']";
	public static final String DYNAMIC_DATA_IN_TABLE = "//td[text()='%s']/following-sibling::td";
	public static final String DYNAMIC_DROPDOWN_LIST = "//select[@name='%s']";
}
