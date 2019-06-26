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

public class Account_Test01_RegisterAndLogin_StepByStep {
	WebDriver driver;
	String loginURL = "http://demo.guru99.com/v4/";
	String email, userID, password;

	@BeforeClass
	public void setUp() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(loginURL);
	}

	@Test
	public void TC_01_RegisterAccount() {
		email = randomeEmail();

		driver.findElement(By.xpath("//a[.='here']")).click();
		
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//h2[.='Access details to demo site.']")).isDisplayed());

		userID = driver.findElement(By.xpath("//td[contains(.,'User ID')]/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[contains(.,'Password')]/following-sibling::td")).getText();
	}

	@Test
	public void TC_02_Login() {
		driver.get(loginURL);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//marquee[.=\"Welcome To Manager's Page of Guru99 Bank\"]"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[.='Manger Id : " + userID + "'"
				+ "]")).isDisplayed());
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
