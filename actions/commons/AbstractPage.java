package commons;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import bankguru.AbstractPageUI;
import bankguru.NewCustomerPageUI;

public class AbstractPage {
	private WebElement element;
	private List<WebElement> elements;
	private Select select;
	private Set<String> allWindows;
	private Actions action;
	private JavascriptExecutor javascriptExecutor;
	private WebDriverWait explicitWait;
	private By byLocator;

	/* Web Browser */
	public void openURL(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentPageURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getCurrentPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void navigateBack(WebDriver driver) {
		driver.navigate().back();
	}

	public void navigateForward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText().trim();
	}

	public void sendKeyToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	/* Web Element */
	public void clickToElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void clickToElement(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void sendKeyToElement(WebDriver driver, String locator, String text) {
		element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(text);
	}

	public void sendKeyToElement(WebDriver driver, String locator, String text, String... dynamicValuesOfLocator) {
		locator = String.format(locator, (Object[]) dynamicValuesOfLocator);
		element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(text);
	}

	public void clearDataElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		element.clear();
	}

	public void selectItemInHtmlDropdownByValue(WebDriver driver, String locator, String valueToSelect) {
		element = driver.findElement(By.xpath(locator));
		select = new Select(element);
		select.selectByValue(valueToSelect);
	}

	public void selectItemInHtmlDropdownByValue(WebDriver driver, String locator, String valueToSelect, String... dynamicValuesOfLocator) {
		locator = String.format(locator, (Object[]) dynamicValuesOfLocator);
		element = driver.findElement(By.xpath(locator));
		select = new Select(element);
		select.selectByValue(valueToSelect);
	}

	public void selectItemInHtmlDropdownByVisibleText(WebDriver driver, String locator, String textToSelect) {
		element = driver.findElement(By.xpath(locator));
		select = new Select(element);
		select.selectByVisibleText(textToSelect);
	}

	public void selectItemInHtmlDropdownByVisibleText(WebDriver driver, String locator, String textToSelect, String... dynamicValuesOfLocator) {
		locator = String.format(locator, (Object[]) dynamicValuesOfLocator);
		element = driver.findElement(By.xpath(locator));
		select = new Select(element);
		select.selectByVisibleText(textToSelect);
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String allOptionsLocator, String value) {
		element = driver.findElement(By.xpath(parentLocator));
		javascriptExecutor = (JavascriptExecutor) driver;

		if (element.isDisplayed()) {
			element.click();
		} else {
			javascriptExecutor.executeScript("arguments[0].scrollIntoView[0]", element);
			javascriptExecutor.executeScript("arguments[0].click()", element);
		}

		elements = driver.findElements(By.xpath(allOptionsLocator));

		explicitWait = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(elements));

		for (WebElement childElement : elements) {
			if (childElement.getText().equals(value)) {
				if (childElement.isDisplayed()) {
					childElement.click();
				} else {
					javascriptExecutor.executeScript("arguments[0].scrollIntoView[0]", childElement);
					javascriptExecutor.executeScript("arguments[0].click()", childElement);
				}

				break;
			}
		}
	}

	public WebElement getSelectedItemInHtmlDropdown(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		select = new Select(element);
		return select.getFirstSelectedOption();

	}

	public WebElement getCurrentSelectedItemInHtmlDropdown(WebDriver driver, String locator, String... dynamicValuesOfLocator) {
		locator = String.format(locator, (Object[]) dynamicValuesOfLocator);
		element = driver.findElement(By.xpath(locator));
		select = new Select(element);
		return select.getFirstSelectedOption();

	}

	public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
		element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attributeName);
	}

	public String getAttributeValue(WebDriver driver, String locator, String attributeName, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attributeName);
	}

	public String getTextElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.getText().trim();
	}

	public String getTextElement(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		element = driver.findElement(By.xpath(locator));
		return element.getText().trim();
	}

	public int countElementNumber(WebDriver driver, String locator) {
		elements = driver.findElements(By.xpath(locator));
		return elements.size();
	}

	public void checkTheCheckbox(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		if (!element.isSelected())
			element.click();
	}

	public void uncheckTheCheckbox(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		if (element.isSelected())
			element.click();
	}

	public boolean isControlDisplayed(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public boolean isControlDisplayed(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public boolean isControlUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, Constants.SHORT_TIMEOUT);
		elements = driver.findElements(By.xpath(locator));
		overrideGlobalTimeout(driver, Constants.LONG_TIMEOUT);

		// Elelment is not present in DOM
		if (elements.size() == 0) {
			return true;
		}
		// Element is present in DOM but NOT visible
		else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		}
		// Element is present in DOM and visible
		else {
			return false;
		}
	}

	public boolean isControlUndisplayed(WebDriver driver, String locator, String... dynanicValues) {
		overrideGlobalTimeout(driver, Constants.SHORT_TIMEOUT);
		locator = String.format(locator, (Object[]) dynanicValues);
		elements = driver.findElements(By.xpath(locator));
		overrideGlobalTimeout(driver, Constants.LONG_TIMEOUT);

		// Elelment is not present in DOM
		if (elements.size() == 0) {
			return true;
		}
		// Element is present in DOM but NOT visible
		else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		}
		// Element is present in DOM and visible
		else {
			return false;
		}
	}

	public void overrideGlobalTimeout(WebDriver driver, int timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public boolean isControlSelected(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}

	public boolean isControlSelected(WebDriver driver, String locator, String... dynamicValuesOfLocator) {
		locator = String.format(locator, (Object[]) dynamicValuesOfLocator);
		element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}

	public boolean isControlEnabled(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.isEnabled();
	}

	public void switchWindowByID(WebDriver driver, String windowID) {
		allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (window.equals(windowID)) {
				driver.switchTo().window(windowID);
				break;
			}
		}
	}

	public void switchWindowByTitle(WebDriver driver, String title) {
		allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
			if (driver.getTitle().equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentWindow) {
		allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
	}

	public void switchToIframeByID(WebDriver driver, String ID) {
		driver.switchTo().frame(ID);
	}

	public void switchToIframeByName(WebDriver driver, String name) {
		driver.switchTo().frame(name);
	}

	public void switchToIframeByWebElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		driver.switchTo().frame(element);
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public void rightClickOnElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		action = new Actions(driver);
		action.contextClick(element).perform();
	}

	public void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
		WebElement sourceElement = driver.findElement(By.xpath(sourceLocator));
		WebElement targetElement = driver.findElement(By.xpath(targetLocator));
		action = new Actions(driver);
		action.dragAndDrop(sourceElement, targetElement).perform();
	}

	public void sendKeyBoardToElement(WebDriver driver, String locator, CharSequence keys) {
		element = driver.findElement(By.xpath(locator));
		action = new Actions(driver);
		action.sendKeys(element, keys).perform();
	}

	public void sendKeyBoardToElement(WebDriver driver, String locator, CharSequence keys, String... dynamicValuesOfLocator) {
		locator = String.format(locator, (Object[]) dynamicValuesOfLocator);
		element = driver.findElement(By.xpath(locator));
		
		// Fix MoveTargetOutOfBoundsException in Firefox & Edge
		if(driver.toString().contains("firefox") || driver.toString().contains("MicrosoftEdge")) {
			scrollToElement(driver, locator);
			try {
				Thread.sleep(500);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		action = new Actions(driver);
		action.sendKeys(element, keys).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("window.scrollBy(0, document.body.scrollHeight)");
	}

	public void scrollToElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void highlightElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].style.border='2px groove green'", element);
	}

	public void removeAttributeOfElement(WebDriver driver, String locator, String attributeName) {
		element = driver.findElement(By.xpath(locator));
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].removeAttribute('" + attributeName + "')", element);
	}
	
	public void removeAttributeOfElement(WebDriver driver, String locator, String attributeName, String ...dynamicValuesOfLocator) {
		locator = String.format(locator, (Object[])dynamicValuesOfLocator);
		element = driver.findElement(By.xpath(locator));
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].removeAttribute('" + attributeName + "')", element);
	}

	public void executeJavascriptToBrowser(WebDriver driver, String javaScript) {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript(javaScript);
	}

	public void executeJavascriptToElement(WebDriver driver, String locator, String javaScript) {
		element = driver.findElement(By.xpath(locator));
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript(javaScript, element);
	}

	public boolean checkImageLoaded(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		javascriptExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) javascriptExecutor.executeScript("return arguments[0].complete " + "&& typeof arguments[0].naturalWidth!=\"undefined\" && arguments[0].naturalWidth > 0", element);
		return status;
	}

	public boolean verifyTextInInnerText(WebDriver driver, String expectedText) {
		javascriptExecutor = (JavascriptExecutor) driver;
		String actualText = (String) javascriptExecutor.executeScript("return document.documentElement.innerText.match('" + expectedText + "')[0]");
		return actualText.equals(expectedText);

	}

	public void waitForElementPresent(WebDriver driver, String locator) {
		byLocator = By.xpath(locator);
		explicitWait = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		byLocator = By.xpath(locator);
		explicitWait = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		byLocator = By.xpath(locator);
		explicitWait = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		byLocator = By.xpath(locator);
		explicitWait = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byLocator));
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		byLocator = By.xpath(locator);
		explicitWait = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
		overrideGlobalTimeout(driver, Constants.SHORT_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
		overrideGlobalTimeout(driver, Constants.LONG_TIMEOUT);
	}

	public void waitForAlertPresent(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void uploadFiles(WebDriver driver, String selectFileButtonXpath, String uploadFileButtonXpath, String[] files) {

		String allFilesPath = "";
		for (int i = 0; i < files.length; i++) {
			if (i < files.length - 1)
				allFilesPath = allFilesPath.concat(files[i]).concat("\n");
			else
				allFilesPath = allFilesPath.concat(files[i]);
		}

		element = driver.findElement(By.xpath(selectFileButtonXpath));
		element.sendKeys(allFilesPath);
		element = driver.findElement(By.xpath(uploadFileButtonXpath));
		element.click();
	}

	public void uploadFilesByRobotClass(WebDriver driver, String selectFileButton, String uploadFileButtonXpath, String[] files) throws InterruptedException, AWTException {
		Clipboard clipboard;
		StringSelection strSelection;

		element = driver.findElement(By.xpath(selectFileButton));

		for (String filePath : files) {
			element.click();

			clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			strSelection = new StringSelection(filePath);
			clipboard.setContents(strSelection, null);

			Robot robot = new Robot();
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
		}

		element = driver.findElement(By.xpath(uploadFileButtonXpath));
		element.click();
	}

	// switch page functions

	// 1st way: Initialize and return PageObject at AbstractPage class.
	// Apply when number of pages is 10-20 pages
	/*
	 * public AbstractPage openMultiplePage(WebDriver driver, String pageName) {
	 * waitForElementVisible(driver, AbstractPageUI.DYNAMIC_MENU_LINK, pageName);
	 * clickToElement(driver, AbstractPageUI.DYNAMIC_MENU_LINK, pageName);
	 * 
	 * switch (pageName) { case "New Customer": return
	 * PageGeneratorManager.getNewCustomerPage(driver); case "Edit Customer": return
	 * PageGeneratorManager.getEditCustomerPage(driver); case "Delete Customer":
	 * return PageGeneratorManager.getDeleteCustomerPage(driver); case
	 * "New Account": return PageGeneratorManager.getNewAccountPage(driver); case
	 * "Edit Account": return PageGeneratorManager.getEditAccountPage(driver); case
	 * "Delete Account": return PageGeneratorManager.getDeleteAccountpage(driver);
	 * case "Deposit": return PageGeneratorManager.getDepositPage(driver); case
	 * "WithDrawal": return PageGeneratorManager.getWithdrawlPage(driver); case
	 * "Fund Transfer": return PageGeneratorManager.getFunTransferPage(driver); case
	 * "Change Password": return PageGeneratorManager.getChangePasswordPage(driver);
	 * case "Balance Enquiry": return
	 * PageGeneratorManager.getBalanceEnquiryPage(driver); case "Mini Statement":
	 * return PageGeneratorManager.getMiniStatementPage(driver); case
	 * "Customised Statement": return
	 * PageGeneratorManager.getCustomisedStatementPage(driver); case "Log out":
	 * return PageGeneratorManager.getLogoutPage(driver); default: return
	 * PageGeneratorManager.getHomePage(driver); } }
	 */

	// 2nd way: Initialize PageObject at test case layer.
	// Apply when there are lots of pages (100-1000 pages)
	public void openMultiplePage(WebDriver driver, String pageName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_MENU_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_MENU_LINK, pageName);
	}

	public void pressTabToDynamicTextbox(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX, fieldName);
		sendKeyBoardToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX, Keys.TAB, fieldName);
	}

	public void pressTabToDynamicTextarea(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTAREA, fieldName);
		sendKeyBoardToElement(driver, AbstractPageUI.DYNAMIC_TEXTAREA, Keys.TAB, fieldName);
	}

	public void inputToDynamicTextbox(WebDriver driver, String fieldName, String text) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX, fieldName);
		
		if(fieldName.equals("dob")) {
			if(driver.toString().contains("chrome") || driver.toString().contains("MicrosoftEdge")) {
				removeAttributeOfElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX, "type", fieldName);
			}
		}
		sendKeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX, text, fieldName);
	}

	public void inputToDynamicTextarea(WebDriver driver, String fieldName, String text) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTAREA, fieldName);
		sendKeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTAREA, text, fieldName);
	}

	public void clickDynamicButton(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_BUTTON, fieldName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_BUTTON, fieldName);
	}

	public String getErrorMessageOfDynamicField(WebDriver driver, String fieldLable) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_ERROR_MESSAGE_OF_FIELD, fieldLable);
		return getTextElement(driver, AbstractPageUI.DYNAMIC_ERROR_MESSAGE_OF_FIELD, fieldLable);
	}

	public void selectDynamicRadioButton(WebDriver driver, String valueToSelect) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_RADIO_BUTTON, valueToSelect);
		if (!isControlSelected(driver, AbstractPageUI.DYNAMIC_RADIO_BUTTON, valueToSelect)) {
			clickToElement(driver, AbstractPageUI.DYNAMIC_RADIO_BUTTON, valueToSelect);
		}
	}

	public boolean isPageTitleOrTableHeaderMessageDisplayed(WebDriver driver, String message) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_PAGE_NAME_OR_TABLE_HEADER_MESSAGE, message);
		return isControlDisplayed(driver, AbstractPageUI.DYNAMIC_PAGE_NAME_OR_TABLE_HEADER_MESSAGE, message);
	}

	public String getDanymicDataInTable(WebDriver driver, String rowName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_DATA_IN_TABLE, rowName);
		return getTextElement(driver, AbstractPageUI.DYNAMIC_DATA_IN_TABLE, rowName);
	}

	public String getTextValueInDynamicTextbox(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX, fieldName);
		return getAttributeValue(driver, AbstractPageUI.DYNAMIC_TEXTBOX, "value", fieldName);
	}

	public String getTextValueInDynamicTextarea(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTAREA, fieldName);
		return getTextElement(driver, AbstractPageUI.DYNAMIC_TEXTAREA, fieldName);
	}

	public void selectItemInDynamicDropdown(WebDriver driver, String valueToSelect, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_DROPDOWN_LIST, fieldName);
		selectItemInHtmlDropdownByVisibleText(driver, AbstractPageUI.DYNAMIC_DROPDOWN_LIST, valueToSelect, fieldName);
	}

	public String getCurrentSelectedItemInDynamicDropdown(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_DROPDOWN_LIST, fieldName);
		return getCurrentSelectedItemInHtmlDropdown(driver, AbstractPageUI.DYNAMIC_DROPDOWN_LIST, fieldName).getText();
	}

	public boolean isDynamicAlertDisplayed(WebDriver driver, String alertMessage) {
		waitForAlertPresent(driver);
		return getTextAlert(driver).equals(alertMessage);
	}
}
