package commons;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		return driver.switchTo().alert().getText();
	}

	public void sendKeyToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	/* Web Element */
	public void clickToElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void sendKeyToElement(WebDriver driver, String localtor, String text) {
		element = driver.findElement(By.xpath(localtor));
		element.sendKeys(text);
	}

	public void selectItemInHtmlDropdownByValue(WebDriver driver, String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		select = new Select(element);
		select.selectByValue(value);
	}

	public void selectItemInHtmlDropdownByVisibleTex(WebDriver driver, String locator, String visibleText) {
		element = driver.findElement(By.xpath(locator));
		select = new Select(element);
		select.selectByVisibleText(visibleText);
	}

	public void selectItemInDropdown(WebDriver driver, String parentLocator, String allOptionsLocator, String value) {
		element = driver.findElement(By.xpath(parentLocator));
		javascriptExecutor = (JavascriptExecutor) driver;

		if (element.isDisplayed()) {
			element.click();
		} else {
			javascriptExecutor.executeScript("arguments[0].scrollIntoView[0]", element);
			javascriptExecutor.executeScript("arguments[0].click()", element);
		}

		elements = driver.findElements(By.xpath(allOptionsLocator));

		explicitWait = new WebDriverWait(driver, 30);
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

	public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
		element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attributeName);
	}

	public String getTextElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.getText();
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

	public boolean isControlSelected(WebDriver driver, String locator) {
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

	public void scrollToBottomPage(WebDriver driver) {
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("window.scrollBy(0, document.body.scrollHeight)");
	}

	public void scrollToElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("arguments[0].scrollIntoView[0]", element);
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
		boolean status = (boolean) javascriptExecutor.executeScript(
				"return arguments[0].complete "
						+ "&& typeof arguments[0].naturalWidth!=\"undefined\" && arguments[0].naturalWidth > 0",
				element);
		return status;
	}

	public boolean verifyTextInInnerText(WebDriver driver, String expectedText) {
		javascriptExecutor = (JavascriptExecutor) driver;
		String actualText = (String) javascriptExecutor
				.executeScript("return document.documentElement.innerText.match('" + expectedText + "')[0]");
		return actualText.equals(expectedText);

	}

	public void waitForElementPresent(WebDriver driver, String locator) {
		byLocator = By.xpath(locator);
		explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		byLocator = By.xpath(locator);
		explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		byLocator = By.xpath(locator);
		explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byLocator));
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		byLocator = By.xpath(locator);
		explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}

	public void waitForAlertPresent(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void uploadFiles(WebDriver driver, String selectFileButtonXpath, String uploadFileButtonXpath,
			String[] files) {

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

	public void uploadFilesByRobotClass(WebDriver driver, String selectFileButton, String uploadFileButtonXpath,
			String[] files) throws InterruptedException, AWTException {
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
}
