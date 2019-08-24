package commons;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

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
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("Chrome Headless")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1366x768");
			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("Safari")) {
			driver = new SafariDriver();
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
			log.info("============================ PASSED ============================");
		} catch (Throwable e) {
			result = false;
			log.info("============================ FAILED ============================");

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
			log.info("============================ PASSED ============================");

		} catch (Throwable e) {
			result = false;
			log.info("============================ FAILED ============================");

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
			if (actual instanceof String && expected instanceof String) {
				actual = actual.toString().trim();
				expected = expected.toString().trim();
			}

			log.info("Actual: " + actual);
			log.info("Expected: " + expected);

			Assert.assertEquals(actual, expected, "Values are not matching");
			log.info("============================ PASSED ============================");

		} catch (Throwable e) {
			result = false;
			log.info("============================ FAILED ============================");

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

			String cmd = "";
			if (driver != null) {
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

		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}
	
	public DateTime getCurrentDate() {
		DateTime currentDate = new DateTime(DateTimeZone.UTC);
		currentDate = DateTime.now();
		return currentDate;
	}
	
	public DateTime getParticularDate(int year, int month, int day) {
		DateTime particularDate = new DateTime(year, month, day, 0, 0, 0, 0, DateTimeZone.UTC);
		return particularDate;
	}
	
	public String formatDate(DateTime date, String formatPattern) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatPattern);
		return date.toString(dateTimeFormatter);
	}
}
