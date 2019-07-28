package com.bankguru.account;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageFactory.HomePageFactory;
import PageFactory.LoginPageFactory;
import PageFactory.RegisterPageFactory;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Account_Level03_RegisterAndLogin_PageFactoryPattern {
	WebDriver driver;
	String email, userID, password, loginPageURL;
	LoginPageFactory loginPage;
	RegisterPageFactory registerPage;
	HomePageFactory homePage;

	@BeforeClass
	public void setUp() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		loginPage = new LoginPageFactory(driver);
		registerPage = new RegisterPageFactory(driver);
		homePage = new HomePageFactory(driver);
		
		driver.get("http://demo.guru99.com/v4/");

		loginPageURL = loginPage.getLoginPageURL();
	}

	@Test
	public void TC_01_RegisterAccount() {
		email = randomeEmail();

		loginPage.clickHereLink();

		registerPage.inputToEmailIDTextbox(email);
		registerPage.clickSubmitButton();

		userID = registerPage.getUserIDInfo();
		password = registerPage.getPasswordInfo();
	}

	@Test
	public void TC_02_Login() {

		registerPage.openLoginPageURL(loginPageURL);

		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickLoginButton();

		Assert.assertTrue(homePage.isWelcomeMessageDisplayed("Welcome To Manager's Page of Guru99 Bank"));
		Assert.assertTrue(homePage.isUserIDDisplayed(userID));
	}

	public String randomeEmail() {
		Random random = new Random();
		return "daothituongvi" + random.nextInt(999999) + "@gmail.com";
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
