package com.bankguru.commons;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Common_01_RegisterToSystem extends AbstractTest {
	WebDriver driver;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	String email;
	public static String username, password;
	

	@Parameters("browser")
	@BeforeTest
	public void BeforeTest(String browserName) {
		log.info("Register - STEP 01: Open Login page");
		driver = openMultipleBrowser(browserName);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		verifyTrue(loginPage.isLoginFormDisplayed());
		
		log.info("Register - STEP 02: Click Here link");
		registerPage = loginPage.clickHereLink();
		verifyTrue(registerPage.isRegisterFormDisplayed());
		
		log.info("Register - STEP 03: Input to Email textbox");
		email =  "randomemail" + randomNumber() + "@gmail.com";
		registerPage.inputToDynamicTextbox(driver, "emailid", email);
		
		log.info("Register - STEP 04: Click Submit button");
		registerPage.clickDynamicButton(driver, "btnLogin");
		
		log.info("Register - STEP 05: Get username and password info");
		username = registerPage.getUserIDInfo();
		password = registerPage.getPasswordInfo();
		
		closeBrowserAndDriver(driver);
	}

}
