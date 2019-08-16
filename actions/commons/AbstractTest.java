package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class AbstractTest {
	private WebDriver driver;
	protected final Log log;
	
	protected AbstractTest() {
		log = LogFactory.getLog(getClass());
	}

	public WebDriver openMultipleBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("Chrome Headless")) {
			System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1366x768");
			driver = new ChromeDriver(options);
		} else {
			System.out.println("The browser type is invalid");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Constants.LONG_TIMEOUT, TimeUnit.SECONDS);

		driver.get("http://demo.guru99.com/V4/");

		return driver;
	}

	private boolean checkPassed(boolean condition) {
		boolean result = true;

		try {
			Assert.assertTrue(condition);
			log.info("==PASSED==");
		} catch (Throwable e) {
			result = false;
			log.info("==FAILED==");

			// Add loi vao ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);

		}
		return result;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkPassed(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean result = true;

		try {
			Assert.assertFalse(condition);
			log.info("==PASSED==");

		} catch (Throwable e) {
			result = false;
			log.info("==FAILED==");

			// Add loi vao ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return result;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean result = true;

		try {
			log.info("Actual: " + actual);
			log.info("Expected: " + expected);
			Assert.assertEquals(expected, actual);
			log.info("==PASSED==");
		} catch (Throwable e) {
			result = false;
			log.info("==FAILED==");

			// Add loi vao ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return result;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
	
	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(1000000);
	}
	
	public void closeBrowserAndDriver(WebDriver driver) {
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("os Name = " + osName);
			
			String cmd="";
			if(driver != null) {
				driver.quit();
			}
			
			if (driver.toString().toLowerCase().contains("chrome")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
				
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}

			if (driver.toString().toLowerCase().contains("internetexplorer")) {
				if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
				
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
			
			log.info("---------- QUIT BROWSER SUCCESS ----------");
				
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		
	}
}
